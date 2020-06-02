package com.engineering.dokkan.view.register;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {
private Button register;
    private EditText username , email , password , confirmPassword;
private CheckBox ch ;

    public RegisterFragment() {
        // Required empty public constructor
    }


        @Override
    public int getLayoutId() {
        return R.layout.fragment_register;
    }
    NavController getNavController() {
        return Navigation.findNavController(getActivity(), R.id.my_nav_host);
    }

    private boolean validateUsername() {
        String usernameInput = username.getText().toString();
        if (usernameInput.isEmpty()) {
            username.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            username.setError("Username is too long");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String emailInput = email.getText().toString();
        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }

    }
    private boolean validatePassword(){
        String passInput = password.getText().toString();
        if (passInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (passInput.length() <6) {
            username.setError("Password shouhd be more than 6 characters");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
    private boolean validateConfirmPassword(){
String confirmPassInput = confirmPassword.getText().toString();
        String passInput = password.getText().toString();

        if (confirmPassInput.isEmpty()) {
            confirmPassword.setError("Field can't be empty");
            return false;
        }
        else if(!confirmPassInput.equals(passInput)){
            confirmPassword.setError("Error!! Confirm Password should match Password");
            return false;
        }
        else {
            confirmPassword.setError(null);
            return true;
        }
    }


       @Override
    public void initializeViews(View view) {
        register = view.findViewById(R.id.butt);
        username = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        confirmPassword = view.findViewById(R.id.confirm_password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateUsername()==true &&validateEmail()==true&&validatePassword()==true&&validateConfirmPassword()==true){
                    getNavController().navigate(R.id.action_registerFragment_to_profileFragment);
                }

            }

        });
    }

    @Override
    public void setListeners() {

    }





}
