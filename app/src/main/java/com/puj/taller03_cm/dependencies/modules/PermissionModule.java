package com.puj.taller03_cm.dependencies.modules;


import com.puj.taller03_cm.utils.PermissionHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PermissionModule {

    @Singleton
    @Provides
    public PermissionHelper providePermissionHelper() {
        return new PermissionHelper();
    }
}
