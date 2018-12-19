package model;

public class Student {
    private String fname;
    private String lname;
    private String sid;
    private Lab lab;

    public Student(String fname, String lname, String sid) {
        this.fname = fname;
        this.lname = lname;
        this.sid = sid;
    }

    public String getSID() {
        return sid;
    }

    public void setSID(String sid) {
        this.sid = sid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;

        if (!lab.containsStudent(this))
            lab.addStudent(this);
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
