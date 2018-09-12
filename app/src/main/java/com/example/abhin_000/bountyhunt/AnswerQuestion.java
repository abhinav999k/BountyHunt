package com.example.abhin_000.bountyhunt;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AnswerQuestion extends AppCompatActivity {
    public String questionID;
    public EditText answerBox;
    public Button answerSubmit;
    private DatabaseReference databaseUsers,databaseChild;
    private FirebaseAuth firebaseAuth;
    public QuestionFormat questionFormat;
    public QuestionFormat qf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        answerBox = findViewById(R.id.answer_box);
        getIncomingIntent();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("User Questions");
        answerSubmit = findViewById(R.id.submit_answer);
        answerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch_update();
            }
        });
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("QuestionID"));
        questionID = getIntent().getStringExtra("QuestionID");
    }

    private void fetch_update(){

        Query queryRef = databaseUsers;
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int score;
                String name;
                String question;
                String QID;
                String answer;
                String askedID;
                String answeredID;
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.getKey().equals(questionID)) {
                        questionFormat = data.getValue(QuestionFormat.class);
                        score = questionFormat.getScore();
                        question = questionFormat.getQuestion();
                        QID = questionFormat.getQID();
                        answer = answerBox.getText().toString().trim();
                        askedID = questionFormat.getAskedID();
                        answeredID = firebaseUser.getUid();
                        qf = new QuestionFormat(score,question,QID,answer,askedID,answeredID);
                        databaseChild = FirebaseDatabase.getInstance().getReference("User Questions").child(questionID);
                        databaseChild.setValue(qf);
                        Toast.makeText(AnswerQuestion.this,"Your answer is posted", Toast.LENGTH_SHORT).show();
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
