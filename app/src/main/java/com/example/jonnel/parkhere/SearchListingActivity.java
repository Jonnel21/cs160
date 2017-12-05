package com.example.jonnel.parkhere;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
        if(user!=null) {
            uid = user.getUid();
        }
        array = new ArrayList<>();
        mUsers = new ArrayList<>();
        lKeys = new ArrayList<>();
        key = createListing_Activity.getKey(); // gets the listing hash key

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                array.clear();
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot); // helper method to iterate thorough user's children
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }

    private void showData(final DataSnapshot dataSnapshot){
        array.clear();
        final ArrayList<DataSnapshot> bookings = new ArrayList<>();
        // Store users key in array list
        for(DataSnapshot d: dataSnapshot.getChildren()) {
            mUsers.add(String.valueOf(d.getKey()));
        }
        int i = 0;
        while(i < mUsers.size()) {
            DataSnapshot userId = dataSnapshot.child(mUsers.get(i));
            Iterable<DataSnapshot> listings = userId.getChildren();


            for (DataSnapshot ds : listings) {
                PSpot spot = ds.getValue(PSpot.class);
                if(spot.owner !=null && spot.availability == true && !spot.owner.equals(uid) && spot.address != null) {
                    array.add(spot.ownerToString());
                    bookings.add(ds);
                }

            }
            i++;
        }

        final   ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
        mListView.setAdapter(adapter);
        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchListingActivity.this);
                builder.setMessage("Would you like to Book now? or Visit User Profile")
                        .setCancelable(false)
                        .setPositiveButton("Book now", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                AlertDialog.Builder adb=new AlertDialog.Builder(SearchListingActivity.this);
                                adb.setTitle("Book Parking Spot?");
                                adb.setMessage("Are you sure you want to book this parking spot");
                                final DataSnapshot update = bookings.get(i);
                                adb.setNegativeButton("Cancel", null);
                                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        update.child("availability").getRef().setValue(false);
                                        update.child("reserve").getRef().setValue(uid);
                                        int tocount = 0;
                                        tocount = Integer.parseInt(update.child("counter").getValue().toString());
                                        update.child("counter").getRef().setValue(tocount + 1);
                                        adapter.notifyDataSetChanged();

                                    }});
                                adb.show();
                            }

                        })

                        .setNegativeButton("Go to User Profile", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                String ownerId = getOwnerParser(array.get(i));
                                Intent otherUser = new Intent(getApplicationContext(), OtherUserProfileActivity.class);
                                otherUser.putExtra("id", ownerId);
                                startActivityForResult(otherUser, 0);
                            }
                        });
                builder.show();

            }
        });*/

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                String listingHash = getListingHashParser(array.get(i));
                System.out.println(listingHash);
                System.out.println(array.get(i));
                String listings = bookings.get(i).getValue().toString();
                System.out.println(listings);
                Intent intent = new Intent(getApplicationContext(), ListingOverviewActivity.class);
                intent.putExtra("Booking", listings);
                intent.putExtra("Owner", getOwnerParser(array.get(i)));
                intent.putExtra("ListingHash", getListingHashParser(array.get(i)));
                intent.putExtra("DataSnapshot", bookings.get(i).toString());
                System.out.println("Printing from SearchListing: " + bookings.get(i).toString());

                startActivity(intent);
            }
        });
    }

    /**
     * helper method to parse string of owner to
     * retrieve id of other users
     * @param str
     * @return
     */
    private String getOwnerParser(String str){
        int indexOfColon = str.indexOf(":");
        return str.substring(indexOfColon + 1 , 47); // TODO: hard coded end index. need to fix.
    }

    /**
     * helper method to parse string of Listing hash
     * @param str
     * @return
     */
    private String getListingHashParser(String str){
        int index = str.indexOf("=");
        return str.substring(index + 2, str.length());
    }

}