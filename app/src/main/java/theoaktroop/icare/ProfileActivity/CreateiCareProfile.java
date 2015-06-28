package theoaktroop.icare.ProfileActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;

import theoaktroop.icare.R;


/**
 * Created by Sunny_PC on 6/10/2015.
 */
public class CreateiCareProfile extends Activity {
    EditText getTxt_pName,getTxt_pRelation,getTxt_pAge;
    private ProfileDataBase mProfileDataBase;
    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createi_crea_profile_xml);


        intilizationOfViews();
        this.mProfileDataBase=new ProfileDataBase(this);

    }

    private void intilizationOfViews() {
        img = (ImageView)findViewById(R.id.profile_pic_input);
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
  public void SetImagesBt(View view)
  {
      Intent intent = new Intent();
      intent.setType("image/*");
      intent.setAction(Intent.ACTION_GET_CONTENT);
      startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
  }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();

                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
               // img.setImageURI(selectedImageUri);
                //img.setImageURI(Uri.parse(selectedImagePath));
                if(selectedImagePath!=null && selectedImagePath!="")// <--CHECK FILENAME IS NOT NULL
                {
                    File f = new File(selectedImagePath);
                    if(f.exists()) // <-- CHECK FILE EXISTS OR NOT
                    {
                        Drawable d = Drawable.createFromPath(selectedImagePath);
                        img.setImageDrawable(d);

                    }
                }
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
