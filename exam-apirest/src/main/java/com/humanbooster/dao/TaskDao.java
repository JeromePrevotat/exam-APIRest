package com.humanbooster.dao;

import org.hibernate.SessionFactory;

import com.humanbooster.model.Task;

public class TaskDao extends GenericDaoImp<Task, Integer>{

    public TaskDao(SessionFactory sessionFactory) {
        super(sessionFactory, Task.class);
    }


}
