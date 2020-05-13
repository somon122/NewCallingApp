package com.world_tech_point.calling_app.CallingDetails;

public class Contacts {

    private String mName;
    private String mNumber;

    public Contacts() {
    }

    public Contacts(String mName, String mNumber) {
        this.mName = mName;
        this.mNumber = mNumber;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }
}
