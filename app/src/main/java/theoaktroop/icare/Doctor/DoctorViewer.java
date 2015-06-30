package theoaktroop.icare.Doctor;

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
import theoaktroop.icare.R;

/**
 * Created by Sunny_PC on 6/23/2015.
 */
public class DoctorViewer extends Activity {
    ListView listViewDoctor;
    DoctorDatabaseQuery mDoctorDatabaseQuery;
    private List<DoctorClass> mDoctorClassList;
    private ListDoctorAdapter mAdapter;
    private Long profileID;
    private long doctorID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list_item_layout);
        listViewDoctor = (ListView) findViewById(R.id.list_item_forDoc);


        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));

      setListView();

        System.out.println(DbHelper.COLUMN_PROFILE_ID);
    }
    public void setListView()
    {  mDoctorDatabaseQuery = new DoctorDatabaseQuery(this);
        mDoctorClassList =mDoctorDatabaseQuery.getAllDoctorsById(profileID);
       // System.out.println("For setList"+mDierchartclass);
        mAdapter = new ListDoctorAdapter(this,mDoctorClassList);
        listViewDoctor.setAdapter(mAdapter);
        System.out.println("From Viewer inside setListView "+profileID);


        listViewDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemIntent = new Intent(DoctorViewer.this, DoctorEditActivity.class);
//                iTemIntent.putExtra("position_id",String.valueOf(id));
                itemIntent.putExtra("profile_id", String.valueOf(profileID));
                itemIntent.putExtra("doctor_id", String.valueOf(mDoctorClassList.get(position).getId()));
                startActivity(itemIntent);
            }
        });


        listViewDoctor.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                doctorID = mDoctorClassList.get(position).getId();

                new AlertDialog.Builder(DoctorViewer.this)
                        .setTitle("Delete Doctor?")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                mDoctorDatabaseQuery.deleteDoctor(doctorID);
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
