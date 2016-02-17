package haftaaa.voidmain.com.instamojotest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    private EditText email;
    private EditText password;
    private Button login;
    private TextView register;
    private AppServer appServer;
    String emailid;
    String passwordtext;
    private PrefManager prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = new PrefManager(this);
        appServer = new AppServer();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginbutton);
        register = (TextView) findViewById(R.id.register);



        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                emailid = email.getText().toString().trim();
                passwordtext = password.getText().toString().trim();
                loginBackground();



            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loginBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg="";

                try {

                    msg = appServer.login(emailid , passwordtext);
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


                if(msg.contains("Success"))
                {
                    String[] array = msg.split("\\s*,\\s*");

                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    prefs.createLogin(array[1].toString().trim(),emailid);

                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                }

            }
        }.execute(null, null, null);
    }

}
