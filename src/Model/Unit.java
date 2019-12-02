package Model;

import java.io.Serializable;


public class Unit implements Serializable {
    private String code;
    private String name;
    private Staff examiner;
    private Staff lecturer;
    private String semester;
    private int year;


    /*
    Name: Getter and Setter
    Purpose: Get and Set variables for model
    Passed: model variables
    Return: model variables
    Input: none
    Output:none
    Effect: Get and Set variables
    */
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Staff getExaminer() {
        return examiner;
    }
    public void setExaminer(Staff examiner) {
        this.examiner = examiner;
    }
    public Staff getLecturer() {
        return lecturer;
    }
    public void setLecturer(Staff lecturer) {
        this.lecturer = lecturer;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

}
