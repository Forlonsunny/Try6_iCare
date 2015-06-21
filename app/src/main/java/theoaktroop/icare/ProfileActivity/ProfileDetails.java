package theoaktroop.icare.ProfileActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import theoaktroop.icare.Health.HealthCreateActivity;
import theoaktroop.icare.R;

/**
 * Created by Mobile App Develop on 17-6-15.
 */
public class ProfileDetails extends Activity {
    TextView getTxt_pName,getTxt_pRelation,getTxt_pAge;
    private ProfileDataBase mProfileDataBase;
    private Profile mProfile;

    String flag;
    long eMid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.profile_details_with_aces);


        intilizationOfViews();


        Intent mEIntent = getIntent();
        flag = mEIntent.getStringExtra("id");
        if (flag != null) {
            eMid = Long.parseLong(flag);


            mProfileDataBase = new ProfileDataBase(this);
            mProfile = mProfileDataBase.getAllProfilesById(eMid);
            getTxt_pName.setText(mProfile.getProfileName());
            getTxt_pRelation.setText(mProfile.getRelation());
            getTxt_pAge.setText(mProfile.getAge());



        }
    }

    private void intilizationOfViews() {
        getTxt_pName=(TextView)findViewById(R.id.txt_profile_name);
        getTxt_pRelation=(TextView)findViewById(R.id.txt_relation);
        getTxt_pAge=(TextView)findViewById(R.id.txt_age);

    }
    public void BtForActionAces(View view)
    {

        switch (view.getId()){
            case R.id.buttonHealth:
                startActivity(new Intent(ProfileDetails.this, HealthCreateActivity.class));
                break;

        }
        finish();
    }

}
