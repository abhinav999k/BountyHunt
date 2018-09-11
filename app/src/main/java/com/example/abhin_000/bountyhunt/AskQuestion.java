package com.example.abhin_000.bountyhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AskQuestion extends AppCompatActivity {
    public Button buttonSubmit;
    public EditText questionInput;
    private DatabaseReference databaseUsers;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        buttonSubmit = findViewById(R.id.question_submit);
        questionInput = findViewById(R.id.questionField);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("User Questions");
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String que = questionInput.getText().toString().trim();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(!TextUtils.isEmpty(que)){
                    String id = databaseUsers.push().getKey();
                    String askedUserID = firebaseUser.getUid();
                    QuestionFormat questionFormat = new QuestionFormat(id,que,askedUserID);
                    databaseUsers.child(id).setValue(questionFormat);
                    Toast.makeText(AskQuestion.this,"Question is successfully posted...", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(AskQuestion.this,"Question posting failed...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
