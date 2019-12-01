package Service;

import Model.Staff;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class StaffService {

//    Add Staff to database
    public Boolean addStaff(Staff staff, Map<Integer, Staff> staffMap) {
        if(staffMap.containsKey(staff.getSid())) {
            System.out.println("Staff ID is already used");
            return Boolean.FALSE;
        }
        staffMap.put(staff.getSid(), staff);
        return Boolean.TRUE;
    }

    
    public void displayStaffs(Map<Integer, Staff> staffMap) {
        staffMap.forEach((k,v) -> System.out.println(k + "\t\t" + v.getName()));
    }


    public Staff getStaffByID(Integer sid, Map<Integer, Staff> staffMap) {
        if(staffMap.containsKey(sid)) {
            return staffMap.get(sid);

        }
        return null;
    }
    public void serializeStaff(Map<Integer, Staff> staffMap) {
        try {
            ObjectOutputStream oes = new ObjectOutputStream(new FileOutputStream("src/FileData/staffData.ser"));
            oes.writeObject(staffMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<Integer, Staff>  deserializeStaff() {
        Map<Integer, Staff> staffMap = null ;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/FileData/staffData.ser"));
            staffMap = (Map<Integer, Staff>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("The current staff file is empty");
        }
        return staffMap;
    }


}
