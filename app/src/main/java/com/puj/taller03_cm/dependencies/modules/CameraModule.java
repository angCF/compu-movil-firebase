package com.puj.taller03_cm.dependencies.modules;

import com.puj.taller03_cm.services.CameraService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CameraModule {
    @Singleton
    @Provides
    public CameraService provideCameraService() {
        return new CameraService();
    }
}
