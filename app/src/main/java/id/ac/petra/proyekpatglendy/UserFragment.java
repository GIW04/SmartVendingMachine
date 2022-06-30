package id.ac.petra.proyekpatglendy;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Objects;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link UserFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class UserFragment extends Fragment {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email;
    Button signOutBtn;

    public UserFragment() {
    }

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment UserFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static UserFragment newInstance(String param1, String param2) {
//        UserFragment fragment = new UserFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().setTitle("User Account");
    }
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        signOutBtn = view.findViewById(R.id.SignOut_Btn);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(requireActivity().getApplicationContext(), gso);
        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(requireActivity());
        if(acc!=null){
            String personName = acc.getDisplayName();
            String personEmail = acc.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }
        signOutBtn.setOnClickListener(view1 -> {
            gsc.signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    requireActivity().finish();
                    //Fragment account = new Intent(UserFragment.this,login.class);
                    //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.signin,accounts).commit();
                    Intent i = new Intent(getActivity(),login.class);
                    startActivity(i);
                }
            });
        });

        return view;
    }
}