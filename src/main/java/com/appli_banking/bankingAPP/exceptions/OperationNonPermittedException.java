package com.appli_banking.bankingAPP.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OperationNonPermittedException extends RuntimeException{


    private final String errorMsg;

    private final String operationId;

    private final String source;

    private final String dependency;

    /*
    on veut supprimer un compte client mais le compte client a deja des operations, donc :

    errorMsg: on peut pas supprimer ce compte utilisateur

    operationId : delete

    source: userDto (utilisateur de façon général)

    dependence: transaction


    */
}
