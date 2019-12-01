package Implementation;
/**
 * This class created to implement the logic for class course
 *
 * @author s3634096
 *
 */
import Model.Course;
import Model.Staff;
import Model.Unit;

import java.io.*;
import java.util.*;

public class CourseImpl {

    /**
     * This method helps to add course.
     * @param courseDetail - Contains all the details of course such as code , .
     * @param courseMap - Contains units information in the map
     * @return String - Contains "OK" or "Error"
     */
    public Boolean addCourse(Course courseDetail, Map<String, Course> courseMap) {

        if(!courseMap.isEmpty() && courseMap.containsKey(courseDetail.getCode())) {
            System.out.println("This course code already being used");
            return Boolean.FALSE;
        }
        //Add course to map
        courseMap.put(courseDetail.getCode(), courseDetail);
        return Boolean.TRUE;
    }

    /**
     * This method helps to assign unit to course.
     * @param unitDetail - Contains all the details of assigned unit such as uid or name
     * @param course - Contains all information of course such as unitList or code
     * @return String - Contains "OK" or "Error"
     */
    public Boolean assignUnit(Unit unitDetail, Course course) {
        if (course.getUnitList().contains(unitDetail)) {
            System.out.println("This unit already belong to this course");
            return Boolean.FALSE;
        }

        course.getUnitList().add(unitDetail);
        return Boolean.TRUE;
    }

    /**
     * This method helps to assign unit to course.
     * @param course - Contains all information of course such as unitList or code
     * @return String - Contains "OK" or "Error"
     */
    public ArrayList<Unit> getUnitList(Course course) {
        if(course.getUnitList() != null) {
            return course.getUnitList();
        }
        return null;
    }

    /**
     * This method helps to assign a staff as director.
     * @param staff - Contains all the details of assigned staff member such as name or address
     * @param course - Contains all information of course such as unitList or code
     * @return String - Contains "OK" or "Error"
     */
    public String assignDirector(Staff staff, Course course) {
            // Implement all required condition
            if(course.getDirector() == null || course.getDirector().getSid() != staff.getSid()) {
                if(course.getDeputy() == null || course.getDeputy().getSid() != staff.getSid()) {
                    course.setDirector(staff);
                    return "OK";
                }
            }
        return "Error";
    }

    /**
     * This method helps to assign a staff as deputy.
     * @param staff - Contains all the details of assigned staff member such as name or address
     * @param course - Contains all information of course such as unitList or code
     * @return String - Contains "OK" or "Error"
     */
    public String assignDeputy(Staff staff, Course course) {
        // Implement all required condition
        if(course.getDeputy() == null || course.getDeputy().getSid() != staff.getSid()) {
            if(course.getDirector() == null || course.getDirector().getSid() != staff.getSid()) {
                course.setDirector(staff);
                return "OK";
            }
        }
        return "Error";
    }

    /**
     * This method helps to delete a course
     * @param code - Contains the code of course that need to be deleted
     * @param courseMap - Contains all information of course such as unitList or code
     * @return String - Contains "OK" or "Error" or else
     */
    public Boolean deleteCourse(String code,Map<String, Course> courseMap) {
        if(courseMap.isEmpty()) {
            System.out.println("There is currently none added unit");
            return Boolean.FALSE;
        }
        if(courseMap.containsKey(code)) {
            courseMap.remove(code);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * This method helps to delete a unit that already assigned to this course
     * @param uid - Contains the id of unit that need to be deleted
     * @param course - Contains all information of course such as unitList or code
     * @return String - Contains "OK" or "Error" or else
     */
    public String removeUnit(String uid,Course course) {
        ArrayList<Unit> unitList = course.getUnitList();
        if(unitList.isEmpty()) {
            return "There is currently none added unit";
        }
        for(int i = 0 ; i<unitList.size() ; i++) {
            if(uid == unitList.get(i).getCode()) {
                unitList.remove(i);
                return "OK";
            }
        }
        return "Error";
    }

    /**
     * This method helps to get a course from provided code
     * @param code - Contains the code information of searched course
     * @param courseMap - Contains all information of different courses such as unitList or code
     * @return Course - Contains empty or fully information
     */
    public Course getCourse(String code,Map<String, Course> courseMap) {
        if(courseMap.isEmpty()) {
            System.out.println("The current course database is empty");
            return new Course();
        }
        //Iterate through unit Map
        Iterator<Map.Entry<String, Course>> iterator = courseMap.entrySet().iterator();
        //Iterate using while loop
        while (iterator.hasNext()) {
            Map.Entry<String, Course> element = iterator.next();
            String ecourseID = element.getValue().getCode();
            //Condition to find if the current element in map is satisfy the provided code or not
            if(ecourseID.equals(code)){
                // return course
                return (Course) element;
            }
        }
        return new Course();
    }

    /**
     * This method helps to show the list of units that already assigned to this course
     * @param course - Contains all information of course such as unitList or code
     * @return String - Contains "OK" or "Error"
     */
    public String showUnitList(Course course) {
        ArrayList<Unit> unitsList = course.getUnitList();
        if(unitsList.isEmpty()) {
            return "The current list of units is empty";
        }
        // Loop through the unit list
        for(int i = 0 ; i<unitsList.size() ; i++) {
            System.out.println("Code: " +unitsList.get(i).getCode()+ "\t\t\t" + "Name: " +unitsList.get(i).getName());
        }
        return "OK";
        }
    /**
     * This method helps to get a course from provided code
     * @param course - Contains all information of course such as unitList or code
     * @return void
     */
    public void showCourseDetail(Course course) {
        System.out.println("Code: " +course.getCode());
        System.out.println("Name: " +course.getCode());
        System.out.println("Director: " +course.getDirector().toString());
        System.out.println("Deputy: " +course.getDeputy().toString());
    }


    public void serializeCourse(Map<String, Course> courseMap, String fileName) {
        try {
            ObjectOutputStream oes = new ObjectOutputStream(new FileOutputStream(fileName));
            oes.writeObject(courseMap);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public Map<String, Course> deserializeCourse(String fileName) {
        Map<String, Course> courseMap = new HashMap<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            courseMap = (Map<String, Course>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("The current course file is empty");
        }
        return courseMap;
    }

}

