// Generated by Dagger (https://dagger.dev).
package com.puj.taller03_cm.dependencies.modules;

import com.puj.taller03_cm.utils.PermissionHelper;
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
public final class PermissionModule_ProvidePermissionHelperFactory implements Factory<PermissionHelper> {
  private final PermissionModule module;

  public PermissionModule_ProvidePermissionHelperFactory(PermissionModule module) {
    this.module = module;
  }

  @Override
  public PermissionHelper get() {
    return providePermissionHelper(module);
  }

  public static PermissionModule_ProvidePermissionHelperFactory create(PermissionModule module) {
    return new PermissionModule_ProvidePermissionHelperFactory(module);
  }

  public static PermissionHelper providePermissionHelper(PermissionModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.providePermissionHelper());
  }
}
