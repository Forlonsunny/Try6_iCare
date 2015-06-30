package theoaktroop.icare.Vaccine;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import theoaktroop.icare.R;

/**
 * Created by Hasan Abdullah on 22-Jun-15.
 */

public class VacciCreateActivity extends Activity{

    private EditText gettxtVacciName,gettxtVacciReason;

     Button gettxtVacciDate;
    private Long profileID;
    private VaccineDatabaseQuery mVaccineDatabaseQuery;
    private int startYear=1992;
    private int startMonth=6;
    private int startDay=15;
    private String selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccenation_chart);

        Calendar cc=Calendar.getInstance();
        startMonth=cc.get(Calendar.MONTH);
        startDay=cc.get(Calendar.DAY_OF_MONTH);
        startYear=cc.get(Calendar.YEAR);

        viewInitialize();
        mVaccineDatabaseQuery = new VaccineDatabaseQuery(this);
        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));

    }

    public void SubmitVacciBt(View view){
        Editable VccrName=gettxtVacciName.getText();

        try {
            if(!TextUtils.isEmpty(VccrName)) {
                VaccinationClass createNewVacci = mVaccineDatabaseQuery.createNewVaccine(profileID.toString(), gettxtVacciName.getText().toString(), gettxtVacciReason.getText().toString(), selectedDate);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Vaccine Name  must fill!",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Vaccine Name  must fill!",Toast.LENGTH_SHORT).show();
        }

    }
    public void datePickerVaccination (View v)
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


public void viewInitialize(){

        gettxtVacciName=(EditText)findViewById(R.id.etVname);
        gettxtVacciReason=(EditText)findViewById(R.id.etReason);
        gettxtVacciDate=(Button)findViewById(R.id.vacci_Bt_Date);
    }
}
