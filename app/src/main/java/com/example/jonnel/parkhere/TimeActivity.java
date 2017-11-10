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

public class TimeActivity extends AppCompatActivity {
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Intent toStartDate = new Intent(TimeActivity.this, CalendarActivity.class);
        nextButton = (Button) findViewById(R.id.myButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFirstCalendar();

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
        Intent toEndTime = new Intent(this, EndTime.class);
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
        //String time = hour + ":" + minute;
        if(isValid(time)) {
            toEndTime.putExtra("startTime", time);
            startActivity(toEndTime);
        }
        else
        {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            CharSequence message = "Please pick a valid time";
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isValid(String temp)
    {
        String tempH = "";
        String tempM = "";
        if(temp.contains(":")) {
            String[] bDate = temp.split(":");
            tempH = bDate[0];
            tempM = bDate[1];
            if(tempH.length() <=2 && tempH.length() >0 && tempM.length() > 0){//&& tempM.matches("[0-9]+") ){
                if(tempH.matches("[0-9]+") || tempM.matches("[0-5][0-9]|[AM]|[PM]")) {
                    return true;
                }
            }
        }

        return false;
    }

}