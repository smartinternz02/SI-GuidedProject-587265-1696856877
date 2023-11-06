// Generated by view binder compiler. Do not edit!
package com.myapp.moneytracker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputLayout;
import com.myapp.moneytracker.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class UpdateDialogBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextInputLayout amountTIL;

  @NonNull
  public final EditText amountUpdate;

  @NonNull
  public final TextInputLayout categoryTIL;

  @NonNull
  public final AutoCompleteTextView categoryUpdate;

  @NonNull
  public final TextInputLayout dateTIL;

  @NonNull
  public final EditText dateUpdate;

  @NonNull
  public final TextInputLayout noteTIL;

  @NonNull
  public final EditText noteUpdate;

  @NonNull
  public final TextInputLayout titleTIL;

  @NonNull
  public final EditText titleUpdate;

  @NonNull
  public final Button updateButton;

  private UpdateDialogBinding(@NonNull RelativeLayout rootView, @NonNull TextInputLayout amountTIL,
      @NonNull EditText amountUpdate, @NonNull TextInputLayout categoryTIL,
      @NonNull AutoCompleteTextView categoryUpdate, @NonNull TextInputLayout dateTIL,
      @NonNull EditText dateUpdate, @NonNull TextInputLayout noteTIL, @NonNull EditText noteUpdate,
      @NonNull TextInputLayout titleTIL, @NonNull EditText titleUpdate,
      @NonNull Button updateButton) {
    this.rootView = rootView;
    this.amountTIL = amountTIL;
    this.amountUpdate = amountUpdate;
    this.categoryTIL = categoryTIL;
    this.categoryUpdate = categoryUpdate;
    this.dateTIL = dateTIL;
    this.dateUpdate = dateUpdate;
    this.noteTIL = noteTIL;
    this.noteUpdate = noteUpdate;
    this.titleTIL = titleTIL;
    this.titleUpdate = titleUpdate;
    this.updateButton = updateButton;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static UpdateDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static UpdateDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.update_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static UpdateDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.amountTIL;
      TextInputLayout amountTIL = ViewBindings.findChildViewById(rootView, id);
      if (amountTIL == null) {
        break missingId;
      }

      id = R.id.amountUpdate;
      EditText amountUpdate = ViewBindings.findChildViewById(rootView, id);
      if (amountUpdate == null) {
        break missingId;
      }

      id = R.id.categoryTIL;
      TextInputLayout categoryTIL = ViewBindings.findChildViewById(rootView, id);
      if (categoryTIL == null) {
        break missingId;
      }

      id = R.id.categoryUpdate;
      AutoCompleteTextView categoryUpdate = ViewBindings.findChildViewById(rootView, id);
      if (categoryUpdate == null) {
        break missingId;
      }

      id = R.id.dateTIL;
      TextInputLayout dateTIL = ViewBindings.findChildViewById(rootView, id);
      if (dateTIL == null) {
        break missingId;
      }

      id = R.id.dateUpdate;
      EditText dateUpdate = ViewBindings.findChildViewById(rootView, id);
      if (dateUpdate == null) {
        break missingId;
      }

      id = R.id.noteTIL;
      TextInputLayout noteTIL = ViewBindings.findChildViewById(rootView, id);
      if (noteTIL == null) {
        break missingId;
      }

      id = R.id.noteUpdate;
      EditText noteUpdate = ViewBindings.findChildViewById(rootView, id);
      if (noteUpdate == null) {
        break missingId;
      }

      id = R.id.titleTIL;
      TextInputLayout titleTIL = ViewBindings.findChildViewById(rootView, id);
      if (titleTIL == null) {
        break missingId;
      }

      id = R.id.titleUpdate;
      EditText titleUpdate = ViewBindings.findChildViewById(rootView, id);
      if (titleUpdate == null) {
        break missingId;
      }

      id = R.id.updateButton;
      Button updateButton = ViewBindings.findChildViewById(rootView, id);
      if (updateButton == null) {
        break missingId;
      }

      return new UpdateDialogBinding((RelativeLayout) rootView, amountTIL, amountUpdate,
          categoryTIL, categoryUpdate, dateTIL, dateUpdate, noteTIL, noteUpdate, titleTIL,
          titleUpdate, updateButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}