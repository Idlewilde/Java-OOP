package StudentSystem;

import java.util.*;

public class StudentSystem {
    private List<Student> repository;

    public StudentSystem()
    {
        this.repository = new ArrayList<>();
    }

    public void add(Student student){
        repository.add(student);
    }

    public String Show(String name) {
        String studentData="";
        for (Student student : repository) {
            if(student.getName().equals(name)){
                studentData=student.getName()+" is "+student.getAge()+" years old.";
                studentData +=getComment(student.getGrade());
            }
    }
        return studentData;
}

    private String getComment(double grade) {
        if(grade>=5){
            return " Excellent student.";}
        else if(grade>=3.5){
            return " Average student.";}
            else{
            return" Very nice person.";}
    }
}
