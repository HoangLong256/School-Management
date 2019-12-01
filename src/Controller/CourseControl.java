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

    private CourseControl(){};
    public static synchronized CourseControl getInstance(){
        if(instance == null){
            instance = new CourseControl();
        }
        return instance;
    }

    public Map<String, Course> loadData(){
        courseMap = courseImp.deserializeCourse();
        return courseMap;
    }



    public Map<String, Course> saveData(){
        courseImp.serializeCourse(courseMap);
        return courseMap;
    }

    public Map<String , Course> getCourseMap(){
        return courseMap;
    }

    public void setCourseMap(Map<String, Course> courseMap) {
        this.courseMap = courseMap;
    }

    public Boolean deleteCourse(String code){
        if(courseImp.deleteCourse(code, courseMap)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean assignUnit(Unit assignedUnit, Course course){
        if(courseImp.assignUnit(assignedUnit, course)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }

    public Boolean addCourse(Course course){
        if(courseImp.addCourse(course, courseMap)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void removeUnit(String code){
        courseMap.forEach((k,v) -> {
            courseImp.removeUnit(code,v);
        });
        saveData();
    }


}

