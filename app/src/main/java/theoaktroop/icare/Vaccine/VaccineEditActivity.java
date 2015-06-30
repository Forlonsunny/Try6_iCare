package theoaktroop.icare.Vaccine;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import theoaktroop.icare.R;

/**
 * Created by Hasan Abdullah on 30-Jun-15.
 */
public class VaccineEditActivity extends Activity{
    VaccinationClass mVaccinationClass;
    VaccineDatabaseQuery mVaccinationDatabaseQuery;
    private EditText gettxtVacciName,gettxtVacciReason;
    Button gettxtVacciDate;
    private long insertID;
    private long profileID;

    private int startYear=1992;
    private int startMonth=6;
    private int startDay=15;
    private String selectedDate;


    CheckBox checkBoxVacci;
    int checkTimpiker=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccine_edit);

        mVaccinationDatabaseQuery = new VaccineDatabaseQuery(this);
        Calendar cc=Calendar.getInstance();
        startMonth=cc.get(Calendar.MONTH);
        startDay=cc.get(Calendar.DAY_OF_MONTH);
        startYear=cc.get(Calendar.YEAR);
        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));
        insertID = Long.parseLong(intent.getStringExtra("vaccine_id"));

        mVaccinationClass = mVaccinationDatabaseQuery.getVaccineById(insertID);

        viewInitialization();
        setter();
    }

    private void setter() {
        gettxtVacciName.setText(mVaccinationClass.getVaccineName());
        gettxtVacciReason.setText(mVaccinationClass.getReason());
        gettxtVacciDate.setText(mVaccinationClass.getVaccineDate());
        if(mVaccinationClass.getVaccinRecheckh().equals("on"))
            checkBoxVacci.setChecked(true);
    }

    private void viewInitialization() {
        gettxtVacciName=(EditText)findViewById(R.id.etVnameEdit);
        gettxtVacciReason=(EditText)findViewById(R.id.etReasonEdit);
        gettxtVacciDate=(Button)findViewById(R.id.etDateEdit);
        checkBoxVacci=(CheckBox)findViewById(R.id.vacciEditRemainder);
    }
    public void remainderSet()
    {
        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");
        calIntent.putExtra(CalendarContract.Events.TITLE, "Vaccination Remainder");
        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "My Desired Clinic ");
        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, gettxtVacciName.getText().toString() + " for " + gettxtVacciReason.getText().toString());


        // long totalMillisecond=setRemainderHour*60*60*1000+SetRemainderminute*60*1000;
        GregorianCalendar calDate = new GregorianCalendar(startYear, startMonth,startDay-1);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                calDate.getTimeInMillis());
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                calDate.getTimeInMillis());

        startActivity(calIntent);
    }
    public void datePickerVaccination(View view)
    {

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
                        startYear, startMonth, startDay);
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
            // age.setDateOfBirth(startYear, startMonth, startDay);
            // getBtForDate.setText(""+selectedDay+"/"+(startMonth+1)+"/"+startYear);
            selectedDate=""+selectedDay+"/"+(startMonth+1)+"/"+startYear;
            gettxtVacciDate.setText(selectedDate);
            // birthDate.setText(""+selectedDay+":"+(startMonth+1)+":"+startYear);
            // calculateAge();
        }
    };

    public void editSaveVaccine(View view){


        Editable VccrName=gettxtVacciName.getText();
        try {
        if(!TextUtils.isEmpty(VccrName)) {
            String vacciRemainder="off";
            if(checkBoxVacci.isChecked()==true && checkTimpiker==1 )
            {  vacciRemainder="on";
                remainderSet();
            }
            mVaccinationDatabaseQuery.updateVaccine(insertID, gettxtVacciName.getText().toString(), gettxtVacciReason.getText().toString(), selectedDate,vacciRemainder);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Vaccine Name  must fill!", Toast.LENGTH_SHORT).show();
        }
    }
    catch (Exception e){
        Toast.makeText(getApplicationContext(),"Vaccine Name  must fill!",Toast.LENGTH_SHORT).show();
    }

    }
}
