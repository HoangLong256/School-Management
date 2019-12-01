package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {



    private int cID;
    private String code;
    private String name;
    private String type;
    private String location;
    private Staff director;
    private Staff deputy;
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
    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

}
