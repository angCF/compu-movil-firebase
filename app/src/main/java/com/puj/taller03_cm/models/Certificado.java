package com.puj.taller03_cm.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Certificado {
    private String titulo;
    private String institucion;
    private Long fechaExpedicion;
}
