package Service;

import Model.Course;
import Model.Staff;
import Model.Unit;

import java.io.*;
import java.util.*;

public class CourseService {

    /*
        Name: addCourse
        Purpose: addCourse to the map
        Passed: course - the course need to be added
                courseMap - the map containing all courses
        Return: true if success, false if fail
        Input: none
        Output: none
        Effect: check the course is in the database or not
                if not, add the course
        */
    public Boolean addCourse(Course course, Map<Integer, Course> courseMap) {

        if(!courseMap.isEmpty() && courseMap.containsKey(course.getcID())) {
            System.out.println("ID is already used");
            return Boolean.FALSE;
        }
        //Add course to map
        courseMap.put(course.getcID(), course);
        return Boolean.TRUE;
    }


    /*
    Name: assignUnit
    Purpose: Assign Unit to Course
    Passed: assignedUnit - unit to be assigned
            course - course to assign
    Return: true if success
            false if fail
    Input: none
    Output: error if failed
    Effect: Validate the unit, then assign it to the course
     */
    public Boolean assignUnit(Unit assignedUnit, Course course) {
        int flag = 0;
        for(Unit unit : course.getUnitList()){
            if(unit.getCode().equals(unit.getCode())){
                flag = 1;
            }
        }
        if(flag == 1){
            System.out.println("Assign Unit Error: Unit already assigned");
            return Boolean.FALSE;
        }
        if(assignedUnit.getYear() > 3 && course.getType().equals("Undergraduate")){
            System.out.println("Assign Unit Error: Unit cannot be assigned to Undergraduate");
            return Boolean.FALSE;
        }
        if(assignedUnit.getYear() <=3 && course.getType().equals("Postgraduate")){
            System.out.println("Assign Unit Error: Unit cannot be assigned to Postgraduate");
            return Boolean.FALSE;
        }
        course.getUnitList().add(assignedUnit);
        return Boolean.TRUE;
    }

    /*
    Name: deleteCourse
    Purpose: delete course from map
    Passed: id - id of course
            courseMap - the map contains course
    Return: true if success, false if failed
    Input: none
    Output: error if have
    Effect: delete course
     */
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


    /*
    Name: deleteUnitFromCourse
    Purpose: delete unit from course
    Passed: uid- unit id
            course - course to remove id from
    Return: true if success, false if failed
    Input:none
    Output:none
    Effect: check if unit is in the course or not, if yes, remove it
     */
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

    /*
    Name: findUnitInCourse
    Purpose: check unit in course or not
    Passed: uid - unit id
            course - course to check
    Return: true if have, false if do not have
    Input: none
    Output:none
    Effect:check the unit is assigned to course or not, then return boolean result
     */
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


    /*
    Name:getCourseByID
    Purpose: get the course
    Passed: id - course id
            courseMap - course database
    Return: course
    Input: none
    Output:none
    Effect: get the course
     */
    public Course getCourseByID(String id, Map<Integer, Course> courseMap) {
        if(courseMap.containsKey(id)) {
            return courseMap.get(id);

        }
        return null;
    }


    /*
    Name: writeData
    Purpose: writeData to file
    Passed: courseMap - course database
    Return:none
    Input:none
    Output:none
    Effect: Write the map collection to file using serialize
     */
    public void writeData(Map<Integer, Course> courseMap) {
        try {
            ObjectOutputStream oes = new ObjectOutputStream(new FileOutputStream("src/FileData/courseData.ser"));
            oes.writeObject(courseMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
    Name: readData
    Purpose: read Data to file
    Passed: none
    Return:courseMap - database
    Input:none
    Output:none
    Effect: read data from file using deserialize
     */
    public Map<Integer, Course> readData() {

        Map<Integer, Course> courseMap = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream("src/FileData/courseData.ser");
            int empty = fis.available();
//            Prevent EOFException while reading empty file
            if(empty !=  0){
                ObjectInputStream ois = new ObjectInputStream(fis);
                courseMap = (Map<Integer, Course>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return courseMap;
    }

}

