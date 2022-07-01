package id.ac.petra.proyekpatglendy;

import android.os.Bundle;

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

public class HomeFragment extends Fragment {
    ListView mylv;
    SwipeRefreshLayout refresh;
    CustomAdapter cAdapter;
    ExecutorService executor;
    Handler handler;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        requireActivity().setTitle("Vending Machine Locations");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mylv = view.findViewById(R.id.listview);
        getLocation();
        refresh = view.findViewById(R.id.swipe_refresh);
        refresh.setOnRefreshListener(this::getLocation);
        return view;
    }

    private void getLocation() {
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            List<DataModel> result = null;
            try {
                result = getREST();
                refresh.setRefreshing(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<DataModel> finalResult = result;
            handler.post(() -> {
                if (getActivity() != null && finalResult != null) {
                    cAdapter = new CustomAdapter(getActivity(),finalResult);
                    mylv.setAdapter(cAdapter);
                    mylv.setClickable(true);
                    /*mylv.setOnClickListener((adapterView, view1, i, l) -> {
                        Intent intent = new Intent(getContext(), UserFragment.class);
                        intent.putExtra("LocId", finalResult.get(i).getId());
                        intent.putExtra("Location",finalResult.get(i).getLoc());
                        startActivity(intent);
                    });*/
                }
            });
        });
    }

    private List<DataModel> getREST() throws Exception {
        String url = "http://192.168.1.10:7000/Location";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent","Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("Send Get Request to: " +url);
        System.out.println("Response Code: " +responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String input;
        StringBuilder response = new StringBuilder();
        while ((input = in.readLine()) != null){
            response.append(input);
        }
        in.close();

        JSONArray myArray = new JSONArray(response.toString());
        List<DataModel> result = new ArrayList<>();
        System.out.println("View: " +result);
        for (int i = 0; i < myArray.length(); i++) {
            JSONObject arrObj = myArray.getJSONObject(i);
            DataModel u = new DataModel();
            System.out.println("View2: " +u);
            System.out.println("View3: " +i);
            u.setId(arrObj.getInt("id"));
            u.setCity(arrObj.getString("city"));
            u.setStreet(arrObj.getString("street"));
            result.add(u);
        }
        return result;
    }
}