package com.example.jonnel.parkhere;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListingOverviewActivity extends AppCompatActivity {
    TextView parkingDetails;
    TextView details;
    TextView review;
    Button book;
    FirebaseUser user;
    String uid;
    DataSnapshot dataSnapshot;
    int toCount = 0;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_overview);

        final String owner = getIntent().getStringExtra("Owner");
        final String listingHash = getIntent().getStringExtra("ListingHash");
        final String bookings = getIntent().getStringExtra("DataSnapshot");

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        parkingDetails = findViewById(R.id.textView2);
        details = findViewById(R.id.textView7);
        review = findViewById(R.id.textView8);
        book = findViewById(R.id.button_book);


        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WritingActivity.class);
                intent.putExtra("owner", owner);
                intent.putExtra("listingHash", listingHash);
                startActivity(intent);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ListingOverviewActivity.this);
                builder.setMessage("Are you sure you would like to Book now?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String counter = counterParser(bookings);
                                myRef.child("User Id: " + ownerParser(bookings)).child(keyParser(bookings)).child("availability").setValue(false);
                                myRef.child("User Id: " + ownerParser(bookings)).child(keyParser(bookings)).child("reserve").setValue(uid);
                                toCount = Integer.parseInt(counter);
                                myRef.child("User Id: " + ownerParser(bookings)).child(keyParser(bookings)).child("counter").getRef().setValue(toCount + 1);

                            }
                        })
                        .setNegativeButton("Cancel", null);
                builder.show();
            }
        });
        String booking = getIntent().getStringExtra("Booking");
        details.setText(booking);
    }


    private String ownerParser(String str){
        int start = str.indexOf("owner=");
        int end = str.lastIndexOf(",");

        return str.substring(start + 6, end);
    }

    private String keyParser(String str){
        int start = str.indexOf("key");
        int end = str.indexOf(",");

        return str.substring(start + 6, end);
    }

    private String counterParser(String str){
        int start = str.indexOf("counter=");
        int end = str.lastIndexOf("}");

        return str.substring(start + 8, end - 2);
    }
}
