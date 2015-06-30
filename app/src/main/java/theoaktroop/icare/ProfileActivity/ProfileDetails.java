package theoaktroop.icare.ProfileActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

import theoaktroop.icare.DietChart.DietViewer;
import theoaktroop.icare.Doctor.DoctorViewer;
import theoaktroop.icare.Health.HealthConditionViewer;
import theoaktroop.icare.R;
import theoaktroop.icare.Vaccine.VaciViewerActivity;

/**
 * Created by Mobile App Develop on 17-6-15.
 */
public class ProfileDetails extends Activity {
    TextView getTxt_pName,getTxt_pRelation,getTxt_pAge;
    private ProfileDataBase mProfileDataBase;
    private Profile mProfile;

    String flag;
    long eMid;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chisty_profile_de);

        intilizationOfViews();

        Intent mEIntent = getIntent();
        flag = mEIntent.getStringExtra("id");
        if (flag != null) {
            eMid = Long.parseLong(flag);

            mProfileDataBase = new ProfileDataBase(this);
            mProfile = mProfileDataBase.getAllProfilesById(eMid);

            getTxt_pName.setText(mProfile.getProfileName());
            getTxt_pRelation.setText("Identity: "+mProfile.getRelation());
            getTxt_pAge.setText("Age : "+mProfile.getAge());
            if(mProfile.getFinalImages()!=null)
            {
                byte[] outImages=mProfile.getFinalImages();
                ByteArrayInputStream imagesStream= new ByteArrayInputStream(outImages);
                Bitmap theImages= BitmapFactory.decodeStream(imagesStream);
                img.setImageBitmap(theImages);
            }
            else {
                img.setImageResource(R.drawable.per_son);
            }

        }
    }

    private void intilizationOfViews() {
        img = (ImageView)findViewById(R.id.profile_pic_output);
        getTxt_pName=(TextView)findViewById(R.id.txt_profile_name);
        getTxt_pRelation=(TextView)findViewById(R.id.txt_relation);
        getTxt_pAge=(TextView)findViewById(R.id.txt_age);

    }
    public void BtForActionAces(View view)
    {

        switch (view.getId()){
            case R.id.buttonHealth:
                Intent hIntent=new Intent(ProfileDetails.this, HealthConditionViewer.class);
                hIntent.putExtra("profile_id", String.valueOf(eMid));
                startActivity(hIntent);

                break;
            case R.id.buttonDiet:
                Intent intent = new Intent(ProfileDetails.this,DietViewer.class);
                intent.putExtra("profile_id",String.valueOf(eMid));
                startActivity(intent);
                break;

            case R.id.buttonDoctor:
                Intent fintent =new Intent(ProfileDetails.this, DoctorViewer.class);
                fintent.putExtra("profile_id", String.valueOf(eMid));
                startActivity(fintent);

                break;
            case R.id.buttonVciNation:
                Intent vintent =new Intent(ProfileDetails.this, VaciViewerActivity.class);
                vintent.putExtra("profile_id", String.valueOf(eMid));
                startActivity(vintent);

                break;
        }
       // finish();
    }

}
