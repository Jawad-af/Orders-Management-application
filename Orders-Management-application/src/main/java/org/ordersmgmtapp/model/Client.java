package org.ordersmgmtapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Client class represents a client entity in the system.
 */
public class Client {
    private long id;
    private String name;
    private int age;

    /**
     * Constructs a new Client object with an auto-incremented ID.
     */
    public Client(){
    }

    /**
     * Constructs a new Client object with the given ID, name, and age.
     * @param id The ID of the client.
     * @param name The name of the client.
     * @param age The age of the client.
     */
    public Client(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * Constructs a new Client object with a generated ID and the given name and age.
     * @param name The name of the client.
     * @param age The age of the client.
     */
    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Retrieves the ID of the client.
     * @return The ID of the client.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the client.
     * @param id The ID to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the client.
     * @return The name of the client.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the age of the client.
     * @return The age of the client.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the client.
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns a string representation of the client.
     * @return A string representation of the client.
     */
    @Override
    public String toString() {
        return '{' +
                "id=" + this.id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
