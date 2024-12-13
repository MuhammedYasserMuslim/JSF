package com.java.jsfcourse.bean;


import com.java.jsfcourse.model.Student;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named
//@RequestScoped // bean destroy after request when make insert in database
//@ViewScoped   // bean destroy after close jsf page
@SessionScoped // bean destroy after session
//@ApplicationScoped // bean destroy after close application
@Setter
@Getter
public class User implements Serializable {

    private String username;
    private String password;
    private int age;
    private Date date;
    private String massage;
    private boolean reminder;
    private String[] languages;
    private String[] skills;
    private String department;
    private String country;
    private String gender;
    List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Muhammed"),
            new Student(2, "Mahmoud"),
            new Student(2, "ali"),
            new Student(2, "Alaa")
    ));

}
