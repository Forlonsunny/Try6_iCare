package theoaktroop.icare.Health;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import theoaktroop.icare.R;

/**
 * Created by Sunny_PC on 6/22/2015.
 */

public class HealthCreateActivity extends Activity {
    EditText getEtHegith,getGetEtWeight,getGetEtBlPl,getGetEtBlSl;
    Spinner bloodGroupSpinner;
    private HealthDatabaseQuery mHealthDatabaseQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_condition);
        intilizationOfViews();
        mHealthDatabaseQuery=new HealthDatabaseQuery(this);
    }

    private void intilizationOfViews() {

        getEtHegith=(EditText)findViewById(R.id.etHight);
        getGetEtWeight=(EditText)findViewById(R.id.etWeight);
        bloodGroupSpinner = (Spinner) findViewById(R.id.bloodGroupSpinner);
//        getGetEtBlGropup=(EditText)findViewById(R.id.etBloodGroup);
        getGetEtBlPl=(EditText)findViewById(R.id.etBloodPressure);
        getGetEtBlSl=(EditText)findViewById(R.id.etBloodSugar);
    }
    public void SubmitHeBt(View view){
        String bloodGroupString = bloodGroupSpinner.getSelectedItem().toString();

        try {
            Editable prHeight=getEtHegith.getText();
            Editable prWeght=getGetEtWeight.getText();
            Editable prBloodGropup=new SpannableStringBuilder(bloodGroupString);
            Editable prBlPre=getGetEtBlPl.getText();
            Editable prBlSl=getGetEtBlSl.getText();

            if (!TextUtils.isEmpty(prHeight) || !TextUtils.isEmpty(prWeght) || !TextUtils.isEmpty(prBloodGropup) ||
                    !TextUtils.isEmpty(prBlPre) || !TextUtils.isEmpty(prBlSl)) {
                HealthClass createNewHealth = mHealthDatabaseQuery.createNewHealth(prHeight.toString(),prWeght.toString(),prBloodGropup.toString(),prBlPre.toString(),prBlSl.toString());
//                Intent intent = new Intent();
//                intent.putExtra(ProfileListactivity.EXTRA_ADDED_PROFILE, (Serializable) createNewHealth);
//                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "You Must Fill all Fields", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "You Must Fill all Fields", Toast.LENGTH_LONG).show();
        }

    }

}
