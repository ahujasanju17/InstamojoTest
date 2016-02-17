package haftaaa.voidmain.com.instamojotest;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sanju on 15-02-2016.
 */
public class ContactSelectActivity extends AppCompatActivity
{

    private ArrayList<Contacts> contacts;
    private ContactsAdapter adapter;
    private ArrayList<Contacts> selected_contacts;
    private Button Add;
    private Button Done;
    private TextView selectedcontacts;
    private SelectedContactsAdapter selected_adapter;
    private String contact_to_send;
    private  String phone_to_send;
    private AppServer appServer;
    StringBuilder checkedcontacts;
    StringBuilder selected_phones;
    StringBuilder s = new StringBuilder();
    private PrefManager prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Done = (Button) findViewById(R.id.done);
        Add = (Button) findViewById(R.id.add);
        prefs = new PrefManager(this);
        appServer = new AppServer();
        contacts = new ArrayList<>();

        selected_contacts = new ArrayList<>();

        getAllContacts(this.getContentResolver());

        ListView lv = (ListView) findViewById(R.id.listcontact);

        adapter = new ContactsAdapter();

        selectedcontacts = (TextView) findViewById(R.id.listselectedcontacts);


        lv.setAdapter(adapter);
        lv.setItemsCanFocus(false);
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.toggle(position);
            }
        });


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkedcontacts = new StringBuilder();
                selected_phones = new StringBuilder();

                selected_contacts.clear();
                for (int i = 0; i < contacts.size(); i++)

                {

                    if (adapter.mCheckStates.get(i) == true) {

                        if (selected_contacts.size() < 5) {


                            selected_contacts.add(contacts.get(i));

                            checkedcontacts.append(contacts.get(i).getName().toString() + ",");
                            s.append(contacts.get(i).getPhone().toString() + ",");

                        } else

                            Toast.makeText(getApplicationContext(), "Please select maximum of 5 Contacts!", Toast.LENGTH_LONG).show();


                    }

                }

                selectedcontacts.setText(checkedcontacts);

            }
        });


        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contact_to_send = selectedcontacts.getText().toString().trim();
                phone_to_send = s.toString().trim();
                Log.d("phone_to_send",phone_to_send);
                Log.d("contacttosend", contact_to_send);

                putContactsBackground();
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



    public void getAllContacts(ContentResolver cr) {

        Cursor phones = cr.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                null, null);
        while (phones.moveToNext()) {
            String name = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.d("The names : ", name);
            Contacts contact = new Contacts(name,phoneNumber);
            contacts.add(contact);

        }

        phones.close();
    }


    private void putContactsBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg="";

                try {

                    msg = appServer.putContacts(prefs.getUserEmail(),contact_to_send,phone_to_send);
                    Log.d("Server Response", msg);


                }
                catch (Exception ex) {
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

                    Intent i = new Intent(ContactSelectActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                }

            }
        }.execute(null, null, null);
    }

    class ContactsAdapter extends BaseAdapter implements
            CompoundButton.OnCheckedChangeListener {
        private SparseBooleanArray mCheckStates;
        LayoutInflater mInflater;
        TextView tv1, tv;
        CheckBox cb;

        ContactsAdapter() {
            mCheckStates = new SparseBooleanArray(contacts.size());
            mInflater = (LayoutInflater) ContactSelectActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return contacts.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub

            return 0;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub
            View vi = convertView;
            if (convertView == null)
                vi = mInflater.inflate(R.layout.contact_list_item, null);
            tv = (TextView) vi.findViewById(R.id.name);
            cb = (CheckBox) vi.findViewById(R.id.checkbox);
            tv.setText(contacts.get(position).getName());
            cb.setTag(position);
            cb.setChecked(mCheckStates.get(position, false));
            cb.setOnCheckedChangeListener(this);

            return vi;
        }

        public boolean isChecked(int position) {
            return mCheckStates.get(position, false);
        }

        public void setChecked(int position, boolean isChecked) {
            mCheckStates.put(position, isChecked);
        }

        public void toggle(int position) {
            setChecked(position, !isChecked(position));
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // TODO Auto-generated method stub

            mCheckStates.put((Integer) buttonView.getTag(), isChecked);

        }

    }

}
