package com.example.abhin_000.bountyhunt;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUP_activity extends AppCompatActivity {
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editTextPassword;
    private RadioButton radioButtonAgreement;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity);
        progressDialog = new ProgressDialog(this);
        buttonRegister = findViewById(R.id.buttonRegister);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        radioButtonAgreement = findViewById(R.id.radioButtonAgreement);
        firebaseAuth = FirebaseAuth.getInstance();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    void registerUser(){
        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        Boolean checked = radioButtonAgreement.isChecked();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Name can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Password can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!checked){
            Toast.makeText(this,"You must agree to the term and condition ",Toast.LENGTH_SHORT).show();
            return;
        }

          progressDialog.setMessage("Registering User...");
          progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(SignUP_activity.this,"User registered successfully ",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignUP_activity.this,"User registeration failed ",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}
