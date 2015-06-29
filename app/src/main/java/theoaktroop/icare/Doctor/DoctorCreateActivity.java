package theoaktroop.icare.Doctor;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import theoaktroop.icare.DietChart.DietEditActivity;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);

        initilizationOfViews();

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
            dateString = "25/12/2015"; // for testx
            DoctorClass newDoctorClass = mDoctorDatabaseQuery.createNewDoctor(profileID.toString(),getTxtdName.getText().toString(), getTxtdType.getText().toString(), getTxtdAddress.getText().toString(),getTxtdPhone.getText().toString(),dateString,timeString);
            finish();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Fill Requirement", Toast.LENGTH_SHORT).show();
        }
    }

//    public void datePick(View view){
//
//        final Calendar myCalendar = Calendar.getInstance();
//
//        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();
//            }
//
//        };
//
//        new DatePickerDialog(DoctorCreateActivity.this, date, myCalendar
//                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//
//        private void updateLabel() {
//
//            String myFormat = "MM/dd/yy"; //In which you need put here
//            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//
//            dateString = sdf.format(myCalendar.getTime()).toString();
//            edittext.setText(sdf.format(myCalendar.getTime()));
//        }
//
//    }

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
