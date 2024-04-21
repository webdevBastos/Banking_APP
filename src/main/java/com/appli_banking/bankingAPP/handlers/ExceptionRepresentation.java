package com.appli_banking.bankingAPP.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
/*
 si par exemple on pas validationErrors on affiche errorMessage et errorSource (pour tout les attributs)
 quoi inclure comme erreur en JSON
*/
public class ExceptionRepresentation {

    private String errorMessage;

    private String errorSource;

    private Set<String> validationErrors;
}
