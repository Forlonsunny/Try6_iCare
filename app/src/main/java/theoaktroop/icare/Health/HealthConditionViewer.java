package theoaktroop.icare.Health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import theoaktroop.icare.ProfileActivity.Profile;
import theoaktroop.icare.ProfileActivity.ProfileDataBase;
import theoaktroop.icare.R;

/**
 * Created by Sunny_PC on 6/22/2015.
 */

public class HealthConditionViewer extends Activity {

    TextView tvHight,tvWeight,tvBlg,tvBlPr,tvBlSr;
int flagForPostResume=0;


    private HealthDatabaseQuery mHealthDatabaseQuery;
    private HealthClass mHealthClass;

    String flag;
    long eMid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_condition_viewer);
        Intent mEIntent = getIntent();
        flag = mEIntent.getStringExtra("profile_id");
        eMid = Long.parseLong(flag);
        System.out.println(flag+" EMid = "+eMid);

        mHealthDatabaseQuery = new HealthDatabaseQuery(this);

        if(mHealthClass==null)
        {

            startActivity(new Intent(HealthConditionViewer.this, HealthCreateActivity.class));

        }
        else {


            ViewSet();
            flagForPostResume=1;
        }


        if (flag != null) {
            System.out.println("From Condition Viewer " + eMid);
        }
    }


    private  void ViewSet()
    {
        initilizationOfViews();
        mHealthClass = mHealthDatabaseQuery.getAllHealthsById(eMid);
        tvHight.setText(mHealthClass.getHeight());
        tvWeight.setText(mHealthClass.getWight());
        tvBlg.setText(mHealthClass.getBloodGroup());
        tvBlPr.setText(mHealthClass.getBloodPressure());
        tvBlSr.setText(mHealthClass.getBloodSugar());
    }

    private void initilizationOfViews() {

        tvHight=(TextView)findViewById(R.id.tvHight);
        tvWeight=(TextView)findViewById(R.id.tvWeight);
        tvBlg=(TextView)findViewById(R.id.tvBlGroop);
        tvBlPr=(TextView)findViewById(R.id.tvBlPr);
        tvBlSr=(TextView)findViewById(R.id.tvBlSr);

    }
   public void SubmitHeBt(View view){
        startActivity(new Intent(HealthConditionViewer.this, HealthCreateActivity.class));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(flagForPostResume==1)
        ViewSet();
    }
}
