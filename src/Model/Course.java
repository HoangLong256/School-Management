package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    // Define all required attributes of course
    // This variable present for unique course code-- must be 3 digits long
    private String code;
    // This variable present for the course name
    private String name;
    // This variable present for the course type -- post or undergraduate
    private String type;
    // This variable present for the  course location -- SGS or HN
    private String location;
    // This variable present for the course director
    private Staff director;
    // This variable present for the course deputy
    private Staff deputy;
    // This variable present for the units belong to this code
    private ArrayList<Unit> unitList;


    // Getter and Setter for all above variables
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Staff getDirector() {
        return director;
    }
    public void setDirector(Staff director) {
        this.director = director;
    }
    public Staff getDeputy() {
        return deputy;
    }
    public void setDeputy(Staff deputy) {
        this.deputy = deputy;
    }
    public ArrayList<Unit> getUnitList() {
        return unitList;
    }
    public void setUnitList(ArrayList<Unit> unitList) {
        this.unitList = unitList;
    }

}
