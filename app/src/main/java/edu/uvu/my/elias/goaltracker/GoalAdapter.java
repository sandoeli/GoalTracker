package edu.uvu.my.elias.goaltracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Elias on 4/27/2017.
 */

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.MyViewHolder> {
    private List<Goal> goalList;
    private Context context;

    public GoalAdapter(List<Goal> goalList, Context context) {
        this.goalList = goalList;
        this.context = context;
    }

    @Override
    //This method will be called when viewholder is created
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_goal_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    //called after onCreateViewHolder to bind data.
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Goal goal = goalList.get(position);

        holder.titleTextView.setText(goal.getTitle());
        holder.shortDescriptionTextView.setText(goal.getDescription());
        holder.dateTextView.setText(goal.getDateStarted().toString());
        holder.longDescriptionTextView.setText("test");

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent displayInformationIntent = new Intent(view.getContext(), DescriptionActivity.class);

                final int result = 1;
                displayInformationIntent.putExtra("Goal", goalList.get(position));

                view.getContext().startActivity(displayInformationIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView;
        public TextView shortDescriptionTextView;
        public TextView dateTextView;
        public TextView longDescriptionTextView;
        public LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            final LayoutInflater root = LayoutInflater.from(context);
            final View rootView = root.inflate(R.layout.description, null);
            titleTextView = (TextView)  itemView.findViewById(R.id.titleTextView);
            shortDescriptionTextView = (TextView) itemView.findViewById(R.id.shortDescriptionTextView);
            dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
            longDescriptionTextView = (TextView) rootView.findViewById(R.id.longDescriptionTextView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.cardViewLinearLayout);
            Log.d(TAG, "MyViewHolder: ");
        }
    }
}
