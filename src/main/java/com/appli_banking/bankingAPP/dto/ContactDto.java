package com.appli_banking.bankingAPP.dto;

import com.appli_banking.bankingAPP.models.Contact;
import com.appli_banking.bankingAPP.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContactDto {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String iban;

    private Integer userId;


    public static ContactDto fromEntity(Contact contact) {
        return ContactDto.builder()
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();
    }

    public static Contact toEntity(ContactDto contactDto) {
        return Contact.builder()
                .firstname(contactDto.getFirstname())
                .lastname(contactDto.getLastname())
                .email(contactDto.getEmail())
                .iban(contactDto.getIban())
                .user(
                        User.builder()
                                .id(contactDto.getUserId())
                                .build()
                )
                .build();
    }
}
