package Model;

import java.io.Serializable;

public class Staff implements Serializable {

    private int sid;
    private String name;
    private String address;



    /*
        Name: Getter and Setter
        Purpose: Get and Set variables for model
        Passed: model variables
        Return: model variables
        Input: none
        Output:none
        Effect: Get and Set variables
     */
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
