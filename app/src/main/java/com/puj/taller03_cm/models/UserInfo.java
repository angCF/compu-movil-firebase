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
    private String uuid;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private long numeroDocumento;
    private Double latitud;
    private Double longitud;
    private boolean disponible;
    private String imagePath;

    public String getName() {
        return nombre;
    }
}
