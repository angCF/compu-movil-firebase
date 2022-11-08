// Generated by Dagger (https://dagger.dev).
package com.puj.taller03_cm.dependencies.modules;

import com.puj.taller03_cm.utils.AlertsHelper;
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
public final class AlertsModule_ProvideAlertHelperFactory implements Factory<AlertsHelper> {
  private final AlertsModule module;

  public AlertsModule_ProvideAlertHelperFactory(AlertsModule module) {
    this.module = module;
  }

  @Override
  public AlertsHelper get() {
    return provideAlertHelper(module);
  }

  public static AlertsModule_ProvideAlertHelperFactory create(AlertsModule module) {
    return new AlertsModule_ProvideAlertHelperFactory(module);
  }

  public static AlertsHelper provideAlertHelper(AlertsModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideAlertHelper());
  }
}