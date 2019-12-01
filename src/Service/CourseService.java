package Service;

import Model.Course;
import Model.Staff;
import Model.Unit;

import java.io.*;
import java.util.*;

public class CourseService {

//  Add Course to Database
    public Boolean addCourse(Course courseDetail, Map<String, Course> courseMap) {

        if(!courseMap.isEmpty() && courseMap.containsKey(courseDetail.getCode())) {
            System.out.println("This course code already being used");
            return Boolean.FALSE;
        }
        //Add course to map
        courseMap.put(courseDetail.getCode(), courseDetail);
        return Boolean.TRUE;
    }

//  Assign Unit to Course
    public Boolean assignUnit(Unit assignedUnit, Course course) {
        int flag = 0;
        for(Unit unit : course.getUnitList()){
            if(unit.getCode().equals(unit.getCode())){
                flag = 1;
            }
        }
        if(flag == 1){
            System.out.println("Unit already assigned");
            return Boolean.FALSE;
        }
        course.getUnitList().add(assignedUnit);
        return Boolean.TRUE;
    }

//  Delete Course from Database
    public Boolean deleteCourse(String code,Map<String, Course> courseMap) {
        if(courseMap.isEmpty()) {
            System.out.println("Course Map is empty");
            return Boolean.FALSE;
        }
        if(courseMap.containsKey(code)) {
            courseMap.remove(code);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


//  Delete Unit from course
    public Boolean deleteUnitFromCourse(String uid, Course course) {
        ArrayList<Unit> unitList = course.getUnitList();
        if(unitList.isEmpty()) {
            System.out.println("Unit List is empty");
            return Boolean.FALSE;
        }
        for(int i = 0 ; i<unitList.size() ; i++) {
            if(uid.equals(unitList.get(i).getCode())) {
                unitList.remove(i);
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }


//    Write Data to file
    public void writeData(Map<String, Course> courseMap) {
        try {
            ObjectOutputStream oes = new ObjectOutputStream(new FileOutputStream("src/FileData/courseData.ser"));
            oes.writeObject(courseMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    Read Data from file
    public Map<String, Course> readData() {

        Map<String, Course> courseMap = new HashMap<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/FileData/courseData.ser"));
            courseMap = (Map<String, Course>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return courseMap;
    }

}

