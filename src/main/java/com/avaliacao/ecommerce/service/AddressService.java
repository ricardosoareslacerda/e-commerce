package com.avaliacao.ecommerce.service;

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

    public List<Address> listAllAddresss() {
        return addressRepository.findAll();
    }

    public Optional<Address> findByCode(int code) {
        return addressRepository.findById(code);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address update(int code, Address address) {
        Address addressModel = this.isExists(code);
        BeanUtils.copyProperties(address, addressModel, "code");
        return addressRepository.save(addressModel);
    }

    private Address isExists(int code) {
        Optional<Address> address = this.findByCode(code);
        if (address.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return address.get();
    }
}
