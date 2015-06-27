package theoaktroop.icare.Vaccine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import theoaktroop.icare.R;

/**
 * Created by Hasan Abdullah on 22-Jun-15.
 */

public class VacciCreateActivity extends Activity{

    private TextView gettxtVacciName,gettxtVacciReason,gettxtVacciDate;
    private Long profileID;
    private VaccineDatabaseQuery mVaccineDatabaseQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccenation_chart);

        viewInitialize();
        mVaccineDatabaseQuery = new VaccineDatabaseQuery(this);
        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));


    }

    public void SubmitVacciBt(View view){

        try {

            VaccinationClass createNewVacci = mVaccineDatabaseQuery.createNewVaccine(profileID.toString(), gettxtVacciName.getText().toString(), gettxtVacciReason.getText().toString(), gettxtVacciDate.getText().toString());
       finish();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Input food menu",Toast.LENGTH_SHORT).show();
        }

    }

    public void viewInitialize(){

        gettxtVacciName=(EditText)findViewById(R.id.etVname);
        gettxtVacciReason=(EditText)findViewById(R.id.etReason);
        gettxtVacciDate=(EditText)findViewById(R.id.etDate);
    }
}
