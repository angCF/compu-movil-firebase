// Generated by Dagger (https://dagger.dev).
package com.puj.taller03_cm.dependencies.modules;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class GeocoderModule_ProvidesApplicationFactory implements Factory<Application> {
  private final GeocoderModule module;

  public GeocoderModule_ProvidesApplicationFactory(GeocoderModule module) {
    this.module = module;
  }

  @Override
  public Application get() {
    return providesApplication(module);
  }

  public static GeocoderModule_ProvidesApplicationFactory create(GeocoderModule module) {
    return new GeocoderModule_ProvidesApplicationFactory(module);
  }

  public static Application providesApplication(GeocoderModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.providesApplication());
  }
}