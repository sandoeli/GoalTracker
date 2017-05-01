package edu.uvu.my.elias.goaltracker;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    }

    public void deleteStep(View view) {
        EditText oldGoal = (EditText) layout.getChildAt(layout.getChildCount()-1);
        layout.removeView(oldGoal);
    }

    public void createGoal(View view) {

        Intent intent = new Intent();
        Goal createdGoal = new Goal();
        createdGoal.setDateStarted(new Date().toString());
        createdGoal.setDescription(((EditText)findViewById(R.id.newGoalDescTextView)).getText().toString());
        createdGoal.setTitle(((EditText)findViewById(R.id.newGoalTitleTextView)).getText().toString());
        List<Step> steps = new ArrayList<>();
        for (int j = 2; j < layout.getChildCount(); j++){
            Step step = new Step(((EditText)layout.getChildAt(j)).getText().toString());
            String data  = ((EditText)layout.getChildAt(j)).getText().toString();
            Log.d("CS3060", "createGoal: ");
            steps.add(step);
        }
        createdGoal.setStepList(steps);
        Log.d("CS3060", "createGoal: ");

        intent.putExtra("new goal", createdGoal);

        setResult(RESULT_OK, intent);

        finish();
    }
}
