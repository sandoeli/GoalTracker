package edu.uvu.my.elias.goaltracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Elias on 4/26/2017.
 */

@SuppressWarnings("serial")
public class Goal implements Serializable {
    private String mDateStarted;
    private String mTitle;
    private String mDescription;
    private List<Step> stepList = new ArrayList<>();
    private double completionPercent;

    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }

    public Goal(){
        mDateStarted = new Date().toString();
    }

    public Goal(String mTitle, String mDescription) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
    }

    public String getDateStarted() {
        return mDateStarted;
    }

    public void setDateStarted(String mDateStarted) {
        this.mDateStarted = mDateStarted;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void addStep(Step step){
        stepList.add(step);
    }
}
