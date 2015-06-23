package theoaktroop.icare.ProfileActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import theoaktroop.icare.R;


/**
 * Created by Sunny_PC on 6/10/2015.
 */
public class CreateiCareProfile extends Activity {
    EditText getTxt_pName,getTxt_pRelation,getTxt_pAge;
    private ProfileDataBase mProfileDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createi_crea_profile_xml);


        intilizationOfViews();
        this.mProfileDataBase=new ProfileDataBase(this);

    }

    private void intilizationOfViews() {
        getTxt_pName=(EditText)findViewById(R.id.et_p_name);
        getTxt_pRelation=(EditText)findViewById(R.id.et_p_relation);
        getTxt_pAge=(EditText)findViewById(R.id.et_p_age);

    }

    public void AddbtAction(View view){

         try {

             Editable prName=getTxt_pName.getText();
             Editable prRelation=getTxt_pRelation.getText();
             Editable prAge=getTxt_pAge.getText();

             if (!TextUtils.isEmpty(prName) && !TextUtils.isEmpty(prRelation) && !TextUtils.isEmpty(prRelation) &&
                !TextUtils.isEmpty(prAge)) {
                Profile creatNewProflie = mProfileDataBase.createNewProfile(prName.toString(), prRelation.toString(), prAge.toString());
                Intent intent = new Intent();
                intent.putExtra(ProfileListactivity.EXTRA_ADDED_PROFILE, (Serializable) creatNewProflie);
                setResult(RESULT_OK, intent);
                finish();
            }
            else {
                Toast.makeText(this, "You Must Fill all Fields!", Toast.LENGTH_LONG).show();
            }

         }
         catch (Exception e) {
            Toast.makeText(this, "You Must Fill all Fields!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
