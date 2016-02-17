package haftaaa.voidmain.com.instamojotest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sanju on 15-02-2016.
 */
public class SignupActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText password;
    private Button signupbutton;
    private String usertext;
    private String emailtext;
    private String passwordtext;
    private AppServer appServer;
    private TextView logintext;
    private PrefManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup);

        appServer = new AppServer();
        prefs = new PrefManager(this);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.emailid);
        password = (EditText) findViewById(R.id.password);
        signupbutton = (Button) findViewById(R.id.signupbutton);

        logintext = (TextView) findViewById(R.id.logintext);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usertext = username.getText().toString().trim();
                emailtext = email.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                passwordtext = password.getText().toString().trim();

                if(!emailtext.matches(emailPattern) || TextUtils.isEmpty(emailPattern))
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid EmailId!",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(usertext))
                {
                    Toast.makeText(getApplicationContext(),"Enter username!",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(passwordtext))
                {
                    Toast.makeText(getApplicationContext(),"Enter password!",Toast.LENGTH_LONG).show();
                }
              else
                {

                    registerBackground();

                }
                }
        });

        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


    }

    private void registerBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";

                try {

                    msg = appServer.register(usertext, emailtext, passwordtext);
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


                    Intent i = new Intent(SignupActivity.this,MainActivity.class);
                    prefs.createLogin(usertext , emailtext);
                    startActivity(i);
                    finish();


            }
        }.execute(null, null, null);

    }
}
