package theoaktroop.icare.Vaccine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import theoaktroop.icare.DbHelper.DbHelper;
import theoaktroop.icare.R;

/**
 * Created by Hasan Abdullah on 22-Jun-15.
 */
public class VaciViewerActivity extends Activity{
    ListView listViewVacci;
    VaccineDatabaseQuery mVaccineDatabaseQuery;
    private List<VaccinationClass> mVaccinationClasses;
    private ListVacciAdapter mAdapter;
    private long profileID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacci_list_item_layout);
        listViewVacci = (ListView) findViewById(R.id.list_item_forVacci);

        mVaccineDatabaseQuery = new VaccineDatabaseQuery(this);

        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));

        setListView();

        System.out.println(DbHelper.COLUMN_PROFILE_ID);
    }
    public void setListView()
    {
        mVaccinationClasses =mVaccineDatabaseQuery.getAllVaccinesById(profileID);
        System.out.println("For setList"+mVaccinationClasses);
        mAdapter = new ListVacciAdapter(this, mVaccinationClasses);
        listViewVacci.setAdapter(mAdapter);
        System.out.println("From Viewer inside setListView "+profileID);
//        if (mDierchartclass != null && mDierchartclass.isEmpty()) {
//            mAdapter = new ListDietAdapter(this, mDierchartclass);
//            listViewDiet.setAdapter(mAdapter);
//            System.out.println("From Viewer inside setListView "+profileID);
//        }

    }
    public void AddVaccine(View view)
    {
        Intent intent = new Intent(VaciViewerActivity.this,VacciCreateActivity.class);
        intent.putExtra("profile_id",String.valueOf(profileID));
        startActivity(intent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setListView();
    }
}

