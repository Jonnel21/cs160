package com.example.jonnel.parkhere;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserInfoActivity extends AppCompatActivity {

    private AutoCompleteTextView AddressView;
    private AutoCompleteTextView CityView;
    private AutoCompleteTextView StateView;
    private AutoCompleteTextView ZipView;
    private FirebaseAuth mAuth;
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
      AddressView = (AutoCompleteTextView) findViewById(R.id.address);
        CityView = (AutoCompleteTextView) findViewById(R.id.city);
        StateView = (AutoCompleteTextView) findViewById(R.id.state);
        ZipView= (AutoCompleteTextView) findViewById(R.id.zip);
        Button finished = (Button) findViewById(R.id.finish);
        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address();
            }
        });
    }
    public void address() {
        final DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();
        final Intent welcome= new Intent(this, WelcomeActivity.class);
        Bundle bundle= getIntent().getExtras();
        String first = "";
        String last = "";
        if(bundle!=null) {

            first = bundle.getString("firstname");
            last = bundle.getString("lastname");
        }

        final String address = AddressView.getText().toString();
        final String city = CityView.getText().toString();
        final String state = StateView.getText().toString();
        final String zip= ZipView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(address) ) {
           AddressView.setError(getString(R.string.error_field_required));
            focusView = AddressView;
            cancel = true;
        }


        if (TextUtils.isEmpty(city)) {
            CityView.setError(getString(R.string.error_field_required));
            focusView = CityView;
            cancel = true;
        }
        if(TextUtils.isEmpty(state)){
          StateView.setError(getString(R.string.error_field_required));
            focusView = StateView;
            cancel = true;
        }

        if(TextUtils.isEmpty(zip)){
           ZipView.setError(getString(R.string.error_field_required));
            focusView = ZipView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = "";
        if(user!=null) {
            uid = user.getUid();
        }
            DatabaseReference userRef = dataRef.child("User Id: " + uid).child("User Information");

            //userRef.child("UserInformation");

            userRef.child("First name" ).setValue(first);
            userRef.child("Last name" ).setValue(last);
            userRef.child("Address").setValue( address);
            userRef.child("City").setValue(city);
            userRef.child("State").setValue(state);
            userRef.child("Zip").setValue(zip);
            userRef.child("photoURI").setValue(null);

        Context context = getApplicationContext();
            CharSequence failure = "You have completed the setup. Please log in";
            int duration = Toast.LENGTH_SHORT;
             Toast.makeText(context, failure, duration).show();

            startActivity(welcome);
            finish();

       }
    }

}
