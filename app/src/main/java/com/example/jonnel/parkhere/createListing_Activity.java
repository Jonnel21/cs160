package com.example.jonnel.parkhere;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class createListing_Activity extends AppCompatActivity {
    Button createListing;
    EditText address_text;
    EditText price_text;
    PSpot spot;
    String address;
    double price;
    private TextView time;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing_);
        time = (TextView) findViewById(R.id.timeText);
        time.setText(getIntent().getExtras().getString("time"));
        date = (TextView) findViewById(R.id.myDate);
        date.setText(getIntent().getExtras().getString("date"));

        createListing = findViewById(R.id.submit);
        address_text = findViewById(R.id.address);
        price_text =  findViewById(R.id.price);

        createListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address = address_text.getText().toString();
                price = Double.parseDouble(price_text.getText().toString());
                spot = new PSpot(price, null, address, null, null);
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

            }
        });
    }
}