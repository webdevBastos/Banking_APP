package com.appli_banking.bankingAPP.services.impl;

import com.appli_banking.bankingAPP.dto.ContactDto;
import com.appli_banking.bankingAPP.models.Contact;
import com.appli_banking.bankingAPP.repositories.ContactRepository;
import com.appli_banking.bankingAPP.services.ContactService;
import com.appli_banking.bankingAPP.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ObjectsValidator<ContactDto> validator;

    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.toEntity(dto);

        return contactRepository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return contactRepository.findAll()
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return contactRepository.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No contact was found with the ID : " +id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete
        contactRepository.deleteById(id);
    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return contactRepository.findAllByUserId(userId)
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }
}
