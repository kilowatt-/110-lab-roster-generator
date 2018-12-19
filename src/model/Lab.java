package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lab implements Comparable<Lab> {
    private final String labID;
    private List<Student> students;

    public Lab(String labID) {
        this.labID = labID;
        students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public void addStudent(Student s) {
        students.add(s);

        if (s.getLab() != this)
            s.setLab(this);
    }

    public void removeStudent(Student s) {
        students.remove(s);

        s.setLab(null);
    }

    public boolean containsStudent(Student s) {
        return students.contains(s);
    }

    public String getLabID() {
        return labID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lab lab = (Lab) o;
        return Objects.equals(labID, lab.labID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(labID);
    }

    @Override
    public String toString() {
        if (!labID.equals(""))
            return this.labID;

        return "<no lab assigned>";
    }

    @Override
    public int compareTo(Lab o) {
        return this.labID.compareTo(o.getLabID());
    }
}
