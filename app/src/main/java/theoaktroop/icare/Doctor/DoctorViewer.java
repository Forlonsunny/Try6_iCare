package theoaktroop.icare.Doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import theoaktroop.icare.DbHelper.DbHelper;
import theoaktroop.icare.R;

/**
 * Created by Sunny_PC on 6/23/2015.
 */
public class DoctorViewer extends Activity {
    ListView listViewDiet;
    DoctorDatabaseQuery mDoctorDatabaseQuery;
    private List<DoctorClass> mDoctorClassList;
    private ListDoctorAdapter mAdapter;
    private long profileID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list_item_layout);
        listViewDiet = (ListView) findViewById(R.id.list_item_forDoc);

        mDoctorDatabaseQuery = new DoctorDatabaseQuery(this);
        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));

      setListView();

        System.out.println(DbHelper.COLUMN_PROFILE_ID);
    }
    public void setListView()
    {
        mDoctorClassList =mDoctorDatabaseQuery.getAllDoctorsById(profileID);
       // System.out.println("For setList"+mDierchartclass);
        mAdapter = new ListDoctorAdapter(this,mDoctorClassList);
        listViewDiet.setAdapter(mAdapter);
        System.out.println("From Viewer inside setListView "+profileID);
//        if (mDierchartclass != null && mDierchartclass.isEmpty()) {
//            mAdapter = new ListDietAdapter(this, mDierchartclass);
//            listViewDiet.setAdapter(mAdapter);
//            System.out.println("From Viewer inside setListView "+profileID);
//        }

    }
    public void AddDoctor(View view)
    {
        Intent intent = new Intent(DoctorViewer.this,DoctorCreateActivity.class);
        intent.putExtra("profile_id",String.valueOf(profileID));
        startActivity(intent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setListView();
    }
}
