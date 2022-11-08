package com.puj.taller03_cm.dependencies.modules;

import android.app.Application;

import com.puj.taller03_cm.services.LocationService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;

@Module
@AllArgsConstructor
public class LocationModule {
    private final Application application;

    @Provides
    @Singleton
    Application providesApplication(){
        return application;
    }

    @Provides
    public LocationService provideLocationService() {
        return new LocationService(application.getApplicationContext());
    }
}
