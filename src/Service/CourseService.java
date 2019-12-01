package Service;

import Model.Course;
import Model.Staff;
import Model.Unit;

import java.io.*;
import java.util.*;

public class CourseService {

//  Add Course to Database
    public Boolean addCourse(Course course, Map<Integer, Course> courseMap) {

        if(!courseMap.isEmpty() && courseMap.containsKey(course.getcID())) {
            System.out.println("ID is already used");
            return Boolean.FALSE;
        }
        //Add course to map
        courseMap.put(course.getcID(), course);
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
    public Boolean deleteCourse(Integer id,Map<Integer, Course> courseMap) {
        if(courseMap.isEmpty()) {
            System.out.println("Course Map is empty");
            return Boolean.FALSE;
        }
        if(courseMap.containsKey(id)) {
            courseMap.remove(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


//  Delete Unit from course
    public Boolean deleteUnitFromCourse(String uid, Course course) {

        ArrayList<Unit> unitList = course.getUnitList();
        if(unitList.isEmpty()) {
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

//    Find Unit in Course
    public Boolean findUnitInCourse(String uid, Course course){
        ArrayList<Unit> unitList = course.getUnitList();
        if(unitList.isEmpty()) {
            return Boolean.FALSE;
        }
        for(int i = 0 ; i<unitList.size() ; i++) {
            if(uid.equals(unitList.get(i).getCode())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

//    Get Course by ID
    public Course getCourseByID(String id, Map<Integer, Course> courseMap) {
        if(courseMap.containsKey(id)) {
            return courseMap.get(id);

        }
        return null;
    }


//    Write Data to file
    public void writeData(Map<Integer, Course> courseMap) {
        try {
            ObjectOutputStream oes = new ObjectOutputStream(new FileOutputStream("src/FileData/courseData.ser"));
            oes.writeObject(courseMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    Read Data from file
    public Map<Integer, Course> readData() {

        Map<Integer, Course> courseMap = new HashMap<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/FileData/courseData.ser"));
            courseMap = (Map<Integer, Course>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return courseMap;
    }

}

