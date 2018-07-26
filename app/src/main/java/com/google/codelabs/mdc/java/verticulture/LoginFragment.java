package com.google.codelabs.mdc.java.verticulture;

import java.util.*;
import java.io.*;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.Text;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;
import io.particle.android.sdk.devicesetup.ParticleDeviceSetupLibrary;
import io.particle.android.sdk.utils.Async;
import io.particle.android.sdk.utils.ui.Toaster;

/**
 * Fragment representing the login screen for Verticulture.
 */
public class LoginFragment extends Fragment {
    //HE::HELLO World!!!!
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ver_login_fragment, container, false);

        // Snippet from "Navigate to the next Fragment" section goes here.
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputEditText emailEditText = view.findViewById(R.id.email_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);

        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleLogin(passwordEditText.getText(), emailEditText.getText(), passwordEditText, emailEditText);
            }
        });

        // Clear the error once more than 8 characters are typed.
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordLimit(passwordEditText.getText())) {
                    passwordTextInput.setError(null); //Clear the error
                }else{
                    passwordTextInput.setError("Password must be longer than 8 characters");
                }
                return false;
            }
        });
        return view;
    }

    // Particle Login Asynctask call
    private void particleLogin(@Nullable Editable password, @Nullable Editable ID, TextInputEditText password_edit_text, TextInputEditText email_edit_text){
        new AsyncTask<Void, Void, Boolean>() {
            protected Boolean doInBackground(Void... params) {
                //  LOG IN TO PARTICLE
                try {
                    // Log in to Particle Cloud using username and password
                    ParticleCloudSDK.getCloud().logIn(ID.toString(), password.toString());
                    return true;
                }
                catch(ParticleCloudException e) {
                    return false;
                }
            }

            protected void onPostExecute(Boolean state) {
                // Show Toast containing message from doInBackground
                isPasswordValid(state, password_edit_text, email_edit_text);
                Toaster.s(LoginFragment.this, state.toString());
            }
        }.execute();

    }

    //check if connection to particle cloud succeded
    private void isPasswordValid(Boolean state, TextInputEditText password_edit_text, TextInputEditText email_edit_text){
        if (!state) {
            password_edit_text.setError(getString(R.string.ver_error_password));
        } else {
            password_edit_text.setError(null); // Clear the error
            ((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); //Go to product grid fragment
        }
    }

    //check if password is below 8
    private boolean isPasswordLimit(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }


}
