package theoaktroop.icare.DietChart;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
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
 * Created by Mobile App Develop on 29-6-15.
 */
public class DietEditActivity extends Activity {

    private Spinner daySpinner;
    private Spinner mealSpinner;
    private EditText menuEditText;
    private Button timeButton;
    private String timeString;
    private DietChartDatabaseQuery mDietChartDatabaseQuery;
    private DietChartClass dietChartClass;
    private Long profileID;
    private Long insertID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_edit);

        mDietChartDatabaseQuery = new  DietChartDatabaseQuery(this);



        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));
        insertID = Long.parseLong(intent.getStringExtra("diet_id"));

        dietChartClass = mDietChartDatabaseQuery.getDietById(insertID);


        viewInitialize();
        setter();

    }

    public void editDietChart(View view){

        try {
            String dayString = daySpinner.getSelectedItem().toString();
            String mealString = mealSpinner.getSelectedItem().toString();
            String menuString = menuEditText.getText().toString();
//            String timeString = timeEditText.getText().toString();
            mDietChartDatabaseQuery.updateDiet(insertID, profileID.toString(), dayString, mealString, menuString, timeString);
            finish();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Input food menu", Toast.LENGTH_SHORT).show();
        }

    }

    public void viewInitialize(){

        daySpinner = (Spinner) findViewById(R.id.daySpinner);
        mealSpinner = (Spinner) findViewById(R.id.mealSpinner);
        menuEditText = (EditText) findViewById(R.id.menuEditText);
        timeButton = (Button) findViewById(R.id.timeButton);
//        timeEditText = (EditText) findViewById(R.id.timeEditText);
    }

    private void setter(){
        String[] day = getResources().getStringArray(R.array.day_arrays);
        String instantDay = dietChartClass.getDay() ;

        for(int i=0; i<=6; i++){
            if(instantDay.equals(day[i])){
                daySpinner.setSelection(i);
                break;
            }

        }


        String[] meal = getResources().getStringArray(R.array.meal_arrays);
        String instantMeal = dietChartClass.getMealType();

        for(int i=0; i<=3; i++){
            if(instantMeal.equals(meal[i])){
                mealSpinner.setSelection(i);
                break;
            }

        }

        menuEditText.setText(dietChartClass.getFoodMenu());
        timeButton.setText(dietChartClass.getDietTime());

    }

    public void timePickerEdit(View view){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(DietEditActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                timeImageView.setVisibility(View.GONE);
                int convertedHour;
                String AM_PM;
                String minute;

                if(selectedMinute<10)
                    minute = "0" + String.valueOf(selectedMinute);
                else
                    minute = String.valueOf(selectedMinute);

                if(selectedHour>12)
                    convertedHour = selectedHour-12;
                else
                    convertedHour = selectedHour;

                if(selectedHour>=12)
                    AM_PM = "PM";
                else
                    AM_PM = "AM";
                timeString = String.valueOf(convertedHour) + ": " + minute + AM_PM;
                timeButton.setText(timeString);
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
