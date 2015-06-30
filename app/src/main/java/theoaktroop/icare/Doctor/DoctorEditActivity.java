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
 * Created by Hasan Abdullah on 29-Jun-15.
 */
public class DoctorEditActivity extends Activity {

    private long insertID;
    private Long profileID;
    private DoctorClass doctorClass;
    private DoctorDatabaseQuery mDoctorDatabaseQuery;
    EditText getTxtdName,getTxtdType,getTxtdAddress,getTxtdPhone,getTxtdAppDate;
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
        setContentView(R.layout.doctor_edit);
        initilizationOfViews();
        mDoctorDatabaseQuery = new DoctorDatabaseQuery(this);

        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));
        insertID = Long.parseLong(intent.getStringExtra("doctor_id"));



        setter();
    }

    public void editDoctorSave(View view){
        String docName = getTxtdName.getText().toString();
        String docType = getTxtdType.getText().toString();
        String docAddress = getTxtdAddress.getText().toString();
        String docPhone = getTxtdPhone.getText().toString();
        String docDate = dateString;
       // dateString = "25/12/2015"; //for test
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
       // mDoctorDatabaseQuery.updateDoctor(insertID,profileID,docName,docType,docAddress,docPhone,dateString,timeString);

        Toast.makeText(getApplicationContext(),"Edit data have been saved!",Toast.LENGTH_SHORT).show();

    }

    private void setter(){
        doctorClass = mDoctorDatabaseQuery.getDoctorById(insertID);
        getTxtdName.setText(doctorClass.getDoctorName().toString());
        getTxtdType.setText(doctorClass.getDoctorType().toString());
        getTxtdAddress.setText(doctorClass.getDoctorAddress().toString());
        getTxtdPhone.setText(doctorClass.getDoctorPhone().toString());
        String daString=doctorClass.getAppointmentDate().toString();
        dateButton.setText(daString);
        String taString=doctorClass.getAppointmentTime().toString();
        timeButton.setText(taString);
    }

    private void initilizationOfViews() {
        getTxtdName=(EditText)findViewById(R.id.doctor_name_edit);
        getTxtdType=(EditText)findViewById(R.id.doctor_type_edit);
        getTxtdAddress=(EditText)findViewById(R.id.doctor_Address_edit);
        getTxtdPhone=(EditText)findViewById(R.id.doctor_Phone_edit);
        timeButton = (Button) findViewById(R.id.doctor_AppTime_edit);
        dateButton = (Button) findViewById(R.id.doctor_AppDate_edit);

    }
    public void datePickEdit(View view){

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
    public void timePickEdit(View view){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(DoctorEditActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
