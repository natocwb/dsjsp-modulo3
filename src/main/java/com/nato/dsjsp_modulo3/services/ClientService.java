package com.nato.dsjsp_modulo3.services;

import com.nato.dsjsp_modulo3.dto.ClientDTO;
import com.nato.dsjsp_modulo3.entities.Client;
import com.nato.dsjsp_modulo3.repositories.ClientRepository;
import com.nato.dsjsp_modulo3.services.exceptions.DatabaseException;
import com.nato.dsjsp_modulo3.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client not found"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = clientRepository.findAll(pageable);
        return result.map(p -> new ClientDTO(p));
    }
    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();
        updateData(client, dto);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }


    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
        Client client = clientRepository.getReferenceById(id);
        updateData(client, dto);
        client = clientRepository.save(client);
        return new ClientDTO(client);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            clientRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }


    private void updateData(Client client, ClientDTO dto) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }

}
