package internshala.mehakmeet.workshopapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import internshala.mehakmeet.workshopapp.model.DashModel;
import internshala.mehakmeet.workshopapp.model.DataProvider;
import internshala.mehakmeet.workshopapp.R;

/**
 * Created by MEHAKMEET on 07-05-2018.
 */

public class DashAdapter extends RecyclerView.Adapter<DashAdapter.ViewHolder> {

    private Context applicationcontext;
    private ArrayList<DashModel> workshopList;

    public DashAdapter(Context applicationContext, ArrayList<DashModel> workshopList) {

        this.applicationcontext=applicationContext;
        this.workshopList=workshopList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.name.setText(workshopList.get(position).getName());
        holder.desc.setText(workshopList.get(position).getDesc());
        holder.date.setText(workshopList.get(position).getDate());
        holder.time.setText(workshopList.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return workshopList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,desc,date,time;

        ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_work);
            desc=itemView.findViewById(R.id.desc_work);
            date=itemView.findViewById(R.id.date_work);
            time=itemView.findViewById(R.id.time_work);

        }
    }
}
