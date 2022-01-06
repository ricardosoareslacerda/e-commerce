package com.avaliacao.ecommerce.service;

import com.avaliacao.ecommerce.model.AddressType;
import com.avaliacao.ecommerce.repository.AddressTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressTypeService {

    @Autowired
    private AddressTypeRepository  addressTypeRepository;

    public List<AddressType> listAllAddresss() {
        return  addressTypeRepository.findAll();
    }

    public Optional<AddressType> findByCode(int code) {
        return  addressTypeRepository.findById(code);
    }

    public AddressType save(AddressType addressType) {
        return  addressTypeRepository.save(addressType);
    }

    public AddressType update(int code, AddressType addressType) {
        AddressType addressTypeModel = this.isExists(code);
        BeanUtils.copyProperties(addressType, addressTypeModel, "code");
        return  addressTypeRepository.save(addressTypeModel);
    }

    private AddressType isExists(int code) {
        Optional<AddressType> address = this.findByCode(code);
        if (address.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return address.get();
    }
}
