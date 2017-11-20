package com.example.jonnel.parkhere;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {
    private EditText fNameEdit;
    private EditText lNameEdit;
    private EditText addressEdit;
    private EditText cityEdit;
    private EditText stateEdit;
    private EditText zipEdit;
    private Button confirmEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        fNameEdit=findViewById(R.id.editFName);
        lNameEdit=findViewById(R.id.editLName);
        addressEdit= findViewById(R.id.editAddress);
        cityEdit=findViewById(R.id.editCity);
        stateEdit= findViewById(R.id.editState);
        zipEdit=findViewById(R.id.editZip);
        confirmEdit=findViewById(R.id.EditProfConfirm);
        confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfileInformation();
            }
        });
    }
    private void editProfileInformation(){
        final DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();
        final Intent profileActivity= new Intent(this, ProfileActivity.class);

        final String firstName= fNameEdit.getText().toString();
        final String lastName=lNameEdit.getText().toString();
        final String address = addressEdit.getText().toString();
        final String city = cityEdit.getText().toString();
        final String state = stateEdit.getText().toString();
        final String zip= zipEdit.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if(TextUtils.isEmpty(firstName))
        {
            fNameEdit.setError("This field is required");
            focusView=fNameEdit;
            cancel =true;
        }
        if(TextUtils.isEmpty(lastName))
        {
            lNameEdit.setError("This field is required");
            focusView=lNameEdit;
            cancel = true;
        }

        if (TextUtils.isEmpty(address) ) {
            addressEdit.setError(getString(R.string.error_field_required));
            focusView = addressEdit;
            cancel = true;
        }


        if (TextUtils.isEmpty(city)) {
            cityEdit.setError(getString(R.string.error_field_required));
            focusView = cityEdit;
            cancel = true;
        }
        if(TextUtils.isEmpty(state)){
            stateEdit.setError(getString(R.string.error_field_required));
            focusView = stateEdit;
            cancel = true;
        }

        if(TextUtils.isEmpty(zip)){
            zipEdit.setError(getString(R.string.error_field_required));
            focusView = zipEdit;
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

            userRef.child("First name" ).setValue(firstName);
            userRef.child("Last name" ).setValue(lastName);
            userRef.child("Address").setValue( address);
            userRef.child("City").setValue(city);
            userRef.child("State").setValue(state);
            userRef.child("Zip").setValue(zip);

            Context context = getApplicationContext();
            CharSequence failure = "You have changed your profile information.";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, failure, duration).show();

            startActivity(profileActivity);
            finish();

        }
    }
}
