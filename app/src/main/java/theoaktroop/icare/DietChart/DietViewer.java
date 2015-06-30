package theoaktroop.icare.DietChart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import theoaktroop.icare.DbHelper.DbHelper;
import theoaktroop.icare.R;

/**
 * Created by Sunny_PC on 6/23/2015.
 */
public class DietViewer extends Activity {
    ListView listViewDiet;
    DietChartDatabaseQuery mDietChartDatabaseQuery;
    private List<DietChartClass> mDietChartclass;
    private ListDietAdapter mAdapter;
    private long profileID;
    private long dietID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_list_item_layout);
        listViewDiet = (ListView) findViewById(R.id.list_item_forD);

        mDietChartDatabaseQuery = new DietChartDatabaseQuery(this);
        Intent intent = getIntent();
        profileID = Long.parseLong(intent.getStringExtra("profile_id"));


        setListView();

//        listViewDiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent iTemIntent=new Intent(DietViewer.this,ProfileDetails.class);
//                iTemIntent.putExtra("id",String.valueOf(id));
//                startActivity(iTemIntent);
//
//            }
//        });
//        listViewDiet.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                ePID = id;
//                return false;
//            }
//        });

        System.out.println(DbHelper.COLUMN_PROFILE_ID);
        contextRegister();

    }
    private void contextRegister ()
    {
        registerForContextMenu(listViewDiet);
    }
    public void setListView()
    {

        //try {
            mDietChartclass =mDietChartDatabaseQuery.getAllDietsById(profileID);
            if (mDietChartclass != null) {
                System.out.println("For setList" + mDietChartclass);
                mAdapter = new ListDietAdapter(this, mDietChartclass);
                listViewDiet.setAdapter(mAdapter);
            } else {
                listViewDiet.setVisibility(View.GONE);
            }
       // }
      //  catch (Exception e)
      //  {
           // listViewDiet.setVisibility(View.GONE);
       // }
        //System.out.println("From Viewer inside setListView " + profileID);


        listViewDiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iTemIntent = new Intent(DietViewer.this, DietEditActivity.class);
//                iTemIntent.putExtra("position_id",String.valueOf(id));
                iTemIntent.putExtra("profile_id", String.valueOf(profileID));
                iTemIntent.putExtra("diet_id", String.valueOf(mDietChartclass.get(position).getId()));//
                startActivity(iTemIntent);
            }
        });
        

        listViewDiet.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dietID = mDietChartclass.get(position).getId();
                return false;
            }
        });





//        if (mDietChartclass != null && mDietChartclass.isEmpty()) {
//            mAdapter = new ListDietAdapter(this, mDietChartclass);
//            listViewDiet.setAdapter(mAdapter);
//            System.out.println("From Viewer inside setListView "+profileID);
//        }

    }
    public void AddDiet(View view)
    {
        Intent intent = new Intent(DietViewer.this,DietCreateActivity.class);
        intent.putExtra("profile_id",String.valueOf(profileID));
        startActivity(intent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setListView();
        
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_delete_diet, menu);




        menu.setHeaderTitle("Select Menu ");
    }

    /** This will be invoked when a menu item is selected */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case R.id.action_deletDiet:
                mDietChartDatabaseQuery.deleteDiet(dietID);
                setListView();
                break;


        }
        return true;
    }
}
