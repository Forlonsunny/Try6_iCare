package theoaktroop.icare.ProfileActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import theoaktroop.icare.R;


/**
 * Created by Sunny_PC on 6/10/2015.
 */
public class CreateiCareProfile extends Activity {
    EditText getTxt_pName,getTxt_pRelation;
    TextView getTxt_pAge;
 private Button  getBtForDate;
    private ProfileDataBase mProfileDataBase;
    private static final int SELECT_PICTURE = 1;

    private AgeCalculation age;
    private int startYear=1992;
    private int startMonth=6;
    private int startDay=15;

    private String selectedDate;
    private ImageView img;
    byte[] finalImages;
    int  checkSelectedImage=0;
    String flag;
    long eMid;
    private Profile mProfile;
    private Button AddUpbuttProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createi_crea_profile_xml);
        mProfileDataBase = new ProfileDataBase(this);

        intilizationOfViews();
        Intent mEIntent = getIntent();
        flag = mEIntent.getStringExtra("id");
        age=new AgeCalculation();
        if (flag != null) {
            eMid = Long.parseLong(flag);
            AddUpbuttProfile.setText("Update Profile");

            mProfile = mProfileDataBase.getAllProfilesById(eMid);

            getTxt_pName.setText(mProfile.getProfileName());
            getTxt_pRelation.setText(mProfile.getRelation());
            getBtForDate.setText(mProfile.getDateOfBirth().toString());
            getTxt_pAge.setText(mProfile.getAge());


            System.out.println("From Update "+mProfile.getDateOfBirth().toString());

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
        AddUpbuttProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b=(Button)v;
                String btText=b.getText().toString();




                if(btText.equals("Save Profile"))
                {
                    try {
                         System.out.println("From add ");

                        Editable prName=getTxt_pName.getText();
                        Editable prRelation=getTxt_pRelation.getText();

                        String prAge=getTxt_pAge.getText().toString();

                        if (!TextUtils.isEmpty(prName) && !TextUtils.isEmpty(prRelation) && !TextUtils.isEmpty(prRelation)   ) {
                            Profile creatNewProflie = mProfileDataBase.createNewProfile(prName.toString(), prRelation.toString(),selectedDate.toString(),prAge,finalImages);
                            Intent intent = new Intent();
                            intent.putExtra(ProfileListactivity.EXTRA_ADDED_PROFILE, (Serializable) creatNewProflie);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();
                        }

                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();
                   }

                }
                else if(btText.equals("Update Profile"))
                {
                    try {
                        System.out.println("From update ");

                        Editable prName=getTxt_pName.getText();
                        Editable prRelation=getTxt_pRelation.getText();
                        String prAge=getTxt_pAge.getText().toString();

                        if (!TextUtils.isEmpty(prName) && !TextUtils.isEmpty(prRelation) && !TextUtils.isEmpty(prRelation)
                                  ) {

                            mProfileDataBase.upDateProfile( eMid,prName.toString(), prRelation.toString(),selectedDate.toString(), prAge,finalImages);
                           // mProfileDataBase.createNewProfile(prName.toString(), prRelation.toString(), prAge.toString(),finalImages);
//                            Intent intent = new Intent();
//                            intent.putExtra(ProfileListactivity.EXTRA_ADDED_PROFILE, (Serializable) creatNewProflie);
//                            setResult(RESULT_OK, intent);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();
                        }

                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();
                    }
                }
                else
                    System.out.println("From else ");
            }
        });


      getBtForDate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              try{
                  showDialog(1);



              }
              catch (Exception e)
              {

              }
          }
      });
    }
    private void intilizationOfViews() {

        AddUpbuttProfile=(Button)findViewById(R.id.addProfileBt);
        img = (ImageView)findViewById(R.id.profile_pic_input);
        getTxt_pName=(EditText)findViewById(R.id.et_p_name);
        getTxt_pRelation=(EditText)findViewById(R.id.et_p_relation);
        getBtForDate=(Button)findViewById(R.id.tv_for_datePiker);
        getTxt_pAge=(TextView)findViewById(R.id.et_p_age);


    }




    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        startYear, startMonth, startDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            startYear=selectedYear;
            startMonth=selectedMonth;
            startDay=selectedDay;
            age.setDateOfBirth(startYear, startMonth, startDay);
            getBtForDate.setText(""+selectedDay+"/"+(startMonth+1)+"/"+startYear);
            selectedDate=""+selectedDay+"/"+(startMonth+1)+"/"+startYear;
           // birthDate.setText(""+selectedDay+":"+(startMonth+1)+":"+startYear);
            calculateAge();
        }
    };

    private void calculateAge()
    {   age.getCurrentDate();
        age.calcualteYear();
        age.calcualteMonth();
        age.calcualteDay();
        //Toast.makeText(getBaseContext(), "click the resulted button"+age.getResult() , Toast.LENGTH_SHORT).show();
        getTxt_pAge.setText(age.getResult());
    }
  public void SetImagesBt(View view)
  {   img.setImageResource(0);
      img.setImageDrawable(null);
      Intent intent = new Intent();
      intent.setType("image/*");
      intent.setAction(Intent.ACTION_GET_CONTENT);
      startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
  }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            img.setImageBitmap(null);
            img.destroyDrawingCache();
            img.setImageResource(0);
            img.setImageDrawable(null);
//            if (requestCode == SELECT_PICTURE) {
            img.setImageURI(data.getData());
            BitmapDrawable d = (BitmapDrawable) img.getDrawable();
            Bitmap image = d.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 10, stream);
            finalImages = stream.toByteArray();
            checkSelectedImage = 1;


        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
