package theoaktroop.icare.Health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import theoaktroop.icare.ProfileActivity.Profile;
import theoaktroop.icare.ProfileActivity.ProfileDataBase;
import theoaktroop.icare.R;

/**
 * Created by Sunny_PC on 6/22/2015.
 */
public class HealthConditionViewer extends Activity {

    TextView tvHight,tvWeight,tvBlg,tvBlPr,tvBlSr;

    private HealthDatabaseQuery mHealthDatabaseQuery;
    private HealthClass mHealthClass;

    String flag;
    long eMid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_condition_viewer);
        initilizationOfViews();
       mHealthDatabaseQuery=new HealthDatabaseQuery(this);

        Intent mEIntent = getIntent();
        flag = mEIntent.getStringExtra("id");
        if (flag != null) {
            eMid = Long.parseLong(flag);
System.out.println("From Condition Viewer "+eMid);


            mHealthDatabaseQuery = new HealthDatabaseQuery(this);
            mHealthClass = mHealthDatabaseQuery.getAllHealthsById(eMid);
            tvHight.setText(mHealthClass.getHeight());
            tvWeight.setText(mHealthClass.getWight());
            tvBlg.setText(mHealthClass.getBloodGroup());
            tvBlPr.setText(mHealthClass.getBloodPressure());
            tvBlSr.setText(mHealthClass.getBloodSugar());
        }
    }

    private void initilizationOfViews() {
        tvHight=(TextView)findViewById(R.id.tvHight);
        tvWeight=(TextView)findViewById(R.id.tvWeight);
        tvBlg=(TextView)findViewById(R.id.tvBlGroop);
        tvBlPr=(TextView)findViewById(R.id.tvBlPr);
        tvBlSr=(TextView)findViewById(R.id.tvBlSr);

    }
}
