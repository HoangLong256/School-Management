package Model;

import java.io.Serializable;

public class Staff implements Serializable {


    // Define all required attributes of staff
    // This variable present for unique staff ID
    private int sid;
    // This variable present for the staff name
    private String name;
    // This variable present for the staff address
    private String address;

    // Generate a constructor for staff class
//    public Staff(int sid, String name, String address) {
////        this.sid = sid;
////        this.name = name;
////        this.address = address;
////    }


    // Getter and Setter of all variables
    public int getSid() {
        return sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


}
