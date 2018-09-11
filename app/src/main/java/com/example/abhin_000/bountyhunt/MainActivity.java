package com.example.abhin_000.bountyhunt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    RelativeLayout rellay1, rellay2;
    private Button buttonSignUp,buttonLogIn;
    private ProgressDialog progressDialog;
    EditText editTextLEmail,editTextLPassword;
    private FirebaseAuth firebaseAuth;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        editTextLEmail = findViewById(R.id.editTextLEmail);
        editTextLPassword = findViewById(R.id.editTextLPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            finish(); //before starting another activity close current one
            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
        }
        buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUP_activity.class);
                startActivity(intent);
            }
        });

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextLEmail.getText().toString().trim();
                String password = editTextLPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this,"Email can't be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Password can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Logging in...");
                progressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    progressDialog.dismiss();
                                    Toast.makeText(MainActivity.this,"Login successful ",Toast.LENGTH_SHORT).show();
                                    finish(); //before starting another activity close current one
                                    startActivity(new Intent(getApplicationContext(), NavigationActivity.class));

                                }else{
                                    Toast.makeText(MainActivity.this,"Login failed ",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
    }



}
