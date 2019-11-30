package Implementation;

import Model.Course;
import Model.Staff;
import Model.Unit;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class UnitImpl {


    public String addUnit(Unit unitDetails, Map<String, Unit> unitMap) {

        if(!unitMap.isEmpty() && unitMap.containsKey(unitDetails.getCode())) {
            return "This unit code already being used";
        }
        //Add course to map
        unitMap.put(unitDetails.getCode(), unitDetails);
        return "OK";
    }



    public String deleteUnit(String uid,Map<String, Unit> unitMap) {
        String message;
        if(unitMap.isEmpty()) {
            message = "There is currently none added unit";
        }
        if(unitMap.containsKey(uid)) {
            unitMap.remove(uid);
            message = "OK";
        } else {
            message = "Error";
        }
        //Return result as String
        return message;
    }

    public void showUnit(Unit unitDetail) {
        System.out.println("Code: " + unitDetail.getCode().toString());
        System.out.println("Name: " + unitDetail.getName().toString());
        System.out.println("Examiner: " + unitDetail.getExaminer().toString());
        System.out.println("Lecturer: " + unitDetail.getLecturer().toString());
    }


    public String assignExaminer(Staff staff, Unit unit) {
        // Implement all required condition
        if(unit.getExaminer() == null || unit.getExaminer().getSid() != staff.getSid()) {
            unit.setExaminer(staff);
            return "OK"; }
        return "Error";
    }


    public String assignLecturer(Staff staff, Unit unit) {
        // Implement all required condition
        if(unit.getLecturer() == null || unit.getLecturer().getSid() != staff.getSid()) {
            unit.setLecturer(staff);
            return "OK"; }
        return "Error";
    }

    public Boolean searchUnitCode(String code, Map<String, Unit> unitMap) {
        if(unitMap.containsKey(code)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Unit getUnitByCode(String code, Map<String, Unit> unitMap) {
//        if(unitMap.containsKey(code)) {
            return unitMap.get(code);
//        }
//        return null;
    }

    public void serializeUnit(Map<String, Unit> unitMap, String fileName) {
        try {
            ObjectOutputStream oes = new ObjectOutputStream(new FileOutputStream(fileName));
            oes.writeObject(unitMap);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public Map<String, Unit> deserializeUnit(String fileName) {
        Map<String, Unit> unitMap = new HashMap<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            unitMap = (Map<String, Unit>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("The current course file is empty");
        }
        return unitMap;
    }

}
