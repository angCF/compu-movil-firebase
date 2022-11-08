// Generated by Dagger (https://dagger.dev).
package com.puj.taller03_cm.dependencies.modules;

import com.puj.taller03_cm.services.CameraService;
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
public final class CameraModule_ProvideCameraServiceFactory implements Factory<CameraService> {
  private final CameraModule module;

  public CameraModule_ProvideCameraServiceFactory(CameraModule module) {
    this.module = module;
  }

  @Override
  public CameraService get() {
    return provideCameraService(module);
  }

  public static CameraModule_ProvideCameraServiceFactory create(CameraModule module) {
    return new CameraModule_ProvideCameraServiceFactory(module);
  }

  public static CameraService provideCameraService(CameraModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideCameraService());
  }
}
