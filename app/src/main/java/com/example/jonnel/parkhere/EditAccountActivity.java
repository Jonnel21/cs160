package com.example.jonnel.parkhere;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URI;

public class EditAccountActivity extends AppCompatActivity {
    private Button choosePicture;
    private Button confirmAccountChanges;
    private EditText changeEmail;
    private EditText changePassword;
    private String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        choosePicture= findViewById(R.id.ChoosePicture);
        confirmAccountChanges=findViewById(R.id.ConfirmAccInfo);
        changeEmail= findViewById(R.id.editAccEmail);
        changePassword=findViewById(R.id.editAccountPass);

        confirmAccountChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAccount();
            }
        });
        choosePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseGooglePicture();
            }
        });
    }

    private void chooseGooglePicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }
    public static final int PICK_IMAGE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Intent profile= new Intent(this,ProfileActivity.class);
        if (requestCode == PICK_IMAGE&&resultCode== Activity.RESULT_OK) {
            Uri selected= data.getData();
            DatabaseReference dref = FirebaseDatabase.getInstance().getReference();
            dref=dref.child("User Id: " + uid).child("User Information");
            dref.child("photoURI").setValue(selected.toString());
        }
        startActivity(profile);
        finish();
    }

    private void updateAccount()
    {
        Intent profileActivity = new Intent(this,ProfileActivity.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String email=changeEmail.getText().toString();
        final String password =changePassword.getText().toString();


        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !checkValidPassword(password)) {
            changePassword.setError(getString(R.string.error_invalid_password));
            focusView = changePassword;
            cancel = true;
        }



        if (!checkValidEmail(email)) {
            changeEmail.setError(getString(R.string.error_invalid_email));
            focusView = changeEmail;
            cancel = true;
        }

        if(cancel)
        {
            focusView.requestFocus();
        }
        else{
            user.updateEmail(email);
            user.updatePassword(password);
            Context context = getApplicationContext();
            CharSequence failure = "You have changed your account information.";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, failure, duration).show();
            startActivity(profileActivity);
            finish();
        }


    }
    private boolean checkValidPassword(String pass)
    {
        return pass.length()>4;
    }
    private boolean checkValidEmail(String email)
    {
        return email.contains("@")&&email.contains(".");
    }



}
