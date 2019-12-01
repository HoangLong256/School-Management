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
        staffMap = staffImp.deserializeStaff();
        return staffMap;
    }

    public Map<Integer, Staff> saveData(){
        staffImp.serializeStaff(staffMap);
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
