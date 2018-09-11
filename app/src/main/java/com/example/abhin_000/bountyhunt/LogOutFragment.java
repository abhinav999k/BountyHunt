package com.example.abhin_000.bountyhunt;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogOutFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private TextView textViewDEmail;
    private FirebaseUser firebaseUser;
    private Button buttonLogOut;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.log_out_fragment,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        textViewDEmail = view.findViewById(R.id.textViewDEmail);
        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getActivity(),MainActivity.class));
        }
        firebaseUser = firebaseAuth.getCurrentUser();
        textViewDEmail.setText("welcome " + firebaseUser.getEmail() );
        buttonLogOut = view.findViewById(R.id.buttonLogOut);

        view.findViewById(R.id.buttonLogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }
}
