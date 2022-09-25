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

    public Accounts(String fullname, String department, String year, String course,String time) {
        this.fullname = fullname;
        this.department = department;
        this.year = year;
        this.course = course;
        this.time = time;
    }
}
