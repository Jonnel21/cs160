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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    ListView mListView;
    FirebaseUser user;
    String uid;
    String key;
    //String s;
    ArrayList<String> array;
    final ArrayList<String> keyList = new ArrayList<>();
    final ArrayList<String> items = new ArrayList<>();
    DatabaseReference myRef;

    private static final String TAG = "DeleteActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_listing);

        myRef = FirebaseDatabase.getInstance().getReference();
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

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void showData(final DataSnapshot dataSnapshot) {
        array.clear();
        final ArrayList<DataSnapshot> deletion = new ArrayList();
        DataSnapshot userId = dataSnapshot.child("User Id: " + uid);
        final Iterable<DataSnapshot> listings = userId.getChildren();
        for (DataSnapshot ds : listings) {
            PSpot spot = ds.getValue(PSpot.class);
            array.add(spot.toString());
            deletion.add(ds);
        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(DeleteActivity.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to cancel your spot?");
                final DataSnapshot remove =deletion.get(position);
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        remove.getRef().removeValue();
                        adapter.notifyDataSetChanged();

                    }});
                adb.show();

            }
        });


    }




}