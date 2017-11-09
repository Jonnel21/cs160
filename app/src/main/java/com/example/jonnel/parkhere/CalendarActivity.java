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

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instance = this;
        setContentView(R.layout.activity_calendar);



    }

    public void toSecondTimer(View view){
        final DatePicker datePicker1 = (DatePicker) findViewById(R.id.datePicker1);
        int year = datePicker1.getYear();
        int month = datePicker1.getMonth() + 1;
        int day = datePicker1.getDayOfMonth();



        String date = month + "/" + day + "/" + year;


        Intent cal2 = new Intent(CalendarActivity.this, EndDate.class);
        //toEndTime.putExtra("startYear", datePicker1.getYear());
        //toEndTime.putExtra("startMonth", datePicker1.getMonth());
        //toEndTime.putExtra("startDay", datePicker1.getDayOfMonth());
        //toEndTime.putExtra("hour",getIntent().getStringExtra("startHour"));
        //toEndTime.putExtra("minute",getIntent().getStringExtra("startMinute"));
        //Bundle dateBundle1 = getIntent().getExtras();


        String startTime="";
        String endTime="";
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            startTime = bundle.getString("startTime");
            endTime = bundle.getString("endTime");
        }



        cal2.putExtra("startTime",startTime);
        cal2.putExtra("endTime",endTime);
        cal2.putExtra("beginDate",date );
        startActivity(cal2);


    }


}