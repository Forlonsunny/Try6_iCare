package theoaktroop.icare.Doctor;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
    private int startYear=2015;
    private int startMonth=5;
    private int startDay=1;
    int checkTimpiker=0;
    private CheckBox checkBoxDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_edit);

        mDoctorDatabaseQuery = new DoctorDatabaseQuery(this);

        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));
        insertID = Long.parseLong(intent.getStringExtra("doctor_id"));

        doctorClass = mDoctorDatabaseQuery.getDoctorById(insertID);
        initilizationOfViews();
        setter();
    }
    public void remainderSet()
    {
        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");
        calIntent.putExtra(CalendarContract.Events.TITLE, "Doctor Appointment  Remainder");
        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "My Desired Doctor Clinic ");
        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, getTxtdName.getText().toString()+" for "+getTxtdType.getText().toString());


        // long totalMillisecond=setRemainderHour*60*60*1000+SetRemainderminute*60*1000;
        GregorianCalendar calDate = new GregorianCalendar(startYear, startMonth,startDay-1);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                calDate.getTimeInMillis());
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                calDate.getTimeInMillis());

        startActivity(calIntent);
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
                String doctorRemainder="off";
                if(checkBoxDoctor.isChecked()==true && checkTimpiker==1 )
                {  doctorRemainder="on";
                    remainderSet();
                }
                mDoctorDatabaseQuery.updateDoctor(insertID,profileID,docName,docType,docAddress,docPhone,dateString,timeString,doctorRemainder);
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

    private void setter(){
        getTxtdName.setText(doctorClass.getDoctorName().toString());
        getTxtdType.setText(doctorClass.getDoctorType().toString());
        getTxtdAddress.setText(doctorClass.getDoctorAddress().toString());
        getTxtdPhone.setText(doctorClass.getDoctorPhone().toString());
        String daString=doctorClass.getAppointmentDate().toString();
        dateButton.setText(daString);
        String taString=doctorClass.getAppointmentTime().toString();
        timeButton.setText(taString);
        timeString = doctorClass.getAppointmentTime().toString();
        if(doctorClass.getDoctoRemaindercheck().equals("on"))
            checkBoxDoctor.setChecked(true);
    }

    private void initilizationOfViews() {
        getTxtdName=(EditText)findViewById(R.id.doctor_name_edit);
        getTxtdType=(EditText)findViewById(R.id.doctor_type_edit);
        getTxtdAddress=(EditText)findViewById(R.id.doctor_Address_edit);
        getTxtdPhone=(EditText)findViewById(R.id.doctor_Phone_edit);
        timeButton = (Button) findViewById(R.id.doctor_AppTime_edit);
        dateButton = (Button) findViewById(R.id.doctor_AppDate_edit);

//        getTxtdAppDate=(EditText)findViewById(R.id.doctor_AppDate);
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
            checkTimpiker=1;
            startYear=selectedYear;
            startMonth=selectedMonth;
            startDay=selectedDay;
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
