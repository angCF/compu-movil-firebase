package com.puj.taller03_cm;

import android.app.Application;

import com.puj.taller03_cm.dependencies.components.ApplicationComponent;
import com.puj.taller03_cm.dependencies.components.DaggerApplicationComponent;
import com.puj.taller03_cm.dependencies.modules.GeoInfoModule;
import com.puj.taller03_cm.dependencies.modules.GeocoderModule;
import com.puj.taller03_cm.dependencies.modules.LocationModule;


import lombok.Getter;

@Getter
public class App extends Application {
     ApplicationComponent appComponent = DaggerApplicationComponent.builder()
             .locationModule(new LocationModule(this))
             .geocoderModule(new GeocoderModule(this))
             .geoInfoModule(new GeoInfoModule(this))
             .build();
}

