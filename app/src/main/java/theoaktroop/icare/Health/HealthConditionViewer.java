package theoaktroop.icare.Health;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent mEIntent = getIntent();
        flag = mEIntent.getStringExtra("profile_id");
        eMid = Long.parseLong(flag);
        System.out.println(flag + " EMid = " + eMid);
       ViewSet();

    }

private void Seter()
{
    mHealthClass = mHealthDatabaseQuery.getAllHealthsById(eMid);
    tvHight.setText(mHealthClass.getHeight());
    tvWeight.setText(mHealthClass.getWight());
    tvBlg.setText(mHealthClass.getBloodGroup());
    tvBlPr.setText(mHealthClass.getBloodPressure());
    tvBlSr.setText(mHealthClass.getBloodSugar());
}
    private  void ViewSet()
    {  setContentView(R.layout.health_condition_viewer);
        initilizationOfViews();
        mHealthDatabaseQuery = new HealthDatabaseQuery(this);
        try {
           Seter();
        }
        catch (Exception e)
        {
            startActivity(new Intent(HealthConditionViewer.this,HealthCreateActivity.class));
        }

    }

    private void initilizationOfViews() {

        tvHight=(TextView)findViewById(R.id.tvHight);
        tvWeight=(TextView)findViewById(R.id.tvWeight);
        tvBlg=(TextView)findViewById(R.id.tvBlGroop);
        tvBlPr=(TextView)findViewById(R.id.tvBlPr);
        tvBlSr=(TextView)findViewById(R.id.tvBlSr);

    }
   public void SubmitHeBt(View view){
       Intent hIntent=new Intent(HealthConditionViewer.this, HealthEditActivity.class);
       hIntent.putExtra("profile_id", String.valueOf(eMid));
       startActivity(hIntent);
       System.out.println("From HealthConditionViewer ");

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        try {
            Seter();
        }
        catch (Exception e)
        {

        }
    }
}
