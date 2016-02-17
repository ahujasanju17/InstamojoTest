package haftaaa.voidmain.com.instamojotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sanju on 13-02-2016.
 */
public class RequestActivity extends AppCompatActivity {

    private ArrayList<String> array_list = new ArrayList<>();
    private RequestAdapter adapter;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
       String name_list = getIntent().getStringExtra("namelist");

        list = (ListView) findViewById(R.id.request_list);


        name_list = name_list.substring(0, name_list.length() - 1);
        String[] array = name_list.split(",");
        for(int a = 0 ; a < array.length ; a++)
        {
            array_list.add(array[a]);
        }
        adapter = new RequestAdapter(this, array_list);
        list.setAdapter(adapter);


    }
}
