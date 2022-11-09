package com.puj.taller03_cm.dependencies.components;

import com.puj.taller03_cm.activities.BasicActivity;
import com.puj.taller03_cm.activities.MapFragment;
import com.puj.taller03_cm.dependencies.modules.AlertsModule;
import com.puj.taller03_cm.dependencies.modules.GeoInfoModule;
import com.puj.taller03_cm.dependencies.modules.GeocoderModule;
import com.puj.taller03_cm.dependencies.modules.LocationModule;
import com.puj.taller03_cm.dependencies.modules.PermissionModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AlertsModule.class, PermissionModule.class,
        GeoInfoModule.class, GeocoderModule.class, LocationModule.class})
public interface ApplicationComponent {
    void inject(BasicActivity activity);
    void inject(MapFragment fragment);
}
