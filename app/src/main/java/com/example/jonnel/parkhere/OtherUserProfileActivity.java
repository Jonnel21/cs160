package com.example.jonnel.parkhere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OtherUserProfileActivity extends AppCompatActivity{
    private TextView fName;
    private TextView lName;
    private TextView address;
    private TextView state;
    private TextView city;
    private TextView zip;
    private Button review;
    private EditText reviewText;
    private String otherUserId;
    DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_profile);
        otherUserId = getIntent().getStringExtra("id");

        reviewText = findViewById(R.id.ReviewText);
        review = findViewById(R.id.submitReview);
        fName = findViewById(R.id.profName);
        lName = findViewById(R.id.profLastName);
        address = findViewById(R.id.profAddress);
        state = findViewById(R.id.profState);
        city = findViewById(R.id.profCity);
        zip = findViewById(R.id.profZip);

        review.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String str = reviewText.getText().toString();
                // TODO: User should be limited to one review per user to avoid spam from other users. need to fix.
                dataRef.child("User Id: " + otherUserId).child("User Information").child("Ratings").child("Reviews").push().setValue(str);
                reviewText.setText(" ");
                CharSequence thanks = "Thank you! Your review has been submitted!";
                Toast.makeText(getApplicationContext(), thanks, Toast.LENGTH_LONG).show();
            }
        });

        DatabaseReference dref = dataRef.child("User Id: " + otherUserId).child("User Information");
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                address.setText(String.format("%s %s", "Address: ", dataSnapshot.child("Address").getValue()));
                fName.setText(String.format("%s %s", "First Name: ", dataSnapshot.child("First name").getValue()));
                lName.setText(String.format("%s %s", "Last Name: ", dataSnapshot.child("Last name").getValue()));
                state.setText(String.format("%s %s", "State: ", dataSnapshot.child("State").getValue()));
                city.setText(String.format("%s %s", "City: ", dataSnapshot.child("City").getValue()));
                zip.setText(String.format("%s %s", "Zip: ", dataSnapshot.child("Zip").getValue()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
