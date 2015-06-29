package theoaktroop.icare.DietChart;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import theoaktroop.icare.R;

/**
 * Created by Hasan Abdullah on 22-Jun-15.
 */
public class DietCreateActivity extends Activity{

    private Spinner daySpinner;
    private Spinner mealSpinner;
    private EditText menuEditText;
    private ImageView timeImageView;
    private EditText timeEditText;
    private DietChartDatabaseQuery mDietChartDatabaseQuery;
    private Long profileID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_chart);

        viewInitialize();
        mDietChartDatabaseQuery = new DietChartDatabaseQuery(this);
        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));

    }

    public void timePicker(View view){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(DietCreateActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                timeImageView.setVisibility(View.GONE);
                int convertedHour;
                String AM_PM;

                if(selectedHour>12)
                    convertedHour = selectedHour-12;
                else
                    convertedHour = selectedHour;

                if(selectedHour>=12)
                    AM_PM = "PM";
                else
                    AM_PM = "AM";

                timeEditText.setText( convertedHour + ":" + selectedMinute + " " + AM_PM);
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public void saveDietChart(View view){

        try {
            String dayString = daySpinner.getSelectedItem().toString();
            String mealString = mealSpinner.getSelectedItem().toString();
            String menuString = menuEditText.getText().toString();
            String timeString = timeEditText.getText().toString();
            DietChartClass createNewDiet = mDietChartDatabaseQuery.createNewDietChart(profileID.toString(),dayString, mealString, menuString, timeString);
            finish();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Input food menu",Toast.LENGTH_SHORT).show();
        }

    }

    public void viewInitialize(){

        daySpinner = (Spinner) findViewById(R.id.daySpinner);
        mealSpinner = (Spinner) findViewById(R.id.mealSpinner);
        menuEditText = (EditText) findViewById(R.id.menuEditText);
        timeImageView = (ImageView) findViewById(R.id.timeImage);
        timeEditText = (EditText) findViewById(R.id.timeEditText);
    }
}
