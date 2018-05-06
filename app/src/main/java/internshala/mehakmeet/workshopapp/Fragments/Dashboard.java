package internshala.mehakmeet.workshopapp.Fragments;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;

import internshala.mehakmeet.workshopapp.Activities.MainActivity;
import internshala.mehakmeet.workshopapp.Adapters.DashAdapter;
import internshala.mehakmeet.workshopapp.R;
import internshala.mehakmeet.workshopapp.model.DashModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {

    RecyclerView dashboard_RV;
    String SavedList;
    public static ArrayList<DashModel> dashList;
    public static DashAdapter dashAdapter;
    public void onResume(){
        super.onResume();

        // Set title bar
        ((MainActivity)getActivity()).setActionBarTitle("Dashboard");

    }

    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_dashboard, container, false);

        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);


        dashboard_RV=v.findViewById(R.id.dash_rv);
        dashboard_RV.setLayoutManager(new LinearLayoutManager(getActivity()));
        dashAdapter=new DashAdapter(getActivity().getApplicationContext(),dashList);
        dashboard_RV.setAdapter(dashAdapter);

        SavedList = new Gson().toJson(dashList);

        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("My_SAVED_LIST", SavedList).apply();

        return v;
    }

}
