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
    private CourseControl(){};
    public static synchronized CourseControl getInstance(){
        if(instance == null){
            instance = new CourseControl();
        }
        return instance;
    }

//    Load database from file
    public Map<Integer, Course> loadData(){
        courseMap = courseImp.readData();
        return courseMap;
    }


//  Sava database to file
    public Map<Integer, Course> saveData(){
        courseImp.writeData(courseMap);
        return courseMap;
    }

//    Getter and Setter course database
    public Map<Integer , Course> getCourseMap(){
        return courseMap;
    }

    public void setCourseMap(Map<Integer, Course> courseMap) {
        this.courseMap = courseMap;
    }

//    Delete Course from database
    public Boolean deleteCourse(Integer id){
        return courseImp.deleteCourse(id,courseMap);
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

//    Get Course by ID
    public Course getCourseByID(String id){
        return courseImp.getCourseByID(id, courseMap);
    }

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

