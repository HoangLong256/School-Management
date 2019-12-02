package Service;

import Model.Staff;
import Model.Unit;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class UnitService {


    public Boolean addUnit(Unit unit, Map<String, Unit> unitMap) {

        if(!unitMap.isEmpty() && unitMap.containsKey(unit.getCode())) {
            System.out.println( "This unit code already being used");
            return Boolean.FALSE;
        }
        //Add course to map
        unitMap.put(unit.getCode(), unit);
        return Boolean.TRUE;
    }





    public Boolean deleteUnit(String uid,Map<String, Unit> unitMap) {
        String message;
        if(unitMap.isEmpty()) {
            message = "There is currently none added unit";
            return Boolean.FALSE;
        }
        if(unitMap.containsKey(uid)) {
            unitMap.remove(uid);
            message = "OK";
            return Boolean.TRUE;
        } else {
            message = "Error";
            return Boolean.FALSE;
        }

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

    public void serializeUnit(Map<String, Unit> unitMap) {
        try {
            ObjectOutputStream oes = new ObjectOutputStream(new FileOutputStream("src/FileData/unitData.ser"));
            oes.writeObject(unitMap);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public Map<String, Unit> deserializeUnit() {
        Map<String, Unit> unitMap = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream("src/FileData/unitData.ser");
            int empty = fis.available();
//            Prevent EOFException while reading empty file
            if(empty !=  0){
                ObjectInputStream ois = new ObjectInputStream(fis);
                unitMap = (Map<String, Unit>) ois.readObject();
                ois.close();
                fis.close();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return unitMap;

    }

}
