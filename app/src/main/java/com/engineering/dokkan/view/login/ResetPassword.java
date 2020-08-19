package com.engineering.dokkan.view.login;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPassword extends BaseFragment {
    private EditText registeredEmail;
    private Button resetPassword_btn;
    private FirebaseAuth firebaseAuth;
    private ImageView backbutton ;


    public ResetPassword() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() { return R.layout.fragment_reset_password; }

    @Override
    public void initializeViews(View view) {
        registeredEmail = view.findViewById(R.id.rest_email);
        resetPassword_btn = view.findViewById(R.id.reset_btn);
        firebaseAuth=FirebaseAuth.getInstance();
        backbutton = view.findViewById(R.id.arrow_back);

    }



    @Override
    public void setListeners() {

        resetPassword_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetEmail();
            }
        });


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNavController().navigate(R.id.action_resetPassword_to_loginFragment);
            }
        });


    }

    private void ResetEmail() {
            String email = registeredEmail.getText().toString();
            if(TextUtils.isEmpty(email)){

                Toast.makeText(getActivity(), "This email is not valid.", Toast.LENGTH_SHORT).show();

            }else {
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getActivity(), " email sent successfully.", Toast.LENGTH_SHORT).show();

                                }else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }


