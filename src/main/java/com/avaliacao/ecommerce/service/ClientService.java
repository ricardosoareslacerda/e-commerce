package com.avaliacao.ecommerce.service;

import com.avaliacao.ecommerce.model.Client;
import com.avaliacao.ecommerce.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> listAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> findByCode(int code) {
        return clientRepository.findById(code);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Client update(int code, Client client) {
        Client clientModel = this.isExists(code);
        BeanUtils.copyProperties(client, clientModel, "code");
        return clientRepository.save(clientModel);
    }

    private Client isExists(int code) {
        Optional<Client> client = this.findByCode(code);
        if (client.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return client.get();
    }
}
