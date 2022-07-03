package id.ac.petra.proyekpatglendy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.concurrent.ExecutorService;

public class ItemMenus extends AppCompatActivity {
    ListView mylv2;
    SwipeRefreshLayout refresh2;
    CustomAdapter2 cAdapter2;
    ExecutorService executor;
    Handler handler;

    public ItemMenus(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}