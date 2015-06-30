package theoaktroop.icare.Doctor;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import theoaktroop.icare.R;
/**
 * Created by Hasan Abdullah on 22-Jun-15.
 */
public class DoctorCreateActivity extends Activity {
    EditText getTxtdName,getTxtdType,getTxtdAddress,getTxtdPhone,getTxtdAppDate;
    private Long profileID;
    private DoctorDatabaseQuery mDoctorDatabaseQuery;
    private String dateString;
    private String timeString;
    private Button timeButton;
    private Button dateButton;
    private int startYear=1992;
    private int startMonth=6;
    private int startDay=15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);

        initilizationOfViews();
        Calendar cc=Calendar.getInstance();
        startMonth=cc.get(Calendar.MONTH);
        startDay=cc.get(Calendar.DAY_OF_MONTH);
        startYear=cc.get(Calendar.YEAR);

        mDoctorDatabaseQuery=new DoctorDatabaseQuery(this);
        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));
    }

    private void initilizationOfViews() {
        getTxtdName=(EditText)findViewById(R.id.doctor_name);
        getTxtdType=(EditText)findViewById(R.id.doctor_type);
        getTxtdAddress=(EditText)findViewById(R.id.doctor_Address);
        getTxtdPhone=(EditText)findViewById(R.id.doctor_Phone);
        timeButton = (Button) findViewById(R.id.doctor_AppTime);
        dateButton = (Button) findViewById(R.id.doctor_AppDate);
//        getTxtdAppDate=(EditText)findViewById(R.id.doctor_AppDate);
    }

    public void addDoctorBt(View view)
    {
        try {
           // dateString = "25/12/2015"; // for testx
            Editable drName=getTxtdName.getText();
            if(!TextUtils.isEmpty(drName)) {
                DoctorClass newDoctorClass = mDoctorDatabaseQuery.createNewDoctor(profileID.toString(), getTxtdName.getText().toString(), getTxtdType.getText().toString(), getTxtdAddress.getText().toString(), getTxtdPhone.getText().toString(), dateString, timeString);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "You Must Fill Doctor Name", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "You Must Fill Doctor Name", Toast.LENGTH_SHORT).show();
        }
    }

    public void datePick(View view){

        try{
            showDialog(1);



        }
        catch (Exception e)
        {

        }
        }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {


            case 1:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        startYear, startMonth+1, startDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

           dateButton.setText(""+selectedDay+"/"+(startMonth+1)+"/"+startYear);
            dateString=""+selectedDay+"/"+(startMonth+1)+"/"+startYear;

            // birthDate.setText(""+selectedDay+":"+(startMonth+1)+":"+startYear);

        }
    };


    public void timePick(View view){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(DoctorCreateActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                timeImageView.setVisibility(View.GONE);
                int convertedHour;
                String AM_PM;
                String minute;

                if(selectedMinute<10)
                    minute = "0" + String.valueOf(selectedMinute);
                else
                    minute = String.valueOf(selectedMinute);

                if(selectedHour>12)
                    convertedHour = selectedHour-12;
                else if(selectedHour==0)
                    convertedHour = 12;
                else
                    convertedHour = selectedHour;

                if(selectedHour>=12)
                    AM_PM = "PM";
                else
                    AM_PM = "AM";
                timeString = String.valueOf(convertedHour) + ":" + minute + " " + AM_PM;
                timeButton.setText(timeString);
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
