package com.engineering.dokkan.view.login;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.engineering.dokkan.R;
import com.engineering.dokkan.view.base.BaseFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginFragment extends BaseFragment {
    private Button btn_sing;
    private FirebaseUser currentUser;
    private EditText Email, Password;
    private TextView newAccount, forgetPassword_btn;
    private FirebaseAuth firebaseAuth;
    ProgressDialog loadingbar;
    private DatabaseReference RootRef;

    public LoginFragment() {

    }

    @Override
    public int getLayoutId() {
        return (R.layout.fragment_login2);
    }

    NavController getNavController() {
        return Navigation.findNavController(requireActivity(), R.id.my_nav_host);
    }

    @Override
    public void initializeViews(View view) {
        initialization(view);
        setListeners();
    }

    public void initialization(View view) {
        FirebaseApp.initializeApp(requireActivity());
        firebaseAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();
        Email = (EditText) view.findViewById(R.id.email_login);
        Password = (EditText) view.findViewById(R.id.password_login);
        forgetPassword_btn = (TextView) view.findViewById(R.id.forget_password);
        newAccount = (TextView) view.findViewById(R.id.regester_here);
        loadingbar = new ProgressDialog(getActivity());
        btn_sing = view.findViewById(R.id.but_sing);
    }

    @Override
    public void setListeners() {
        btn_sing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowUserToLogin();
            }
        });

        // send to register Activity

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        forgetPassword_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getNavController().navigate(R.id.action_loginFragment_to_resetPassword);

            }
        });
    }

    private void allowUserToLogin() {
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "This email is not valid.", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "This password is not valid.", Toast.LENGTH_SHORT).show();

        } else {

            loadingbar.setTitle("Sing In");
            loadingbar.setMessage("please Wait,While we are checking the account.");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                    String currentUserID = firebaseAuth.getCurrentUser().getUid();
                                    RootRef.child("Users").child(currentUserID).setValue("");
                                    loadingbar.dismiss();
                                    getNavController().navigate(R.id.action_loginFragment_to_homeFragment2);
                                    Toast.makeText(getActivity(), "Logged is succesfully.", Toast.LENGTH_SHORT).show();

                                } else {
                                    loadingbar.dismiss();
                                    Toast.makeText(getActivity(), "please veify your email address", Toast.LENGTH_SHORT).show();

                                }

                            } else {

                                loadingbar.dismiss();
                                String error = task.getException().getMessage();
                                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
