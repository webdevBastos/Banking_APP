package com.appli_banking.bankingAPP.dto;


import com.appli_banking.bankingAPP.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {

    private Integer id;
    @NotNull(message = "le prenom ne doit pas être vide")
    @NotEmpty(message = "le prenom ne doit pas être vide") // '' vide tout court
    @NotBlank(message = "le prenom ne doit pas être vide") // ' ' espace vide
    private String firstname;

    @NotNull(message = "le nom ne doit pas être vide")
    @NotEmpty (message = "le nom ne doit pas être vide")// '' vide tout court
    @NotBlank(message = "le nom ne doit pas être vide") // ' ' espace vide
    private String lastname;

    @NotNull(message = "email ne doit pas être vide")
    @NotEmpty (message = "email ne doit pas être vide")// '' vide tout court
    @NotBlank (message = "email ne doit pas être vide")// ' ' espace vide
    @Email(message = " email n'est pas conforme")
    private String email;

    @NotNull(message = "le mot de passe ne doit pas être vide")
    @NotEmpty(message = "le mot de passe ne doit pas être vide")
    @NotBlank(message = "le mot de passe ne doit pas être vide")
    @Size(min = 8, max = 16, message = " le mot de passe doit être entre 8 et 16 caractères") //le mot de passe doit avoir minimum 8 caracteres et ne doit pas depasser 16 caracteres
    private String password;

    @Past //pour selectionner une date de passé
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime birthdaydate;

    public static UserDto fromEntity(User user){
        //null check
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public static User toEntity(UserDto user){
        //null check
        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
