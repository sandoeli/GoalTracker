package edu.uvu.my.elias.goaltracker;

import java.io.Serializable;

/**
 * Created by Elias on 4/27/2017.
 */

@SuppressWarnings("serial")
public class Step implements Serializable {
    private String title;
    //private String mDescription;

    public Step(String title) {
        this.title = title;
    }


    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
