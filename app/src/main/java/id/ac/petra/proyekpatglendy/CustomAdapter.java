package id.ac.petra.proyekpatglendy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataModel> {
    private List<DataModel> location;

    public CustomAdapter(@NonNull Context context, List<DataModel> loc) {
        super(context,0, loc);
        location = new ArrayList<>(loc);
    }
}
