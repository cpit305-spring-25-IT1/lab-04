package cpit305.fcit.kau.edu.sa;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int id;
    private double gpa;

    public Student(String name, int id, double gpa) {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
    }

    // Getters and setters
    public String getName() { return name; }
    public int getId() { return id; }
    public double getGpa() { return gpa; }
}