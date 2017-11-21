package com.example.jonnel.parkhere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    ListView reviews;
    ArrayList<String> array;
    FirebaseUser user;
    String uid;
    DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        reviews = findViewById(R.id.ListviewReview);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        array = new ArrayList<>();

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot snapshot){
        array.clear();
        Iterable<DataSnapshot> children = snapshot.child("User Id: " + uid).child("User Information").child("Ratings").child("Reviews").getChildren();
        for(DataSnapshot ds : children){
            array.add(ds.getValue(String.class));
        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
        reviews.setAdapter(adapter);
    }
}
