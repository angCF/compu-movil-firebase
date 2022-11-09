// Generated by view binder compiler. Do not edit!
package com.puj.taller03_cm.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.puj.taller03_cm.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCreateUserBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final MaterialButton cameraButton;

  @NonNull
  public final Button createButton;

  @NonNull
  public final TextInputLayout createDisplayName;

  @NonNull
  public final TextInputLayout createDisplaySurname;

  @NonNull
  public final TextInputLayout createEmail;

  @NonNull
  public final TextInputLayout createPass;

  @NonNull
  public final MaterialButton galeriaButton;

  @NonNull
  public final TextView imagenDeContacto;

  @NonNull
  public final TextInputLayout latitud;

  @NonNull
  public final TextInputLayout longitud;

  @NonNull
  public final TextView mainLabel;

  @NonNull
  public final TextInputLayout numberid;

  private ActivityCreateUserBinding(@NonNull RelativeLayout rootView,
      @NonNull MaterialButton cameraButton, @NonNull Button createButton,
      @NonNull TextInputLayout createDisplayName, @NonNull TextInputLayout createDisplaySurname,
      @NonNull TextInputLayout createEmail, @NonNull TextInputLayout createPass,
      @NonNull MaterialButton galeriaButton, @NonNull TextView imagenDeContacto,
      @NonNull TextInputLayout latitud, @NonNull TextInputLayout longitud,
      @NonNull TextView mainLabel, @NonNull TextInputLayout numberid) {
    this.rootView = rootView;
    this.cameraButton = cameraButton;
    this.createButton = createButton;
    this.createDisplayName = createDisplayName;
    this.createDisplaySurname = createDisplaySurname;
    this.createEmail = createEmail;
    this.createPass = createPass;
    this.galeriaButton = galeriaButton;
    this.imagenDeContacto = imagenDeContacto;
    this.latitud = latitud;
    this.longitud = longitud;
    this.mainLabel = mainLabel;
    this.numberid = numberid;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCreateUserBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCreateUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_create_user, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCreateUserBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.camera_button;
      MaterialButton cameraButton = ViewBindings.findChildViewById(rootView, id);
      if (cameraButton == null) {
        break missingId;
      }

      id = R.id.createButton;
      Button createButton = ViewBindings.findChildViewById(rootView, id);
      if (createButton == null) {
        break missingId;
      }

      id = R.id.createDisplayName;
      TextInputLayout createDisplayName = ViewBindings.findChildViewById(rootView, id);
      if (createDisplayName == null) {
        break missingId;
      }

      id = R.id.createDisplaySurname;
      TextInputLayout createDisplaySurname = ViewBindings.findChildViewById(rootView, id);
      if (createDisplaySurname == null) {
        break missingId;
      }

      id = R.id.createEmail;
      TextInputLayout createEmail = ViewBindings.findChildViewById(rootView, id);
      if (createEmail == null) {
        break missingId;
      }

      id = R.id.createPass;
      TextInputLayout createPass = ViewBindings.findChildViewById(rootView, id);
      if (createPass == null) {
        break missingId;
      }

      id = R.id.galeria_button;
      MaterialButton galeriaButton = ViewBindings.findChildViewById(rootView, id);
      if (galeriaButton == null) {
        break missingId;
      }

      id = R.id.imagenDeContacto;
      TextView imagenDeContacto = ViewBindings.findChildViewById(rootView, id);
      if (imagenDeContacto == null) {
        break missingId;
      }

      id = R.id.latitud;
      TextInputLayout latitud = ViewBindings.findChildViewById(rootView, id);
      if (latitud == null) {
        break missingId;
      }

      id = R.id.longitud;
      TextInputLayout longitud = ViewBindings.findChildViewById(rootView, id);
      if (longitud == null) {
        break missingId;
      }

      id = R.id.mainLabel;
      TextView mainLabel = ViewBindings.findChildViewById(rootView, id);
      if (mainLabel == null) {
        break missingId;
      }

      id = R.id.numberid;
      TextInputLayout numberid = ViewBindings.findChildViewById(rootView, id);
      if (numberid == null) {
        break missingId;
      }

      return new ActivityCreateUserBinding((RelativeLayout) rootView, cameraButton, createButton,
          createDisplayName, createDisplaySurname, createEmail, createPass, galeriaButton,
          imagenDeContacto, latitud, longitud, mainLabel, numberid);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
