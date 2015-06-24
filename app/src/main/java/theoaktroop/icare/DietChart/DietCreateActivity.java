package theoaktroop.icare.DietChart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import theoaktroop.icare.R;

/**
 * Created by Hasan Abdullah on 22-Jun-15.
 */
public class DietCreateActivity extends Activity{

    private Spinner daySpinner;
    private Spinner mealSpinner;
    private EditText menuEditText;
    private DietChartDatabaseQuery mDietChartDatabaseQuery;
    private Long profileID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_chart);

        viewInitialize();
        mDietChartDatabaseQuery = new DietChartDatabaseQuery(this);
        Intent intent = getIntent();
        profileID = intent.getLongExtra("profile_id",0);


    }

    public void saveDietChart(View view){

        try {
            String dayString = daySpinner.getSelectedItem().toString();
            String mealString = mealSpinner.getSelectedItem().toString();
            String menuString = menuEditText.getText().toString();
            DietChartClass createNewDiet = mDietChartDatabaseQuery.createNewDietChart(profileID.toString(),dayString, mealString, menuString);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Input food menu",Toast.LENGTH_SHORT).show();
        }

    }

    public void viewInitialize(){

        daySpinner = (Spinner) findViewById(R.id.daySpinner);
        mealSpinner = (Spinner) findViewById(R.id.mealSpinner);
        menuEditText = (EditText) findViewById(R.id.menuEditText);
    }
}
