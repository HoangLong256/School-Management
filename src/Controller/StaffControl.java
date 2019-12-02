package Controller;

import Service.StaffService;
import Model.Staff;

import java.util.HashMap;
import java.util.Map;

public class StaffControl {
    private static StaffControl instance;
    static Map<Integer, Staff> staffMap =  new HashMap<Integer, Staff>();
    private StaffService staffImp = new StaffService();



    //    Constructor
    private StaffControl(){};

//    Singleton Pattern
    /*
    Name: getInstance
    Purpose: to create only one object in the application lifetime
    Passed: none
    Return: none
    Input: none
    Output: none
    Effect: prevent this object is created outside
     */
    public static synchronized StaffControl getInstance(){
        if(instance == null){
            instance = new StaffControl();

        }
        return instance;
    }

    public Boolean addStaff(int id, String name, String address){
        Staff staff = new Staff();
        staff.setSid(id);
        staff.setName(name);
        staff.setAddress(address);
        if(staffImp.addStaff(staff, staffMap)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    /*
    Name: loadData()
    Purpose: load data to database
    Passed: none
    Return: staffMap -database
    Input: none
    Output: none
    Effect: assign data to courseMap
     */
    public Map<Integer, Staff> loadData(){
        staffMap = staffImp.readData();
        return staffMap;
    }

    /*
    Name: saveData
    Purpose: save data to map
    Passed: none
    Return: staffMap - database
    Input: none
    Output: none
    Effect: write data to file
     */
    public Map<Integer, Staff> saveData(){
        staffImp.writeData(staffMap);
        return staffMap;
    }

    /*
    Name: getStaffMap and setStaffMap
    Purpose: get and set courseMap
    Passed: staffMap - database
    Return: staffMap - database
    Input: none
    Output: none
    Effect: getter and setter
     */
    public Map<Integer, Staff> getStaffMap(){
        return  staffMap;
    }
    public void setStaffMap(Map<Integer, Staff> map){
        this.staffMap = map;
    }

    /*
    Name: getStaffByID
    Purpose: find staff in the database
    Passed: sid- staff id
            staffMap - database
    Return: staff
    Input: none
    Output:none
    Effect:find staff
     */
    public Staff getStaffByID(Integer sid){
        return staffImp.getStaffByID(sid, staffMap);
    }
}
