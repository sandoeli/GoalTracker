package edu.uvu.my.elias.goaltracker;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Elias on 4/26/2017.
 */

public class GoalList {
    private static GoalList sGoalList;

    private List<Goal> mGoals;

    public static GoalList get(Context context) {
        if (sGoalList == null)
            sGoalList = new GoalList(context);
        return sGoalList;
    }

    private GoalList(Context context){
        mGoals = new ArrayList<>();
    }

    public List<Goal> getGoals(){
        return mGoals;
    }

}
