package theoaktroop.icare.Health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import theoaktroop.icare.R;

/**
 * Created by Sunny_PC on 6/27/2015.
 */
public class HealthEditActivity extends Activity {
    EditText getEtHegith,getGetEtWeight,getGetEtBlGropup,getGetEtBlPl,getGetEtBlSl;
    private HealthDatabaseQuery mHealthDatabaseQuery;

    private HealthClass mHealthClass;
    String flag;
  private   long eMid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mEIntent = getIntent();
        flag = mEIntent.getStringExtra("profile_id");
        eMid = Long.parseLong(flag);
        System.out.println(flag + " EMid = " + eMid);
        Seter();
    }



    private void Seter()

    {
        setContentView(R.layout.health_condition);
        intilizationOfViews();
        mHealthDatabaseQuery=new HealthDatabaseQuery(this);
        mHealthClass = mHealthDatabaseQuery.getAllHealthsById(eMid);
        getEtHegith.setText(mHealthClass.getHeight());
        getGetEtWeight.setText(mHealthClass.getWight());
        getGetEtBlGropup.setText(mHealthClass.getBloodGroup());
        getGetEtBlPl.setText(mHealthClass.getBloodPressure());
        getGetEtBlSl.setText(mHealthClass.getBloodSugar());
    }
    private void intilizationOfViews() {

        getEtHegith=(EditText)findViewById(R.id.etHight);
        getGetEtWeight=(EditText)findViewById(R.id.etWeight);
        getGetEtBlGropup=(EditText)findViewById(R.id.etBloodGroup);
        getGetEtBlPl=(EditText)findViewById(R.id.etBloodPressure);
        getGetEtBlSl=(EditText)findViewById(R.id.etBloodSugar);
    }
    public void SubmitHeBt(View view){

        try {
            Editable prHeight=getEtHegith.getText();
            Editable prWeght=getGetEtWeight.getText();
            Editable prBloodGropup=getGetEtBlGropup.getText();
            Editable prBlPre=getGetEtBlPl.getText();
            Editable prBlSl=getGetEtBlSl.getText();

            if (!TextUtils.isEmpty(prHeight) && !TextUtils.isEmpty(prWeght) && !TextUtils.isEmpty(prBloodGropup) &&
                    !TextUtils.isEmpty(prBlPre) && !TextUtils.isEmpty(prBlSl)) {


                mHealthDatabaseQuery.upDateHealth(eMid,prHeight.toString(),prWeght.toString(),prBloodGropup.toString(),prBlPre.toString(),prBlSl.toString());
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
