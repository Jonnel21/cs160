package com.example.jonnel.parkhere;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.UUID;

public class createListing_Activity extends AppCompatActivity {
    Button createListing;
    EditText address_text;
    EditText price_text;
    String address;
    double price;
    private static String key = " ";
    private String sTime;
    private String eTime;
    private String sDate;
    private String eDate;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_create_listing_);

        Bundle pastInstance = getIntent().getExtras();
        if(pastInstance !=null) {
            TextView startTime = (TextView) findViewById(R.id.startTime);
            startTime.setText(pastInstance.getString("startTime"));
            sTime= pastInstance.getString("startTime");

            TextView endTime = (TextView) findViewById(R.id.endTime);
            endTime.setText(pastInstance.getString("endTime"));
            eTime= pastInstance.getString("endTime");

            TextView startDate = (TextView) findViewById(R.id.startDate);
            startDate.setText(pastInstance.getString("beginDate"));
            sDate= pastInstance.getString("beginDate");

            TextView endDate = (TextView) findViewById(R.id.endDate);
            endDate.setText(pastInstance.getString("endDate"));
            eDate= pastInstance.getString("endDate");
        }
        createListing = findViewById(R.id.submit);
        address_text = findViewById(R.id.address);
        price_text = findViewById(R.id.price);

        String add = address_text.getText().toString();
        String pri = price_text.getText().toString();

        if ( add.trim().equals("") || pri.trim().equals("")){
            price_text.setError(getString(R.string.error_field_required));
            address_text.setError(getString(R.string.error_field_required));
        }
        if(!add.trim().equals("") && !pri.trim().equals("")){
            price_text.setError(null);
            address_text.setError(null);
            //createListing.setEnabled(true);
        }
        createListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> userListings = new HashMap<>();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid="";
                if(user!=null) {
                     uid = user.getUid();
                }
                //THIS IS NOT A FIX FOR HAVING PRICE NULL AND IT DOES NOT EVEN HANDLE ADDRESS NULL PLZ FIX
                if(!price_text.getText().toString().equals("")) {
                    price = Double.parseDouble(price_text.getText().toString());
                }
                else
                {
                    price = 0;
                }


                address = address_text.getText().toString();




                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                formatter.format(price);



                PSpot spot = new PSpot(price, address, sDate, eDate, sTime, eTime, true, uid, null);

               // if (address_text != null && price_text != null) {
                if(validPrice(price) && validAddress(address) && PriceBound(price)){
                    Context context = getApplicationContext();
                    CharSequence message = "You created a new parking spot"
                            + " " + "at" + " " + address + " " + "for" + " " + formatter.format(price) + " dollars";
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, message, duration).show();

                    System.out.println("You have successfully created a new parking spot!");
                    System.out.println("Price:" + " " + "$" + formatter.format(price));
                    System.out.println("Address:" + " " + address);
                } else {

                    Context context = getApplicationContext();
                    CharSequence message = "Invalid parking spot listing";
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, message, duration).show();
                }


                DatabaseReference userRef = dataRef.child("User Id: " + uid);
                DatabaseReference listingRef = userRef.push();
                listingRef.setValue(spot);
                key = listingRef.getKey();


                menu();

            }
        });
        Intent data = new Intent("name").putExtra("key", key);
        LocalBroadcastManager.getInstance(this).sendBroadcast(data);



    }
    public static String getKey(){
        return key;
    }
    public boolean validPrice(double price){
        if(price <= 0){
            return false;
        }
        return true;
    }
    public boolean validAddress(String add){
        if(add.length() > 6 && !add.isEmpty()){
            return true;
        }
        return false;

    }
    public boolean PriceBound(double price) {
        if( price > 10000 ) {
         return false;
        }
        return true;
    }
    private void menu(){
        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
}