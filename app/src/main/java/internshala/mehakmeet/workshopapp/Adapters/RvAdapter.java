package internshala.mehakmeet.workshopapp.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import github.ishaan.buttonprogressbar.ButtonProgressBar;
import internshala.mehakmeet.workshopapp.login.Login;
import internshala.mehakmeet.workshopapp.Fragments.Dashboard;
import internshala.mehakmeet.workshopapp.model.DashModel;
import internshala.mehakmeet.workshopapp.model.DataProvider;
import internshala.mehakmeet.workshopapp.R;

/**
 * Created by MEHAKMEET on 07-05-2018.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private Activity activity;
    private ArrayList<DataProvider> workshopList;
    public RvAdapter(Activity activity, ArrayList<DataProvider> workshopList) {

        this.activity=activity;
        this.workshopList=workshopList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workshop_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        int flag=0;
        holder.name.setText(workshopList.get(position).getName());
        holder.desc.setText(workshopList.get(position).getDesc());
        holder.date.setText(workshopList.get(position).getDate());
        holder.time.setText(workshopList.get(position).getTime());

        if(PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext()).contains("My_SAVED_LIST"))
        {
            for(int i=0;i<Dashboard.dashList.size();i++){
                if(position==Dashboard.dashList.get(i).getPos()){
                    holder.apply_btn.stopLoader();
                    flag=1;
                }
            }
        }

        final int finalFlag = flag;
        holder.apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalFlag !=1){



                    if(Login.HAS_LOGGED_IN==1){

                        holder.apply_btn.startLoader();
                        DashModel dashModel=new DashModel(workshopList.get(position).getName(),workshopList.get(position).getDesc(),
                                workshopList.get(position).getDate(),workshopList.get(position).getTime(),position);

                        Dashboard.dashList.add(dashModel);
                        Dashboard.dashAdapter.notifyItemInserted(Dashboard.dashList.size()-1);

                        holder.apply_btn.setClickable(false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                holder.apply_btn.stopLoader();
                            }
                        }, 3000);
                    }
                    else
                    {
                        Intent i= new Intent(activity, Login.class);
                        activity.startActivity(i);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return workshopList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,desc,date,time;
        ButtonProgressBar apply_btn;


        ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_work);
            desc=itemView.findViewById(R.id.desc_work);
            date=itemView.findViewById(R.id.date_work);
            time=itemView.findViewById(R.id.time_work);
            apply_btn=itemView.findViewById(R.id.apply_btn);
        }
    }
}
