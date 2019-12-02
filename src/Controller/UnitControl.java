package Controller;

import Service.UnitService;
import Model.Unit;

import java.util.HashMap;
import java.util.Map;

public class UnitControl {
    private static UnitControl instance;
    private Map<String, Unit> unitMap = new HashMap<>();
    private UnitService unitImp = new UnitService();



    //    Constructor
    private UnitControl(){};

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
    public static synchronized UnitControl getInstance(){
        if(instance == null){
            instance = new UnitControl();
        }
        return instance;
    }

    /*
    Name: loadData()
    Purpose: load data to database
    Passed: none
    Return: unitMap -database
    Input: none
    Output: none
    Effect: assign data to courseMap
     */
    public Map<String, Unit> loadData(){
        unitMap = unitImp.readData();
        return unitMap;
    }

    /*
    Name: saveData
    Purpose: save data to map
    Passed: none
    Return: unitMap - database
    Input: none
    Output: none
    Effect: write data to file
     */
    public Map<String, Unit> saveData(){
        unitImp.writeData(unitMap);
        return unitMap;
    }

    /*
    Name: getUnitMap and setUnitMap
    Purpose: get and set courseMap
    Passed: unitMap - database
    Return: unitMap - database
    Input: none
    Output: none
    Effect: getter and setter
     */
    public void setUnitMap(Map<String, Unit> map ){
        this.unitMap = map;
    }
    public Map<String, Unit> getUnitMap(){
        return unitMap;
    }

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
    public Boolean addUnit(Unit unit){
        return  unitImp.addUnit(unit, unitMap);

    }

    /*
    Name: searchUnit
    Purpose: search unit in database
    Passed: code - unit code
            unitMap - database
    Return: true if found , false if not
    Input: none
    Output: none
    Effect: find unit
     */
    public Boolean searchUnit(String code){
        return unitImp.searchUnitCode(code, unitMap);
    }

    /*
    Name: getUnit
    Purpose: get unit in database
    Passed: code - unit code
            unitMap - database
    Return: found unit
    Input: none
    Output: none
    Effect: get unit
     */
    public Unit getUnit(String code) {
        return unitImp.getUnitByCode(code, unitMap);
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
    public Boolean deleteUnit(String code){
        return unitImp.deleteUnit(code, unitMap);
    }


}

