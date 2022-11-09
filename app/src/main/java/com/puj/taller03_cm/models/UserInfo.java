package com.puj.taller03_cm.models;

import com.google.android.gms.maps.model.LatLng;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private long numeroDocumento;
    private String latitud;
    private String longitud;
    //private long fechaNacimiento;
//    private String tipoDocumento;
//    private long phoneNumber;
//    private String role;
    //private String pass2;
    //private long createdAt;
    //private long lastLogin;

//    public UserInfo() {}
}
