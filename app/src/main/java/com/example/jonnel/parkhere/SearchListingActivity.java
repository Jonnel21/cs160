package com.example.jonnel.parkhere;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.ContactsContract;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class SearchListingActivity extends AppCompatActivity {
    ListView mListView;
    FirebaseUser user;
    String uid;
    String key;
    ArrayList<String> array;
    ArrayList<String> mUsers;
    ArrayList<String> lKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_listing);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        mListView = findViewById(R.id.Listview);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        array = new ArrayList<>();
        mUsers = new ArrayList<>();
        lKeys = new ArrayList<>();
        key = createListing_Activity.getKey(); // gets the listing hash key

        key = createListing_Activity.getKey();
        System.out.println("SearchListingClass: " + key);
        System.out.println("User Id: " + uid);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot); // helper method to iterate thorough user's children
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showData(final DataSnapshot dataSnapshot){
        array.clear();
        final ArrayList<DataSnapshot> bookings = new ArrayList();
        // Store users key in array list
        for(DataSnapshot d: dataSnapshot.getChildren()) {
            //PSpot spot = d.getValue(PSpot.class);
            mUsers.add(String.valueOf(d.getKey()));
        }
            int i = 0;
            while(i < mUsers.size()) {
                DataSnapshot userId = dataSnapshot.child(mUsers.get(i));
                Iterable<DataSnapshot> listings = userId.getChildren();


                for (DataSnapshot ds : listings) {
                    PSpot spot = ds.getValue(PSpot.class);
                    if(spot.availablity == true && !spot.owner.equals(uid)) {
                        array.add(spot.ownerToString()); // adds user listing to list view
                        bookings.add(ds);
                    }

                }
                i++;
            }

             final   ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
                mListView.setAdapter(adapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                        AlertDialog.Builder adb=new AlertDialog.Builder(SearchListingActivity.this);
                        adb.setTitle("Book Parking Spot?");
                        adb.setMessage("Are you sure you want to book this parking spot");
                        final DataSnapshot update = bookings.get(i);
                        adb.setNegativeButton("Cancel", null);
                        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                update.child("availablity").getRef().setValue(false);
                                update.child("reserve").getRef().setValue(uid);
                                adapter.notifyDataSetChanged();

                            }});
                        adb.show();
                    }
                });
    }

}

