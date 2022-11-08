package com.puj.taller03_cm.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String name;
    //private long fechaNacimiento;
    private String tipoDocumento;
    private long numeroDocumento;
    private long phoneNumber;
    private String email;
    private String role;
    //private String pass2;
    //private long createdAt;
    //private long lastLogin;
}
