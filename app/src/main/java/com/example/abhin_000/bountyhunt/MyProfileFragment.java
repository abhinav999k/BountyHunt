package com.example.abhin_000.bountyhunt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyProfileFragment extends Fragment {
    private ImageView imageViewEditName;
    private Button buttonSave;
    private EditText editTextUserName;
    private DatabaseReference databaseUsers;
    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_profile_fragment,null);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewEditName = view.findViewById(R.id.imageViewEditName);
        buttonSave = view.findViewById(R.id.buttonSave);
        editTextUserName = view.findViewById(R.id.editTextUserName);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
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
    UserInformation userInformation;
    private void saveUserInformation(){
        String name = editTextUserName.getText().toString().trim();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(!TextUtils.isEmpty(name)){
            String id = databaseUsers.push().getKey();
            userInformation = new UserInformation(id,name);
            databaseUsers.child(firebaseUser.getUid()).setValue(userInformation);
            Toast.makeText(getActivity(),"Information saved...", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getActivity(),"Information save failed...", Toast.LENGTH_SHORT).show();


       // UserInformation userInformation = new UserInformation(name);
        //FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        //databaseUsers.child(firebaseUser.getUid()).setValue(userInformation);
        ////Toast.makeText(getActivity(),"Information saved...", Toast.LENGTH_SHORT).show();
    }
}
