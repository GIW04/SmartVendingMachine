package id.ac.petra.proyekpatglendy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemFragment extends Fragment {
    ListView mylv2;
    SwipeRefreshLayout refresh2;
    CustomAdapter2 cAdapter2;
    ExecutorService executor;
    Handler handler;

    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        requireActivity().setTitle("Item List");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item,container,false);
        mylv2 = (ListView) view.findViewById(R.id.lv2);
        getItem();
        refresh2 = view.findViewById(R.id.swipe_refresh2);
        refresh2.setOnRefreshListener(this::getItem);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void getItem() {
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        executor.execute(()->{
            List<DataModel2> results = null;
            try {
                results = getREST();
                refresh2.setRefreshing(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<DataModel2> finalsResult = results;
            handler.post(()->{
               if(getActivity() != null && finalsResult != null) {
                   cAdapter2 = new CustomAdapter2(getActivity(),finalsResult);
                   mylv2.setAdapter(cAdapter2);
                   mylv2.setClickable(true);
                   mylv2.setOnItemClickListener((adapterView, view, i, l) -> {
                       Intent intent = new Intent(getContext(),login.class);
                       intent.putExtra("ItemId", finalsResult.get(i).getId());
                       intent.putExtra("Name", finalsResult.get(i).getName());
                       intent.putExtra("Price", finalsResult.get(i).getPrice());
                       startActivity(intent);
                   });
                }
            });
        });
    }

    private List<DataModel2> getREST() throws Exception {
        String url = "http://192.168.1.7:7000/menu";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.connect();

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String input;
        StringBuffer responses = new StringBuffer();
        while ((input = in.readLine()) != null){
            responses.append(input);
        }
        in.close();

        JSONArray myArray = new JSONArray(responses.toString());
        List<DataModel2> results = new ArrayList<DataModel2>();
        for (int i = 0; i < myArray.length(); i++){
            JSONObject arrObj = myArray.getJSONObject(i);
            DataModel2 u = new DataModel2();
            u.setId(arrObj.getInt("id"));
            u.setName(arrObj.getString("name"));
            u.setPrice(arrObj.getString("price"));
        }
        return results;
    }
}