package com.example.jonnel.parkhere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {
    private ListView deleteList;
    DatabaseReference dref;
    List<String> spotList= new ArrayList<>();
    ArrayAdapter adapter;
    PSpot temp;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);



        String uid= user.getUid();

        dref= FirebaseDatabase.getInstance().getReference();
        String key = dref.child(uid).push().getKey();
        deleteList = (ListView) findViewById(R.id.deleteList);

       // this.retrieveData();

        dref.child(key).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                spotList.clear();
                temp = dataSnapshot.getValue(PSpot.class);
                spotList.add(temp.address);
                   for(DataSnapshot items: dataSnapshot.getChildren()) {
                        temp = items.getValue(PSpot.class);
                        spotList.add(temp.address);
                        // spotList.add(Double.toString(temp.price));
                        // spotList.add(Double.toString(temp.price));
                        // spotList.add(temp.date);
                        //spotList.add(temp.time);
                    }
                adapter = new ArrayAdapter<>(DeleteActivity.this,android.R.layout.simple_list_item_1,spotList);
                deleteList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
/*
    private void retrieveData()
    {
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getList(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getList(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void getList(DataSnapshot test)
    {
        spotList.clear();
        for(DataSnapshot data : test.getChildren())
        {
            PSpot cucked= new PSpot();
            cucked.setAddress(data.getValue(PSpot.class).getAddress());

            spotList.add(cucked.getAddress());
        }
        if(spotList.size()>0)
        {
            ArrayAdapter adapter2 = new ArrayAdapter(DeleteActivity.this,android.R.layout.simple_dropdown_item_1line,spotList);
            deleteList.setAdapter(adapter2);
        }

    }
*/





}
