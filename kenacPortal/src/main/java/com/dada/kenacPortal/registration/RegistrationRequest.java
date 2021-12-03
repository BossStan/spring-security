package com.dada.kenacPortal.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode

public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String tittle;
    private String password;


}
