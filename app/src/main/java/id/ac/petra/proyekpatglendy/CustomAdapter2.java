package id.ac.petra.proyekpatglendy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter2 extends ArrayAdapter<DataModel2> {
    private List<DataModel2> ListItemFull;

    public CustomAdapter2(@NonNull Context context, @NonNull List<DataModel2> ItemList){
        super(context,0,ItemList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent,false);
        }
        TextView textViewItem = convertView.findViewById(R.id.listview_item);
        TextView textViewPrice = convertView.findViewById(R.id.listview_price);
        DataModel2 DataModel2 = getItem(position);
        if(DataModel2 != null){
            textViewItem.setText(DataModel2.getName());
            textViewPrice.setText(DataModel2.getPrice());
        }
        return convertView;
    }
}
