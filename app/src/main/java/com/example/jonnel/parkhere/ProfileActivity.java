package com.example.jonnel.parkhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseUser user;
    private Button edit;
    private TextView fName;
    private TextView lName;
    private TextView addRESS;
    private TextView state;
    private TextView city;
    private TextView zip;
    private DatabaseReference dref;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);;
        edit = (Button)findViewById(R.id.editProf);
        fName = findViewById(R.id.profName);
        lName = findViewById(R.id.profLastName);
        addRESS=findViewById(R.id.profAddress);
        state = findViewById(R.id.profState);
        city = findViewById(R.id.profCity);
        zip=findViewById(R.id.profZip);
        user= FirebaseAuth.getInstance().getCurrentUser();

        uid=user.getUid();

        dref=dataRef.child("User Id: " + uid).child("User Information");
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fName.setText(String.format("%s %s", "First Name: ", dataSnapshot.child("First name").getValue()));
                lName.setText(String.format("%s %s", "Last Name: ", dataSnapshot.child("Last name").getValue()));
                addRESS.setText(String.format("%s %s", "Address: ", dataSnapshot.child("Address").getValue()));
                state.setText(String.format("%s %s", "State: ", dataSnapshot.child("State").getValue()));
                city.setText(String.format("%s %s", "City: ", dataSnapshot.child("City").getValue()));
                zip.setText(String.format("%s %s", "Zip: ", dataSnapshot.child("Zip").getValue()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfileNow();
            }
        });
    }
    private void editProfileNow(){

        Intent editProfIntent = new Intent(this,EditProfileActivity.class );
        finish();
        startActivity(editProfIntent);
    }
}
