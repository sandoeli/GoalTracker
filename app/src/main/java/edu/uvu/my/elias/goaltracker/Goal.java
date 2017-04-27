package edu.uvu.my.elias.goaltracker;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Elias on 4/26/2017.
 */

public class Goal {
    private Date mDateStarted;
    private String mTitle;
    private String mDescription;
    private UUID mID;

    public Goal(){
        mID = UUID.randomUUID();
        mDateStarted = new Date();
    }
    public Date getDateStarted() {
        return mDateStarted;
    }

    public void setDateStarted(Date mDateStarted) {
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

    public UUID getID() {
        return mID;
    }

    public void setID(UUID mID) {
        this.mID = mID;
    }
}