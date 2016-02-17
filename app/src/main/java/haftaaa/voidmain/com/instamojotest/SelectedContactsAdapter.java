package haftaaa.voidmain.com.instamojotest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sanju on 12-02-2016.
 */
public class SelectedContactsAdapter extends BaseAdapter {

    Context context;
    ArrayList<Contacts> contact_list = new ArrayList<Contacts>();

    public SelectedContactsAdapter(Context context, ArrayList<Contacts> contact_list)
    {
        this.context = context;
        this.contact_list = contact_list;
    }


    @Override
    public int getCount() {
        return contact_list.size();
    }

    @Override
    public Object getItem(int position) {
        return contact_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.selected_contact_item,null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.selected_contact);


         name.setText(contact_list.get(position).getName());

        return convertView;

    }
}
