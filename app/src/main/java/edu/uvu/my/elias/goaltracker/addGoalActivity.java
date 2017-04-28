package edu.uvu.my.elias.goaltracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * Created by Elias on 4/27/2017.
 */

public class addGoalActivity extends AppCompatActivity {
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_goal);

        layout = (LinearLayout) findViewById(R.id.goalLinearLayout);
        layout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT
                , LayoutParams.WRAP_CONTENT);
    }

    public void addMoreSteps(View view) {
        EditText newGoal = new EditText(this);
        newGoal.setHint("Next step");
        layout.addView(newGoal);
        Log.d("CS3060", "addMoreSteps: " + layout.getChildCount());

        /*final LayoutInflater root = LayoutInflater.from(view.getContext());
        final View rootView = root.inflate(R.layout.row, null);
        EditText textOut = (EditText) findViewById(R.id.textOut);
        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.addGoalLayout);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams
                (ActionBar.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        EditText newGoal = new EditText(view.getContext());
        newGoal.setHint("Next step");
        newGoal.setLayoutParams(layoutParams);
        layout.addView(newGoal);*/
    }
}
