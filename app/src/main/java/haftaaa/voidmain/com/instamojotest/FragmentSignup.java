package haftaaa.voidmain.com.instamojotest;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sanju on 15-02-2016.
 */
public class FragmentSignup extends Fragment {

    private EditText username;
    private EditText email;
    private EditText password;
    private Button signupbutton;
    private String usertext;
    private String emailtext;
    private String passwordtext;
    private AppServer appServer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signup, container, false);


        appServer = new AppServer();
        username = (EditText) v.findViewById(R.id.username);
        email = (EditText) v.findViewById(R.id.emailid);
        password = (EditText) v.findViewById(R.id.password);
        signupbutton = (Button) v.findViewById(R.id.signupbutton);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usertext = username.getText().toString().trim();
                emailtext = email.getText().toString().trim();
                passwordtext = password.getText().toString().trim();
                registerBackground();
            }
        });


        return v;
    }

    private void registerBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg="";

                try {

                    msg = appServer.register(usertext,emailtext,passwordtext);
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

                if(msg.equalsIgnoreCase("success"))
                {

                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }

            }
        }.execute(null, null, null);
    }



}
