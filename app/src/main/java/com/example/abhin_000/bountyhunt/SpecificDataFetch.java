package com.example.abhin_000.bountyhunt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SpecificDataFetch {
  /*  public String nameValue;
    public SpecificDataFetch specificDataFetch;



    public void fetch_name(final String id, final SpecificDataFetch sdf) {
        specificDataFetch = sdf;
        DatabaseReference databaseUsers;
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        Query queryRef = databaseUsers;
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserInformation userInformation;
                String name;

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.getKey().equals(id)) {

                        userInformation = data.getValue(UserInformation.class);
                        //score = userInformation.getScore();
                        name = userInformation.getName();

                        specificDataFetch.nameValue = name;


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



    }*/


}
