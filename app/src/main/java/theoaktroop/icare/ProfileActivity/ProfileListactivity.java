package theoaktroop.icare.ProfileActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import theoaktroop.icare.DbHelper.DbHelper;
import theoaktroop.icare.R;

public class ProfileListactivity extends ActionBarActivity {
    public static final int REQUEST_CODE_ADD_PROFILE=40;
    public static final String EXTRA_ADDED_PROFILE = "extra_key_added_profile";
    private ListView mListView;
    private List<Profile>mProfileList;
    private ListProfileAdapter mAdapter;
    private ProfileDataBase mprofileDataBase;
    private Context context;
    long ePID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mprofileDataBase=new ProfileDataBase(this);
        mProfileList=mprofileDataBase.getAllProfiles();
        mListView=(ListView)findViewById(R.id.list_item_forProfile);

        System.out.println(DbHelper.COLUMN_PROFILE_ID);


        if (mProfileList!=null && !mProfileList.isEmpty()){

            mAdapter=new ListProfileAdapter(this,mProfileList);
            mListView.setAdapter(mAdapter);
            contextRegister();
        }
        else {
            mListView.setVisibility(View.GONE);
        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iTemIntent=new Intent(ProfileListactivity.this,ProfileDetails.class);
                iTemIntent.putExtra("id",String.valueOf(id));
                startActivity(iTemIntent);

            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ePID=id;
                return false;
            }
        });



    }
    public void CreateNewProfile(View view){
        Intent newIntent=new Intent(ProfileListactivity.this,CreateiCareProfile.class);
        startActivityForResult(newIntent,REQUEST_CODE_ADD_PROFILE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {


            if (requestCode == REQUEST_CODE_ADD_PROFILE) {
                Profile createdProfile = (Profile) data.getSerializableExtra(EXTRA_ADDED_PROFILE);
                if (mProfileList == null) {
                    mProfileList = new ArrayList<Profile>();
                    mProfileList.add(createdProfile);
                }

                if (mListView.getVisibility() != View.VISIBLE) {
                    mListView.setVisibility(View.VISIBLE);

                }

                if (mAdapter == null) {
                    mAdapter = new ListProfileAdapter(this, mProfileList);
                    mListView.setAdapter(mAdapter);
                    contextRegister();
                } else {
                    // mAdapter.setItems(mListEmployess);
                    //mAdapter.notifyDataSetChanged();
                }
            } else {

            }
        }
        catch (Exception e){
//            Toast.makeText(getApplicationContext(),"Enter Data!", Toast.LENGTH_SHORT).show();
        }

    }

    private void contextRegister ()
    {
        registerForContextMenu(mListView);
    }

    private void forRefresh()
    {
        mprofileDataBase=new ProfileDataBase(this);
        mProfileList=mprofileDataBase.getAllProfiles();
        mListView=(ListView)findViewById(R.id.list_item_forProfile);


        if (mProfileList!=null && !mProfileList.isEmpty()){

            mAdapter=new ListProfileAdapter(this,mProfileList);
            mListView.setAdapter(mAdapter);
            contextRegister();
        }
        else {
            mListView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

       forRefresh();
    }
    /** This will be invoked when an item in the listview is long pressed */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main , menu);




        menu.setHeaderTitle("Select Menu ");
    }

    /** This will be invoked when a menu item is selected */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case R.id.action_UpdateP:
                Intent HIntent=new Intent(ProfileListactivity.this,CreateiCareProfile.class);
                HIntent.putExtra("id",String.valueOf(ePID));
                startActivity(HIntent);
                break;
            case R.id.action_DeleteP:
                mprofileDataBase.deleteById(ePID);
                forRefresh();
                break;
            case R.id.action_DeleteAllP:
               mprofileDataBase.deleteTable();
                forRefresh();
                break;





        }
        return true;
    }
}
