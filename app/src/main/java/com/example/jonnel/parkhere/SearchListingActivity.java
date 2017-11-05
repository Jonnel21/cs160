package com.example.jonnel.parkhere;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
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

public class SearchListingActivity extends AppCompatActivity {
    ListView mListView;
    FirebaseUser user;
    String uid;
    String key;
    //String s;
    ArrayList<String> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_listing);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        mListView = findViewById(R.id.Listview);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        array = new ArrayList<>();

        key = createListing_Activity.getKey();
        System.out.println("SearchListingClass: " + key);
        System.out.println("User Id: " + uid);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);

                //Testing Purposes
                /*String s = dataSnapshot.child("User Id: " + uid).child(key).getValue().toString();
                System.out.println(s);
                ArrayList<String> array = new ArrayList<>();
                array.add(s);
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
                mListView.setAdapter(adapter);*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Testing Purposes
        /*BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String key = intent.getStringExtra("key");
                System.out.println("SearchListingClass: " + key);
            }
        };
        // Testing Purposes
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter("name"));*/
        /*String[] listItems = new String[10];
        for(int i = 0; i < listItems.length; i++){
            listItems[i] = "Test String";
        }*/


    }

    private void showData(DataSnapshot dataSnapshot) {
        array.clear();
        DataSnapshot userId = dataSnapshot.child("User Id: " + uid);
        Iterable<DataSnapshot> listings = userId.getChildren();
        for (DataSnapshot ds : listings) {
            PSpot spot = ds.getValue(PSpot.class);

            array.add(spot.toString());
            //Testing Purposes
            //s = spot.toString();
            /*PSpot spot = new PSpot();
            System.out.println();
            spot.setAddress(ds.child("User Id: " + uid).child(key).getValue(PSpot.class).getAddress());
            System.out.println(spot.getAddress());*/
            //s = dataSnapshot.child("User Id: " + uid).child(key).getValue().toString();
            //array = new ArrayList<>();
            //array.add(s);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
        mListView.setAdapter(adapter);
        //Testing Purposes
            /*PSpot spot = new PSpot();
            s = dataSnapshot.child("User Id: " + uid).child(key).getValue(PSpot.class).getAddress();
            spot.setAddress(s);
            System.out.println(s);
            array = new ArrayList<>();
            array.add(s);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
            mListView.setAdapter(adapter);*/

    }


}