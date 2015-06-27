package theoaktroop.icare.Doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import theoaktroop.icare.R;

/**
 * Created by Hasan Abdullah on 22-Jun-15.
 */
public class DoctorCreateActivity extends Activity {
    EditText getTxtdName,getTxtdType,getTxtdAddress,getTxtdPhone,getTxtdAppDate;
   private Long profileID;
    private DoctorDatabaseQuery mDoctorDatabaseQuery;
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
        getTxtdAppDate=(EditText)findViewById(R.id.doctor_AppDate);
    }

    public void addDoctorBt(View view)
    {
        try {

            DoctorClass newDoctorClass = mDoctorDatabaseQuery.createNewDoctor(profileID.toString(),getTxtdName.getText().toString(), getTxtdType.getText().toString(), getTxtdAddress.getText().toString(),getTxtdPhone.getText().toString(),getTxtdAppDate.getText().toString());
            finish();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Fill Requirement", Toast.LENGTH_SHORT).show();
        }
    }
}
