package edu.uvu.my.elias.goaltracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Elias on 4/27/2017.
 */


public class DescriptionActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Step> stepList;
    private TextView titleTextView;
    private TextView descriptionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);

        recyclerView = (RecyclerView) findViewById(R.id.stepsReyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        titleTextView = (TextView) findViewById(R.id.descActivityTitleTextView);
        descriptionTextView = (TextView) findViewById(R.id.longDescriptionTextView);

        Intent mainListIntent = getIntent();

        Goal goal = (Goal) mainListIntent.getSerializableExtra("Goal");

        stepList = goal.getStepList();

        adapter = new StepAdapter(stepList, this);
        recyclerView.setAdapter(adapter);

        titleTextView.setText(goal.getTitle());
        descriptionTextView.setText(goal.getDescription());


        Log.d("cs3060", "onCreate: ");
    }
}
