package Service;

import Model.Staff;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class StaffService {
    /**
     * This method helps to add course.
     * @param staff - Contains all the details of staff such as id or name ,...
     * @param staffMap - Contains units information in the staff map
     * @return String - Contains "OK" or "Error"
     */
    public Boolean addStaff(Staff staff, Map<Integer, Staff> staffMap) {
        if(staffMap.containsKey(staff.getSid())) {
            System.out.println("This course already being stored");
            return Boolean.FALSE;
        }
            //Add course to map
        staffMap.put(staff.getSid(), staff);
        return Boolean.TRUE;
    }

    public void showAllStaff(Map<Integer, Staff> staffMap) {
        Iterator<Map.Entry<Integer, Staff>> iterator = staffMap.entrySet().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()){
            Map.Entry<Integer, Staff> entry =  iterator.next();
            Staff staff = entry.getValue();
            stringBuilder.append(entry.getKey()).append('\t').append(staff.getName()).append('\n');
        }
        System.out.println(stringBuilder);
    }

    /**
     * This method helps get staff by ID.
     * @param sid - Contains all the details of staff such as id or name ,...
     * @param staffMap - Contains units information in the staff map
     * @return String - Contains "OK" or "Error"
     */
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

    public Boolean searchID(Integer sid, Map<Integer, Staff> staffMap) {
        if(staffMap.containsKey(sid)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
