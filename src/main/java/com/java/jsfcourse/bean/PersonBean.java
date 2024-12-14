package com.java.jsfcourse.bean;

import com.java.jsfcourse.db.dao.PersonDao;
import com.java.jsfcourse.db.model.Person;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
@Setter
@Getter
public class PersonBean implements Serializable {

    private Person person = new Person();
    private List<Person> persons;
    private int id;


    public void findAll() {
        this.persons = PersonDao.getInstance().findAll();
    }

    public void findById(int id) {
        this.person = PersonDao.getInstance().findById(id);
    }

    public void insert() {
        PersonDao.getInstance().save(person);
        findAll();
    }

    public void deleteById(int id){
        PersonDao.getInstance().deleteById(id);
        findAll();
    }


}
