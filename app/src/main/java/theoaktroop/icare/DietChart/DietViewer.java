package theoaktroop.icare.DietChart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


import theoaktroop.icare.ProfileActivity.ProfileDBHelper;

import theoaktroop.icare.R;

/**
 * Created by Sunny_PC on 6/23/2015.
 */
public class DietViewer extends Activity {
    ListView listViewDiet;
    DietChartDatabaseQuery mDietChartDatabaseQuery;
    private List<DietChartClass> mDierchartclass;
    private ListDietAdapter mAdapter;
    private long profileID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_list_item_layout);
        listViewDiet = (ListView) findViewById(R.id.list_item_forD);

        mDietChartDatabaseQuery = new DietChartDatabaseQuery(this);
        Intent intent = getIntent();
        profileID = intent.getLongExtra("profile_id", 0);



        System.out.println(ProfileDBHelper.COLUMN_PROFILE_ID);

       //try {
           //it should be mDierchartclass =  mDietChartDatabaseQuery.getAllDietsByProfileId();
           mDierchartclass =  mDietChartDatabaseQuery.getAllDiets();


           if (mDierchartclass != null && mDierchartclass.isEmpty()) {
               mAdapter = new ListDietAdapter(this, mDierchartclass);
               listViewDiet.setAdapter(mAdapter);
           }
      // }

       //catch (Exception e)
      // {
         //  Toast.makeText(getBaseContext(),"Something is Worng",Toast.LENGTH_SHORT).show();
      // }
    }
}
