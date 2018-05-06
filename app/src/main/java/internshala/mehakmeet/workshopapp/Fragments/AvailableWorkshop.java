package internshala.mehakmeet.workshopapp.Fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import internshala.mehakmeet.workshopapp.Activities.MainActivity;
import internshala.mehakmeet.workshopapp.model.DataProvider;
import internshala.mehakmeet.workshopapp.R;
import internshala.mehakmeet.workshopapp.Adapters.RvAdapter;
import internshala.mehakmeet.workshopapp.SqliteDB.WorkshopDbHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class AvailableWorkshop extends Fragment {

    WorkshopDbHelper workshopDbHelper;
    SQLiteDatabase sqLiteDatabase;

    RecyclerView workshop_RV;
    Cursor cursor;
    RvAdapter rvAdapter;
    ArrayList<DataProvider> workshopList;

    public AvailableWorkshop() {
        // Required empty public constructor
    }
    public void onResume(){
        super.onResume();

        // Set title bar
        ((MainActivity)getActivity()).setActionBarTitle("Available Workshops");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_available_workshop, container, false);

        workshop_RV=v.findViewById(R.id.workshops_rv);
        workshopList=new ArrayList<>();
        rvAdapter=new RvAdapter(getActivity(),workshopList);


        workshopDbHelper=new WorkshopDbHelper(getActivity().getApplicationContext());
        sqLiteDatabase= workshopDbHelper.getReadableDatabase();
        cursor=workshopDbHelper.getInfo(sqLiteDatabase);
        int c=0;
        if(cursor.moveToFirst())
        {
            do{

                String name,desc,date,time;
                name=cursor.getString(0);
                desc=cursor.getString(1);
                date=cursor.getString(2);
                time=cursor.getString(3);
                Log.e("DESC",desc);

                DataProvider dataProvider=new DataProvider(name,desc,date,time);
                workshopList.add(dataProvider);
                c++;
                cursor.moveToNext();
            }while(c<6);//JUST FOR PROTOTYPING else use while(cursor.moveToNext()) if inserting like a form

            RecyclerView.LayoutManager new_Layout=new LinearLayoutManager(getActivity());
            workshop_RV.setLayoutManager(new_Layout);
            workshop_RV.setAdapter(rvAdapter);
        }



        return v;
    }



}
