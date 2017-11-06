package com.example.jonnel.parkhere;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class createListing_Activity extends AppCompatActivity {
    Button createListing;
    EditText address_text;
    EditText price_text;
    PSpot spot;
    String address;
    double price;
    private TextView startTime;
    private TextView endTime;
    private TextView startDate;
    private TextView endDate;
    private static String key = " ";
    private static String childKey="";

    DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing_);
        final String sTime;
        final String eTime;
        final String bDate;
        final String eDate;

        startTime = (TextView) findViewById(R.id.startTime);
        startTime.setText(getIntent().getExtras().getString("startTime"));

        endTime = (TextView) findViewById(R.id.endTime);
        endTime.setText( getIntent().getExtras().getString("endTime"));

        startDate = (TextView) findViewById(R.id.startDate);
        startDate.setText( getIntent().getExtras().getString("beginDate"));

        endDate = (TextView) findViewById(R.id.endDate);
        endDate.setText(getIntent().getExtras().getString("endDate"));



        createListing = findViewById(R.id.submit);
        address_text = findViewById(R.id.address);
        price_text =  findViewById(R.id.price);


        createListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                address = address_text.getText().toString();
                price = Double.parseDouble(price_text.getText().toString());

                PSpot spot = new PSpot(price,address,getIntent().getExtras().getString("beginDate"),getIntent().getExtras().getString("endDate"),getIntent().getExtras().getString("startTime"),getIntent().getExtras().getString("endTime"));

                if(address_text != null && price_text != null) {
                    toastMessage("You have created Parking Spot!");

                }
                else
                {
                   toastMessage("Error: Please fill in all values");
                }

                DatabaseReference userRef = dataRef.child("User Id: " + uid);
                DatabaseReference listingRef = userRef.push();
                listingRef.setValue(spot);
                key = listingRef.getKey();

            }
        });
    }

    public static String getKey(){
        return key;
}

    // helper method to create toasts
    private void toastMessage(String str){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, str, duration).show();

    }



    public static String getChildKey(){return childKey;}
}
