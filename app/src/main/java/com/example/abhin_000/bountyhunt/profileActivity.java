package com.example.abhin_000.bountyhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class profileActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    //private DatabaseReference databaseReference;
    private TextView textViewDEmail;
    private Button buttonLogOut;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        textViewDEmail = findViewById(R.id.textViewDEmail);
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
        firebaseUser = firebaseAuth.getCurrentUser();
        textViewDEmail.setText("welcome " + firebaseUser.getEmail() );
        buttonLogOut = findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log out process
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(profileActivity.this, MainActivity.class));
            }
        });
    }
}
