package com.example.abhin_000.bountyhunt;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GlobalQuestionFragment extends Fragment{

    //private ImageView imageViewEditName;
    //private Button buttonSave;
    //private EditText editTextUserName;
    private DatabaseReference databaseUsers;
    private FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    QuestionAdapter questionAdapter;
    List<QuestionFormat> questionFormats;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.global_question_fragment,null);
    }



    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("User Questions");
        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        questionFormats = new ArrayList<>();

        /*QuestionFormat a1 = new QuestionFormat(58,"Abhinav Kumar","When the hell this project is going to complete?Damn!");
        QuestionFormat a2 = new QuestionFormat(59,"Abhinav Kumar","When the hell this project is going to complete?Damn!");
        QuestionFormat a3 = new QuestionFormat(58,"Abhinav Kumar","When the hell this project is going to complete?Damn!");
        QuestionFormat a4 = new QuestionFormat(58,"Abhinav Kumar","When the hell this project is going to complete?Damn!");
        QuestionFormat a5 = new QuestionFormat(58,"Abhinav Kumar","When the hell this project is going to complete?Damn!");
        QuestionFormat a6 = new QuestionFormat(58,"Abhinav Kumar","When the hell this project is going to complete?Damn!");
        QuestionFormat a7 = new QuestionFormat(58,"Abhinav Kumar","When the hell this project is going to complete?Damn!");
        QuestionFormat a8 = new QuestionFormat(58,"Abhinav Kumar","When the hell this project is going to complete?Damn!");
        QuestionFormat a9 = new QuestionFormat(58,"Abhinav Kumar","When the hell this project is going to complete?Damn!");
        QuestionFormat a10 = new QuestionFormat(58,"Abhinav Kumar","When the hell this project is going to complete?Damn!");
        QuestionFormat a11 = new QuestionFormat(58,"Abhinav Kumar","When the hell this project is going to complete?Damn!");
        QuestionFormat a12 = new QuestionFormat(58,"Abhinav Kumar","When the hell this project is going to complete?Damn!");*/







        /*imageViewEditName = view.findViewById(R.id.imageViewEditName);
        buttonSave = view.findViewById(R.id.buttonSave);
        editTextUserName = view.findViewById(R.id.editTextUserName);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSave.setVisibility(View.GONE);
                saveUserInformation();
            }
        });
        imageViewEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEditing();
            }
        });


    }
    private void enableEditing(){
        buttonSave.setVisibility(View.VISIBLE);
        editTextUserName.setEnabled(true);
    }

    private void saveUserInformation(){
        String name = editTextUserName.getText().toString().trim();
        UserInformation userInformation = new UserInformation(name);
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference.child(firebaseUser.getUid()).setValue(userInformation);
        Toast.makeText(getActivity(),"Information saved...", Toast.LENGTH_SHORT).show();
    }*/
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionFormats.clear();
                for(DataSnapshot userQuestions: dataSnapshot.getChildren()){
                    QuestionFormat question = userQuestions.getValue(QuestionFormat.class);
                    questionFormats.add(question);
                }

               // ArrayList list = new ArrayList(Arrays.asList(a));

                questionAdapter = new QuestionAdapter(questionFormats,getActivity());
                recyclerView.setAdapter(questionAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
