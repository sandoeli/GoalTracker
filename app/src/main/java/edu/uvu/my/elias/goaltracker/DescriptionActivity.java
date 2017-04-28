package edu.uvu.my.elias.goaltracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Elias on 4/27/2017.
 */

public class DescriptionActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.description);

        Intent mainListIntent = getIntent();

        String data = mainListIntent.getExtras().getString("Goal");

        TextView description = (TextView)findViewById(R.id.longDescriptionTextView);

    }
}
