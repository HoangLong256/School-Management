package Controller;

import Implementation.CourseImpl;
import Model.Course;
import Model.Unit;

import java.util.HashMap;
import java.util.Map;

public class CourseControl {
    private static CourseControl instance;

    private CourseImpl courseImp = new CourseImpl();



    static Map<String, Course> courseMap = new HashMap<>();

    private CourseControl(){};
    public static synchronized CourseControl getInstance(){
        if(instance == null){
            instance = new CourseControl();
        }
        return instance;
    }

    public Map<String, Course> loadData(){
        courseMap = courseImp.deserializeCourse("course.txt");
        return courseMap;
    }



    public Map<String, Course> saveData(){
        courseImp.serializeCourse(courseMap, "course.txt");
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


}

