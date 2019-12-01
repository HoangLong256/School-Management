package Controller;

import Service.CourseService;
import Model.Course;
import Model.Unit;

import java.util.HashMap;
import java.util.Map;

public class CourseControl {
    private static CourseControl instance;

    private CourseService courseImp = new CourseService();
    private  Map<String, Course> courseMap = new HashMap<>();

//    Singleton Pattern
    private CourseControl(){};
    public static synchronized CourseControl getInstance(){
        if(instance == null){
            instance = new CourseControl();
        }
        return instance;
    }

//    Load database from file
    public Map<String, Course> loadData(){
        courseMap = courseImp.readData();
        return courseMap;
    }


//  Sava database to file
    public Map<String, Course> saveData(){
        courseImp.writeData(courseMap);
        return courseMap;
    }

//    Getter and Setter course database
    public Map<String , Course> getCourseMap(){
        return courseMap;
    }

    public void setCourseMap(Map<String, Course> courseMap) {
        this.courseMap = courseMap;
    }

//    Delete Course from database
    public Boolean deleteCourse(String code){
        return courseImp.deleteCourse(code,courseMap);
    }

//    Assign Unit to database
    public Boolean assignUnit(Unit assignedUnit, Course course){
        return courseImp.assignUnit(assignedUnit,course);

    }

//  Add Course to database
    public Boolean addCourse(Course course){
        return courseImp.addCourse(course, courseMap);
    }

//    Remove Unit from Course
    public void removeUnit(String code){
        courseMap.forEach((k,v) -> {
            courseImp.deleteUnitFromCourse(code,v);
        });
        saveData();
    }


}

