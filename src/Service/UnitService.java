package Service;

import Model.Staff;
import Model.Unit;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class UnitService {

    /*
        Name: addUnit
        Purpose: add unit to database
        Passed: unit - unit to be added
                unitMap - database
        Return: true if success, false if failed
        Input: none
        Output: none
        Effect: add unit
     */
    public Boolean addUnit(Unit unit, Map<String, Unit> unitMap) {

        if(!unitMap.isEmpty() && unitMap.containsKey(unit.getCode())) {
            System.out.println( "This unit code already being used");
            return Boolean.FALSE;
        }
        //Add course to map
        unitMap.put(unit.getCode(), unit);
        return Boolean.TRUE;
    }

    /*
    Name: deleteUnit
    Purpose: delete unit from database
    Passed: uid - unit id
            unitMap - database
    Return: true if success, false if failed
    Input: none
    Output: none
    Effect: delete unit
     */
    public Boolean deleteUnit(String uid,Map<String, Unit> unitMap) {
        String message;
        if(unitMap.isEmpty()) {
            return Boolean.FALSE;
        }
        if(unitMap.containsKey(uid)) {
            unitMap.remove(uid);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }

    }


    /*
    Name: searchUnitByCode
    Purpose: search unit in database
    Passed: code - unit code
            unitMap - database
    Return: true if found , false if not
    Input: none
    Output: none
    Effect: find unit
     */
    public Boolean searchUnitCode(String code, Map<String, Unit> unitMap) {
        if(unitMap.containsKey(code)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /*
    Name: getUnitByCode
    Purpose: get unit in database
    Passed: code - unit code
            unitMap - database
    Return: found unit
    Input: none
    Output: none
    Effect: get unit
     */
    public Unit getUnitByCode(String code, Map<String, Unit> unitMap) {
       if(unitMap.containsKey(code)) {
            return unitMap.get(code);
        }
       return null;
    }

    /*
    Name: writeData
    Purpose: writeData to file
    Passed: unitMao - unit database
    Return:none
    Input:none
    Output:none
    Effect: Write the map collection to file using serialize
     */
    public void writeData(Map<String, Unit> unitMap) {
        try {
            ObjectOutputStream oes = new ObjectOutputStream(new FileOutputStream("src/FileData/unitData.ser"));
            oes.writeObject(unitMap);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /*
    Name: readData
    Purpose: read Data to file
    Passed: none
    Return:unitMap - database
    Input:none
    Output:none
    Effect: read data from file using deserialize
     */
    public Map<String, Unit> readData() {
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
