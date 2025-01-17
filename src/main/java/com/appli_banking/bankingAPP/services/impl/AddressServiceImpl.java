package com.appli_banking.bankingAPP.services.impl;

import com.appli_banking.bankingAPP.dto.AddressDto;
import com.appli_banking.bankingAPP.models.Address;
import com.appli_banking.bankingAPP.repositories.AddressRepository;
import com.appli_banking.bankingAPP.services.AddressService;
import com.appli_banking.bankingAPP.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);

        return addressRepository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return addressRepository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No address found with the ID : " + id));

    }

    @Override
    public void delete(Integer id) {
        addressRepository.deleteById(id);
    }
}
