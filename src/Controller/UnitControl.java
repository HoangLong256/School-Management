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
    public static synchronized UnitControl getInstance(){
        if(instance == null){
            instance = new UnitControl();
        }
        return instance;
    }

    public Map<String, Unit> loadData(){
        unitMap = unitImp.deserializeUnit();
        return unitMap;
    }

    public Map<String, Unit> saveData(){
        unitImp.serializeUnit(unitMap);
        return unitMap;
    }

    public void setUnitMap(Map<String, Unit> map ){
        this.unitMap = map;
    }

    public Map<String, Unit> getUnitMap(){
        return unitMap;
    }

    public Boolean addUnit(Unit unit){
        return  unitImp.addUnit(unit, unitMap);

    }

    public Boolean searchUnit(String code){
        return unitImp.searchUnitCode(code, unitMap);
    }

    public Unit getUnit(String code) {
        return unitImp.getUnitByCode(code, unitMap);
    }

    public Boolean deleteUnit(String code){
        return unitImp.deleteUnit(code, unitMap);
    }
}

