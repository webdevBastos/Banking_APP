package com.appli_banking.bankingAPP.dto;


import com.appli_banking.bankingAPP.models.Address;
import com.appli_banking.bankingAPP.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressDto {

    private Integer id;
    @NotNull
    @NotEmpty // '' vide tout court
    @NotBlank // ' ' espace vide
    private String street;

    private Integer houseNumber;
    @NotNull
    @NotEmpty // '' vide tout court
    @NotBlank // ' ' espace vide
    private Integer zipCode;
    @NotNull
    @NotEmpty // '' vide tout court
    @NotBlank // ' ' espace vide
    private String city;
    @NotNull
    @NotEmpty // '' vide tout court
    @NotBlank // ' ' espace vide
    private String country;

    private  Integer userId;

    public static AddressDto fromEntity(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .userId(address.getUser().getId())
                .build();
    }

    public static Address toEntity(AddressDto address) {
        return Address.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .user(User.builder().id(address.getId()).build())
                .build();
    }
}
