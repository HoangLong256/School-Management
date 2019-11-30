package Model;

import java.io.Serializable;

/**
 * This class created to store information about unit
 *
 * @author s3634096
 *
 */

public class Unit implements Serializable {
    // Define all required attributes of unit
    // This variable present for unique unit code
    private String code;
    // This variable present for the unit name
    private String name;
    // This variable present for the chief examiner
    private Staff examiner;
    // This variable present for the lecturer
    private Staff lecturer;
    // This variable present for the current semesterEnum
    private String semester;


    // Getter and Setter of all variables
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

}
