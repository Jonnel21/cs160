package com.example.jonnel.parkhere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.Button;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;


public class EndDate extends AppCompatActivity {
    //public static EndDate instance = null;
    final Context MyContext = this;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_date);
    }

    public void toSecondTimer(View view) {
        final DatePicker datePicker1 = (DatePicker) findViewById(R.id.datePicker1);
        int year = datePicker1.getYear();
        int month = datePicker1.getMonth() + 1;
        int day = datePicker1.getDayOfMonth();
        next = findViewById(R.id.toTime2Button);
        // String myTime = getIntent().getExtras().getString("time");

        String date = month + "/" + day + "/" + year;


        Intent cal2 = new Intent(this, createListing_Activity.class);
        //toEndTime.putExtra("startYear", datePicker1.getYear());
        //toEndTime.putExtra("startMonth", datePicker1.getMonth());
        //toEndTime.putExtra("startDay", datePicker1.getDayOfMonth());
        //toEndTime.putExtra("hour",getIntent().getStringExtra("startHour"));
        //toEndTime.putExtra("minute",getIntent().getStringExtra("startMinute"));
        //Bundle dateBundle1 = getIntent().getExtras();

        String startTime = "";
        String endTime = "";
        String startDate = "";
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            startTime = bundle.getString("startTime");
            endTime = bundle.getString("endTime");
            startDate = bundle.getString("beginDate");

            if (validEnd(startDate, date) && isValidEndDate(date) && startDate != null && date != null) {
                cal2.putExtra("startTime", startTime);
                cal2.putExtra("endTime", endTime);
                cal2.putExtra("beginDate", startDate);
                cal2.putExtra("endDate", date);
                startActivity(cal2);

            }
            else {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                CharSequence message = "Please pick a valid date";
                Toast.makeText(MyContext, message, Toast.LENGTH_SHORT).show();
               // next.setEnabled(false);
            }

        }
        startActivity(cal2);

    }





    public boolean isValidEndDate(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(input.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public boolean validEnd(String date1, String date2) {
        String[] bDate = date1.split("/");
        String mon1 = bDate[0];
        String day1 = bDate[1];
        String year1 = bDate[2];
        String[] eDate = date2.split("/");
        String mon2 = eDate[0];
        String day2 = eDate[1];
        String year2 = eDate[2];


        if(Integer.parseInt(mon1) <= Integer.parseInt(mon2) && Integer.parseInt(year1) <= Integer.parseInt(year2)&& Integer.parseInt(day1) <= Integer.parseInt(day2))
        {
            return true;
        }
        else if(Integer.parseInt(year2) > Integer.parseInt(year1))
        {
            return true;
        }


        return false;

    }


}