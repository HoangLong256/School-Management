package Service;

import Model.Staff;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class StaffService {


    /*
    Name: addStaff
    Purpose: add staff to database
    Passed: staff - staff to add
            staffMap - database
    Return: True if success, false if failed
    Input: none
    Output: none
    Effect: add staff
     */
    public Boolean addStaff(Staff staff, Map<Integer, Staff> staffMap) {
        if(staffMap.containsKey(staff.getSid())) {
            System.out.println("Staff ID is already used");
            return Boolean.FALSE;
        }
        staffMap.put(staff.getSid(), staff);
        return Boolean.TRUE;
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
    public Staff getStaffByID(Integer sid, Map<Integer, Staff> staffMap) {
        if(staffMap.containsKey(sid)) {
            return staffMap.get(sid);

        }
        return null;
    }

    /*
    Name: writeData
    Purpose: writeData to file
    Passed: staffMap - staff database
    Return:none
    Input:none
    Output:none
    Effect: Write the map collection to file using serialize
     */
    public void writeData(Map<Integer, Staff> staffMap) {
        try {
            ObjectOutputStream oes = new ObjectOutputStream(new FileOutputStream("src/FileData/staffData.ser"));
            oes.writeObject(staffMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Name: readData
    Purpose: read Data to file
    Passed: none
    Return:staffMap - database
    Input:none
    Output:none
    Effect: read data from file using deserialize
     */
    public Map<Integer, Staff> readData() {
        Map<Integer, Staff> staffMap = null ;
        try {
            FileInputStream fis = new FileInputStream("src/FileData/staffData.ser");
            int empty = fis.available();
            if(empty != 0 ){
                ObjectInputStream ois = new ObjectInputStream(fis);
                staffMap = (Map<Integer, Staff>) ois.readObject();
                fis.close();
                ois.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException  e){
            e.printStackTrace();
        }
        return staffMap;
    }


}
