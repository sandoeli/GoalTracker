package edu.uvu.my.elias.goaltracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Elias on 4/28/2017.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.MyViewHolder> {
    private List<Step> stepList;
    private Context context;

    public StepAdapter(List<Step> stepList, Context context) {
        this.stepList = stepList;
        this.context = context;
    }
    //This method will be called when viewholder is created
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepAdapter.MyViewHolder holder, int position) {
        Step step = stepList.get(position);

        holder.descriptionTextView.setText(step.getTitle());
    }

    @Override
    public int getItemCount() {

        return  stepList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;

        public MyViewHolder(View itemView) {

            super(itemView);
            LayoutInflater root = LayoutInflater.from(context);
            View view = root.inflate(R.layout.description, null);
            titleTextView = (TextView) view.findViewById(R.id.descActivityTitleTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.textOut);
        }
    }
}
