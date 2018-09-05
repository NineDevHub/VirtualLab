package com.nyongconnect.android.virtuallab.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nyongconnect.android.virtuallab.R;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText editTextEmail,editTextPassword;

    FirebaseAuth firebaseAuth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);

        dialog = new ProgressDialog(this);
        dialog.setMessage("logging in...");

        loginButton = findViewById(R.id.login_botton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                dialog.show();

                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                dialog.dismiss();
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Snackbar.make(view,"log in failed!",Snackbar.LENGTH_SHORT).show();
                                }

                            }
                        });


            }
        });
    }
}
