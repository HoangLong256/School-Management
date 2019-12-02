package Controller;

import Service.CourseService;
import Model.Course;
import Model.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseControl {
    private static CourseControl instance;

    private CourseService courseImp = new CourseService();
    private  Map<Integer, Course> courseMap = new HashMap<>();

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
    private CourseControl(){};
    public static synchronized CourseControl getInstance(){
        if(instance == null){
            instance = new CourseControl();
        }
        return instance;
    }

    /*
    Name: loadData()
    Purpose: load data to database
    Passed: none
    Return: courseMap -database
    Input: none
    Output: none
    Effect: assign data to courseMap
     */
    public Map<Integer, Course> loadData(){
        courseMap = courseImp.readData();
        return courseMap;
    }



    /*
    Name: saveData
    Purpose: save data to map
    Passed: none
    Return: courseMap - database
    Input: none
    Output: none
    Effect: write data to file
     */
    public Map<Integer, Course> saveData(){
        courseImp.writeData(courseMap);
        return courseMap;
    }

    /*
    Name: getCourseMap and setCourseMap
    Purpose: get and set courseMap
    Passed: courseMap - database
    Return: courseMap - database
    Input: none
    Output: none
    Effect: getter and setter
     */
    public Map<Integer , Course> getCourseMap(){
        return courseMap;
    }
    public void setCourseMap(Map<Integer, Course> courseMap) {
        this.courseMap = courseMap;
    }

    /*
    Name: deleteCourse
    Purpose: delete course from map
    Passed: id - id of course
    Return: true if success, false if failed
    Input: none
    Output: error if have
    Effect: delete course
     */
    public Boolean deleteCourse(Integer id){
        return courseImp.deleteCourse(id,courseMap);
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
    public Boolean assignUnit(Unit assignedUnit, Course course){
        return courseImp.assignUnit(assignedUnit,course);

    }

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
    public Boolean addCourse(Course course){
        return courseImp.addCourse(course, courseMap);
    }

    /*
    Name: deleteUnitFromCourse
    Purpose: delete unit from course
    Passed: code- unit id
    Return: true if success, false if failed
    Input:none
    Output:none
    Effect: loop through database
            check if unit is in the course or not, if yes, remove it
     */
    public void removeUnit(String code){
        courseMap.forEach((k,v) -> {
            courseImp.deleteUnitFromCourse(code,v);
        });
        saveData();
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
    public Course getCourseByID(String id){
        return courseImp.getCourseByID(id, courseMap);
    }

    /*
    Name: getCoursesHaveUnit
    Purpose: check unit in course or not
    Passed: unitCode - unit code
    Return: list - list of courses that have unit
    Input: none
    Output:none
    Effect:check the unit is assigned to course or not, then return list of course that have unit
     */
    public ArrayList<Course> getCoursesHaveUnit(String unitCode){
        ArrayList<Course> list = new ArrayList<>();
        for(Map.Entry<Integer, Course> entry : courseMap.entrySet()) {
            if(courseImp.findUnitInCourse(unitCode,entry.getValue())) {
                list.add(entry.getValue());
            }
        }
        return list;
    }


}

