package com.puj.taller03_cm.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class GeoInfo {
    private Double latitude;
    private Double longitude;
    private String name;
}
