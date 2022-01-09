package com.avaliacao.ecommerce.service;

import com.avaliacao.ecommerce.excecao.BusinessException;
import com.avaliacao.ecommerce.model.Address;
import com.avaliacao.ecommerce.repository.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> listAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findByCode(int code) {
        return addressRepository.findById(code);
    }

    public Address save(Address address) {
        this.validateDuplicate(address);
        return addressRepository.save(address);
    }

    public Address update(int code, Address address) {
        Address addressModel = this.validateExists(code);
        this.validateDuplicate(address);
        BeanUtils.copyProperties(address, addressModel, "code");
        return addressRepository.save(addressModel);
    }

    public void delete(int code) {
        addressRepository.deleteById(code);
    }

    private Address validateExists(int code) {
        Optional<Address> address = this.findByCode(code);
        if (address.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return address.get();
    }

    private void validateDuplicate(Address address) {
        Address addressModel = addressRepository.findByNome(address.getPublicPlace());
        if (addressModel != null &&
                addressModel.getCode() != address.getCode()) {
            throw new BusinessException(
                    String.format("A Endereço %s já esta cadastrado", address.getPublicPlace().toUpperCase()));
        }
    }
}
