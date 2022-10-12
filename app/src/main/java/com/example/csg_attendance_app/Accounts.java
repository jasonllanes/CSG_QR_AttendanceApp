package com.example.csg_attendance_app;

public class Accounts {

    public String fullname;
    public String department;
    public String year;
    public String course;
    public String time;
    public Accounts() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Accounts(String fullname, String course, String year,String time) {
        this.fullname = fullname;
        this.course = course;
        this.year = year;
        this.time = time;
    }
}
