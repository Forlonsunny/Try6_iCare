package theoaktroop.icare.SplashActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import theoaktroop.icare.ProfileActivity.ProfileListactivity;
import theoaktroop.icare.R;


public class MainSplashScreen extends Activity {
//hello it  is test for github
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_splash_screen);

// METHOD 1

        /****** Create Thread that will sleep for 2 seconds *************/
        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 2000 seconds
                    sleep(1500);

                    // After 5 seconds redirect to another intent
                    Intent i=new Intent(getBaseContext(),ProfileListactivity.class);
                    startActivity(i);

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}
