package com.test.dansmultipro.test.dansmultipro.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignupRequest implements Serializable {
    private String Username;
    private String password;
}
