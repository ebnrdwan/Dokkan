package com.engineering.dokkan.view.register;


import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {
    private Button register;
    EditText username, email, password, confirmPassword;
    private ProgressDialog mProgress;
    FirebaseAuth mFireBaseAuth;
    private DatabaseReference databaseReference;
    private String currentUserID;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_register;
    }




    @Override
    public void initializeViews(View view) {
        register = view.findViewById(R.id.butt);
        username = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        confirmPassword = view.findViewById(R.id.confirm_password);
        mFireBaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mProgress = new ProgressDialog(getActivity());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }

        });
    }

    private void createAccount() {
        final String name = username.getText().toString();
        final String mail = email.getText().toString();
        String pass = password.getText().toString();
        String confirmPass = confirmPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "Please! write your user name first...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(mail)) {
            Toast.makeText(getContext(), "Error!! Email is Empty", Toast.LENGTH_LONG).show();

        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getContext(), "Error!! PassWord is Empty", Toast.LENGTH_LONG).show();

        }
        if (TextUtils.isEmpty(confirmPass)) {
            Toast.makeText(getContext(), "Error!! ConfirmPassWord is Empty", Toast.LENGTH_LONG).show();

        } else if (!pass.equals(confirmPass)) {
            Toast.makeText(getContext(), "Error!! ConfirmPassWord should match PassWord", Toast.LENGTH_LONG).show();

        } else {
            mProgress.setTitle("Create User");
            mProgress.setMessage("please Wait! User creation is in Progress...");
            mProgress.setCanceledOnTouchOutside(true);
            mProgress.show();

            mFireBaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        currentUserID = mFireBaseAuth.getCurrentUser().getUid();

                        HashMap<String, String> map = new HashMap<>();
                        map.put("uid", currentUserID);
                        map.put("name", name);
                        map.put("email", mail);

                        databaseReference.child("Users").child(currentUserID).setValue(map);
                        mProgress.dismiss();
                        Toast.makeText(getContext(), "Registered Successful :) \nPlease, Check your E-mail for Verifications", Toast.LENGTH_LONG).show();

                        mFireBaseAuth.getCurrentUser().sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            getNavController().navigate(R.id.action_registerFragment_to_LoginFragment);
                                        } else {
                                            mProgress.dismiss();
                                            String message = task.getException().toString();
                                            Toast.makeText(getContext(), "Exception:" + message, Toast.LENGTH_LONG).show();

                                        }
                                    }
                                });
                    } else {
                        String message = task.getException().toString();
                        Toast.makeText(getContext(), "Exception:" + message, Toast.LENGTH_LONG).show();
                    }
                }


            });
        }

    }


    @Override
    public void setListeners() {

    }


}