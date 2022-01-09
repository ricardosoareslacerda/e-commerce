package com.avaliacao.ecommerce.service;

import com.avaliacao.ecommerce.excecao.BusinessException;
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

    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findByCode(int code) {
        return clientRepository.findById(code);
    }

    public Client save(Client client) {
        this.validateDuplicate(client);
        return clientRepository.save(client);
    }

    public Client update(int code, Client client) {
        Client clientModel = this.validateExists(code);
        this.validateDuplicate(client);
        BeanUtils.copyProperties(client, clientModel, "code");
        return clientRepository.save(clientModel);
    }

    public void delete(int code) {
        clientRepository.deleteById(code);
    }

    private Client validateExists(int code) {
        Optional<Client> client = this.findByCode(code);
        if (client.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return client.get();
    }

    private void validateDuplicate(Client client) {
        Client clientModel = clientRepository.findByNome(client.getName());
        if (clientModel != null &&
                clientModel.getCode() != client.getCode()) {
            throw new BusinessException(
                    String.format("A cliente %s já esta cadastrado", client.getName().toUpperCase()));
        }
    }
}
