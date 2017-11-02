package com.example.jonnel.parkhere;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {
    private Button signOutButton;
    private TextView helloUserText;
    private Button createListing;
    private Button profileButton;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        auth=FirebaseAuth.getInstance();
        signOutButton = (Button) findViewById(R.id.signoutButton);
        createListing = (Button) findViewById(R.id.createButton);
       // profileButton = (Button) findViewById(R.id.profileButton);
        helloUserText = (TextView) findViewById(R.id.emailText);
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if( user == null)
                {
                    startActivity(new Intent(UserActivity.this,LoginActivity.class));
                    finish();
                }
                else
                {

                    helloUserText.setText(String.format("%s %s", "User: ", user.getEmail()));
                }
            }
        };
/*
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile();
                //String Test = "hehexd";
                //DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();
                //dataRef.child(Test).setValue(0);
            }
        });
*/
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutButton();
            }
        });
        createListing.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCreateListing();
            }
        }));


    }


/* profile will be added later
    private void profile()
    {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
*/
    private void startCreateListing()
    {
        Intent intent = new Intent(this, TimeActivity.class);
        startActivity(intent);
    }

    private void signOutButton(){
        auth.signOut();
    }

    protected void onResume()
    {
        super.onResume();
    }

    public void onStart()
    {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    public void onStop()
    {
        super.onStop();
        if(authListener!=null)
        {
            auth.removeAuthStateListener(authListener);
        }
    }
}
