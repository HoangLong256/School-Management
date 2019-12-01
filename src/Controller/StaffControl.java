package Controller;

import Implementation.StaffImpl;
import Model.Staff;

import java.util.HashMap;
import java.util.Map;

public class StaffControl {
    private static StaffControl instance;
    private Map<Integer, Staff> staffMap =  new HashMap<Integer, Staff>();
    private StaffImpl staffImp = new StaffImpl();



    //    Constructor
    private StaffControl(){};

//    Singleton Pattern
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


    public Map<Integer, Staff> loadData(){
        staffMap = staffImp.deserializeStaff("staff.txt");
        return staffMap;
    }

    public Map<Integer, Staff> saveData(){
        staffImp.serializeStaff(staffMap, "staff.txt");
        return staffMap;
    }

    public Map<Integer, Staff> getStaffMap(){
        return  staffMap;
    }

    public void setStaffMap(Map<Integer, Staff> map){
        this.staffMap = map;
    }

    public void showAllStaff(){
        staffImp.showAllStaff(staffMap);
    }

    public Staff getStaffByID(Integer sid){
        return staffImp.getStaffByID(sid, staffMap);
    }
}
