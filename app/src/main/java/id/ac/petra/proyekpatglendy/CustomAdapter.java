package id.ac.petra.proyekpatglendy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<DataModel> id;
    ArrayList<DataModel> city;
    ArrayList<DataModel> street;
    LayoutInflater inflater;

    public CustomAdapter(@NonNull Context c, List<DataModel> id) {
        this.c = c;
        this.id = (ArrayList<DataModel>) id;
        this.city = (ArrayList<DataModel>) city;
        this.street = (ArrayList<DataModel>) street;
        inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_locations,null);
        TextView locate = (TextView) view.findViewById(R.id.listview1);
        locate.setText((CharSequence) city);
        return view;
    }
}
