package theoaktroop.icare.Vaccine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import theoaktroop.icare.R;

/**
 * Created by Hasan Abdullah on 30-Jun-15.
 */
public class VaccineEditActivity extends Activity{
    VaccinationClass mVaccinationClass;
    VaccineDatabaseQuery mVaccinationDatabaseQuery;
    private TextView gettxtVacciName,gettxtVacciReason,gettxtVacciDate;
    private long insertID;
    private long profileID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccine_edit);

        mVaccinationDatabaseQuery = new VaccineDatabaseQuery(this);

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
    }

    private void viewInitialization() {
        gettxtVacciName=(EditText)findViewById(R.id.etVnameEdit);
        gettxtVacciReason=(EditText)findViewById(R.id.etReasonEdit);
        gettxtVacciDate=(Button)findViewById(R.id.etDateEdit);
    }

    public void editSaveVaccine(View view){

        try {
            mVaccinationDatabaseQuery.updateVaccine(insertID, gettxtVacciName.getText().toString(), gettxtVacciReason.getText().toString(), gettxtVacciDate.getText().toString());
            finish();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "ALl field must fill!", Toast.LENGTH_SHORT).show();
        }

    }
}
