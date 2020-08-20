package com.engineering.dokkan.view.register;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
    private TextView terms_condition;

    private ImageView backbutton ;
    private EditText username, email, password, confirmPassword;
    private ProgressDialog mProgress;
    FirebaseAuth mFireBaseAuth;
    private DatabaseReference databaseReference;
    private String currentUserID;
    private CheckBox ckbox;


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
        backbutton = view.findViewById(R.id.arrow_back);

        terms_condition = view.findViewById(R.id.tv_terms);
        ckbox = view.findViewById(R.id.cb_reg);

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


            if (!ckbox.isChecked()) {
                Toast.makeText(getContext(), " please accept our terms & condition first !!", Toast.LENGTH_LONG).show();
            }

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
                                                String message = task.getException().getMessage();
                                                Toast.makeText(getContext(), "Exception:" + message, Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                        } else {
                            mProgress.dismiss();
                            String message = task.getException().getMessage();
                            Toast.makeText(getContext(), "Exception:" + message, Toast.LENGTH_LONG).show();
                        }
                    }


                });
            }

        }


        @Override
        public void setListeners () {
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createAccount();
                }

            });


            terms_condition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showTermsAndCondition();
                }
            });
            backbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getNavController().navigate(R.id.action_registerFragment_to_fragment_Welcome);
                }
            });
        }

    private void showTermsAndCondition() {
        final AlertDialog.Builder mB = new AlertDialog.Builder(getContext());
        mB.setTitle("License Agreement");
        mB.setMessage( "" +
                " Welcome to DOKKAN :))\n" +
                "\n" +
                "These terms and conditions outline the rules and regulations for the use of Dokkan application.\n" +
                "\n" +
                "By accessing this application we assume you accept these terms and conditions. Do not continue to use dokkan if you do not agree to take all of the terms and conditions stated on this page please .\n" +
                "\n" +
                "The following terminology applies to these Terms and Conditions, Privacy Statement and Disclaimer Notice and all Agreements: 'Client', 'You' and 'developer of this app',"
                + "let's be clear with each other , don't be harm for any person on your service." +
                "these app Standard Terms and Conditions, Your product shall mean any audio, video text, images or other material you choose to display on this application. " +
                "By displaying Your Content, you grant Company Name a non-exclusive, worldwide irrevocable, sub licensable license to use, reproduce, adapt, publish, translate and distribute it in any and all media.\n" +
                "\n" +
                "Your Content must be your own and must not be invading any third-party's rights. Dokkan developer reserves the right to remove any of Your Content from this application at any time without notice."
        );

        mB.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog m = mB.create();
        mB.show();
    }




}