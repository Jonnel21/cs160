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
        import java.util.HashMap;
        import java.util.Map;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import java.util.UUID;


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
                Map<String, Object> userListings = new HashMap<>();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                address = address_text.getText().toString();
                price = Double.parseDouble(price_text.getText().toString());
                //spot = new PSpot(price, null, address, null, null);

                PSpot spot = new PSpot(price,address,getIntent().getExtras().getString("beginDate"),getIntent().getExtras().getString("endDate"),getIntent().getExtras().getString("startTime"),getIntent().getExtras().getString("endTime"));
                //PSpot test = new PSpot(price,address,bDate,eDate,sTime,eTime);

                if(address_text != null && price_text != null) {
                    Context context = getApplicationContext();
                    CharSequence message = "You created a new parking spot"
                            + " " + "at" + " " + address + " " + "for" + " " + "$" + price + "dollars";
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, message, duration).show();

                    System.out.println("You have successfully created a new parking spot!");
                    System.out.println("Price:" + " " + "$" + price);
                    System.out.println("Address:" + " " + address);
                }
                else
                {
                    System.out.println("Error: Please fill in all values");
                }

                //userListings.put("address:", address);
                //userListings.put("price:", price);
                //userListings.put("startTime:", getIntent().getExtras().getString("startTime"));
                //userListings.put("endtime:", getIntent().getExtras().getString("endTime"));
                //userListings.put("startDate:", getIntent().getExtras().getString("beginDate"));
                //userListings.put("endDate:", getIntent().getExtras().getString("endDate"));
                //userListings.put("e-mail:",user.getEmail());
                //userListings.put("userID:",uid);
                //userListings.put("parking spot", spot);
                dataRef.child(uid).child("Parking Spot Listing:" + UUID.randomUUID().toString()).setValue(spot);
               //dataRef.child(UUID.randomUUID().toString()).updateChildren(userListings);





            }
        });
    }
}

