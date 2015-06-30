package theoaktroop.icare.Vaccine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import theoaktroop.icare.DbHelper.DbHelper;
import theoaktroop.icare.Doctor.DoctorEditActivity;
import theoaktroop.icare.Doctor.DoctorViewer;
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
    private long vaccineID;



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

        listViewVacci.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemIntent = new Intent(VaciViewerActivity.this, VaccineEditActivity.class);
//                iTemIntent.putExtra("position_id",String.valueOf(id));
                itemIntent.putExtra("profile_id", String.valueOf(profileID));
                itemIntent.putExtra("vaccine_id", String.valueOf(mVaccinationClasses.get(position).getId()));
                startActivity(itemIntent);
            }
        });

        listViewVacci.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vaccineID = mVaccinationClasses.get(position).getId();

                new AlertDialog.Builder(VaciViewerActivity.this)
                        .setTitle("Delete Vaccine?")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                mVaccineDatabaseQuery.deleteVaccine(vaccineID);

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return true;
            }
        });

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

