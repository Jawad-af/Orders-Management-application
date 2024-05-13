package org.ordersmgmtapp.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private static long clientId = 0;
    private long id;
    private String name;
    private int age;

    public Client(){
        this.id = ++clientId;
    }

    public Client(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public Client(String name, int age) {
        this.id = ++clientId;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return '{' +
                "id=" + this.id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
