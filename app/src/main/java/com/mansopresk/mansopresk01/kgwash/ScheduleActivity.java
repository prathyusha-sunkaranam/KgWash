package com.mansopresk.mansopresk01.kgwash;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mansopresk.mansopresk01.kgwash.Navigation.AboutUsActivity;
import com.mansopresk.mansopresk01.kgwash.Navigation.NavigationMainActivity;
import com.mansopresk.mansopresk01.kgwash.Order.PriceOrderActivity;

import java.util.Calendar;
import java.util.Date;

public class ScheduleActivity extends AppCompatActivity {
    String emailid;
    TextView textViewsch;
    Spinner spinnertime,spinnerarea;
    Calendar calendar;
    private int year, month, day;
    EditText name_sch, mobile_sch, alternatenum_sch, emailregister_sch, address_sch, landmark_sch, etcalendar;
    Button Schedule, calendarbtn;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    LinearLayout calendarll;
    static final int DATE_PICKER_ID = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        name_sch = findViewById(R.id.name_register);
        mobile_sch = findViewById(R.id.mobile_register);
        alternatenum_sch = findViewById(R.id.alternate_mob_register);
        emailregister_sch = findViewById(R.id.email_register);
        address_sch = findViewById(R.id.address_register);
        landmark_sch = findViewById(R.id.landmark_register);
        spinnertime = (Spinner) findViewById(R.id.spinner1);
        spinnerarea = (Spinner) findViewById(R.id.spinner_area);
        etcalendar = findViewById(R.id.etcalender);
        calendarbtn = findViewById(R.id.calendarbtn);
        textViewsch = (TextView) findViewById(R.id.toolbartextsch);
        textViewsch.setText("Schedule Now");
        calendarll = findViewById(R.id.calendarll);
        Schedule = findViewById(R.id.schedule);

        Toolbar mToolbar = findViewById(R.id.toolbarsch);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();

                finish();
            }
        });
        sharedpreferences = getSharedPreferences("userdetails", MODE_PRIVATE);

        String uname = sharedpreferences.getString("username", null);
        String mobilenum = sharedpreferences.getString("mobile", null);
        String alernum = sharedpreferences.getString("alternatenum", null);
        String emailid = sharedpreferences.getString("emailregister", null);
        String adreesdetails = sharedpreferences.getString("address", null);
        String landmarkdetails = sharedpreferences.getString("landmark", null);

        name_sch.setText(uname);
        mobile_sch.setText(mobilenum);
        alternatenum_sch.setText(alernum);
        emailregister_sch.setText(emailid);
        address_sch.setText(adreesdetails);
        landmark_sch.setText(landmarkdetails);
    }

    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }

    public void schedule(View v) {
        if (name_sch.getText().toString().isEmpty()) {
            name_sch.requestFocus();
            name_sch.setError("please provide name");
        } else if (mobile_sch.getText().toString().isEmpty()) {
            mobile_sch.requestFocus();
            mobile_sch.setError("please provide mobile number");
        } else if (mobile_sch.length() != 10) {
            showSnackbar(mobile_sch, "Please enter 10 digit mobile number", 4000);

        } else if (emailregister_sch.getText().toString().isEmpty()) {
            emailregister_sch.requestFocus();
            emailregister_sch.setError("please provide email id");
        } else if (address_sch.getText().toString().isEmpty()) {
            address_sch.requestFocus();
            address_sch.setError("please provide address");

        } else if (etcalendar.getText().toString().isEmpty()) {
            showSnackbar(etcalendar, "Please select the date", 4000);
        } else if (spinnertime.getSelectedItemPosition() == 0) {
            showSnackbar(spinnertime, "Please select time slot", 4000);
        }else if (spinnerarea.getSelectedItemPosition() == 0) {
            showSnackbar(spinnerarea, "Please select area", 4000);
        } else {
            Intent i = new Intent(ScheduleActivity.this, PriceOrderActivity.class);
            editor = getSharedPreferences("userdetails", MODE_PRIVATE).edit();
            String custlandmark = landmark_sch.getText().toString();
            String custaddress = address_sch.getText().toString();
            String custemail = emailregister_sch.getText().toString();
            String custno = mobile_sch.getText().toString();
            String custdate = etcalendar.getText().toString();
            String custname = name_sch.getText().toString();

            editor.putString("custname", custname);
            editor.putString("userdate", custdate);
            editor.putString("usermno", custno);
            editor.putString("useremail", custemail);
            editor.putString("useraddress", custaddress);
            editor.putString("userlandmark", custlandmark);
            editor.putString("usertime", spinnertime.getSelectedItem().toString());
          //  editor.putString("userarea", spinnerarea.getSelectedItem().toString());
            editor.commit();
            startActivity(i);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(ScheduleActivity.this, NavigationMainActivity.class);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
    public void calender(View v) {
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        showDialog(DATE_PICKER_ID);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, datePickerListener, year, month, day);
                calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 0); // Add 0 days to Calendar
                Date newDate = calendar.getTime();
                datePickerDialog.getDatePicker().setMinDate(newDate.getTime() - (newDate.getTime() % (24 * 60 * 60 * 1000)));
                return datePickerDialog;
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        // the callback received when the user "sets" the Date in the
        // DatePickerDialog
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            etcalendar.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }
    };
    public void onBackPressed() {
        super.onBackPressed();

    }

}
