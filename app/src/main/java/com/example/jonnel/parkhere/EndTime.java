package com.example.jonnel.parkhere;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.widget.TimePicker;
import android.widget.Button;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class EndTime extends AppCompatActivity {
    private Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_time);
        Intent toStartDate = new Intent(this, CalendarActivity.class);
        nextButton = (Button) findViewById(R.id.myButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFirstCalendar();
                // Context context = getApplicationContext();
                //CharSequence failure = "Testing.";
                //int duration = Toast.LENGTH_LONG;
                //Toast.makeText(context, failure, duration).show();

            }
        });

    }

    public void toFirstCalendar(){
        final TimePicker timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        int hour = timePicker1.getCurrentHour();
        int minute = timePicker1.getCurrentMinute();
        String am = "AM";
        String pm = "PM";
        String time;
        Intent toStartDate = new Intent(this, CalendarActivity.class);
        String min;
        if( minute < 10)
        {
            min = "0"+ minute;
        }
        else
        {
            min =""+ minute;
        }
        if( hour == 0 )
        {
            hour = hour + 12;
            time = hour + ":" + min + am;
        }
        else if( hour == 12)
        {
            time = hour + ":" + min + pm;
        }

        else if( hour > 12 && hour < 23)
        {
            hour = hour - 12;
            time = hour + ":" + min + pm;

        }
        else
        {
            time = hour + ":" + min + am;
        }
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null)
        {
            //String time = hour + ":" + minute;
            String startTime = bundle.getString("startTime");

            toStartDate.putExtra("endTime", time);
            toStartDate.putExtra("startTime", startTime);

        }







        startActivity(toStartDate);

    }

}