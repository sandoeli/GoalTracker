package edu.uvu.my.elias.goaltracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Goal> goalList;
    private List<Step> stepList;
    public SQLiteDatabase goalsDB = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goalList = new ArrayList<>();
        stepList = new ArrayList<>();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createDatabase();

        recyclerView = (RecyclerView) findViewById(R.id.goal_recycler_view);
        //every item on recyclerView has a fixed size
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //populate list w/ test data

        adapter = new GoalAdapter(goalList, this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            //Testing
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), addGoalActivity.class);

                final int result = 1;

                startActivityForResult(i, result);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Goal goal = (Goal) data.getSerializableExtra("new goal");
        Gson gson = new Gson();

        String arrayList = gson.toJson(goal.getStepList());

        goalsDB.execSQL("INSERT INTO goals (date, title, description, steps) VALUES ('" +
                goal.getDateStarted() + "','" + goal.getTitle() + "','" + goal.getDescription() +
                "','" + arrayList + "');");

        try {
            getGoals(gson, arrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();
    }

    public void createDatabase(){
        try{
            Goal goal1 = new Goal("Lose weight", "Lose 15 lbs in 3 months");
            goal1.setDateStarted(new Date().toString());
            Step step1 = new Step("Sleep early"), step2 = new Step("Eat less"), step3 = new Step("Workout everyday");
            ArrayList<Step> goal1Steps = new ArrayList<>();
            goal1Steps.add(step1);
            goal1Steps.add(step2);
            goal1Steps.add(step3);
            goal1.setStepList(goal1Steps);

            /*JSONObject goal1Json = new JSONObject();
            goal1Json.put("steps1", new JSONArray(goal1.getStepList()));
            String arrayList = goal1Json.toString();*/

            Gson gson = new Gson();

            String arrayList = gson.toJson(goal1Steps);


            goalsDB = this.openOrCreateDatabase("MyGoals", MODE_PRIVATE, null);

            goalsDB.execSQL("CREATE TABLE IF NOT EXISTS goals " + "(id integer primary key, " +
                    "date VARCHAR, title VARCHAR, description VARCHAR, steps VARCHAR);");

            goalsDB.execSQL("INSERT INTO goals (date, title, description, steps) VALUES ('" +
            goal1.getDateStarted() + "','" + goal1.getTitle() + "','" + goal1.getDescription() +
                    "','" + arrayList + "');");

            try {
                getGoals(gson, arrayList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            Log.e("CONTACTS ERROR", "Error creating database");
        }
    }

    public void getGoals(Gson gson, String arrayList) throws JSONException {
        Cursor cursor = goalsDB.rawQuery("SELECT * FROM goals", null);
        Goal goal;

        int idColumn = cursor.getColumnIndex("id");
        int dateColumn = cursor.getColumnIndex("date");
        int titleColumn = cursor.getColumnIndex("title");
        int descColumn = cursor.getColumnIndex("description");
        int stepsColumn = cursor.getColumnIndex("steps");

        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            do {
                String id = cursor.getString(idColumn);
                String date = cursor.getString(dateColumn);
                String title = cursor.getString(titleColumn);
                String description = cursor.getString(descColumn);
                String steps = cursor.getString(stepsColumn);

                Type type = new TypeToken<ArrayList<Step>>() {
                }.getType();
                List<Step> stepList = gson.fromJson(arrayList, type);
                goal = new Goal(title, description);
                goal.setDateStarted(date);
                goal.setStepList(stepList);

            } while (cursor.moveToNext());

            goalList.add(goal);
        }
    }
}
