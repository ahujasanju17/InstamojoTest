package haftaaa.voidmain.com.instamojotest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sanju on 15-02-2016.
 */
public class MainActivity extends AppCompatActivity {

    private TextView username;
    private TextView email;
    private FloatingActionButton fab;
    private Button requests;
    private PrefManager prefs;
    private AppServer appServer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        username = (TextView)findViewById(R.id.user_id);
        email = (TextView) findViewById(R.id.user_email);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        requests = (Button) findViewById(R.id.requests);
        prefs  = new PrefManager(this);
        appServer = new AppServer();

        username.setText(prefs.getUserName());
        email.setText(prefs.getUserEmail());


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Check Permissions Now
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            1);
                }
                else {

                    Intent i = new Intent(MainActivity.this, ContactSelectActivity.class);
                    startActivity(i);
                }
            }
        });

        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveListBackground();
            }
        });





    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent i = new Intent(MainActivity.this, ContactSelectActivity.class);
                    startActivity(i);


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void retrieveListBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg="";

                try {

                    msg = appServer.getRequests(prefs.getUserEmail());
                    Log.d("Server Response", msg);


                } catch (Exception ex) {
                    msg = "Error :" + ex.getMessage();
                    Log.d("RegisterActivity", "Error: " + msg);
                }
                Log.d("RegisterActivity", "AsyncTask completed: " + msg);
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {


                String msg_to_send = msg;
                Log.d("msg_to_send",msg_to_send);
                Intent i = new Intent(MainActivity.this,RequestActivity.class);
            i.putExtra("namelist",msg_to_send);
                startActivity(i);

            }
        }.execute(null, null, null);
    }

}
