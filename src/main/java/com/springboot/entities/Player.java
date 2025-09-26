package com.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private Integer jerseyNumber;
    private Integer age;
    private String role;
    private Long contact;

    // ✅ No-arg constructor (required by JPA)
    public Player() {}

    // ✅ Constructor without ID (for new records)
    public Player(String name, Integer jerseyNumber, String role, Integer age, Long contact) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.role = role;
        this.age = age;
        this.contact = contact;
    }

    // ✅ Constructor with ID (for updates/testing)
    public Player(int id, String name, Integer jerseyNumber, String role, Integer age, Long contact) {
        this.id = id;
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.role = role;
        this.age = age;
        this.contact = contact;
    }


    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(Integer jerseyNumber) { this.jerseyNumber = jerseyNumber; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Long getContact() { return contact; }
    public void setContact(Long contact) { this.contact = contact; }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jerseyNumber=" + jerseyNumber +
                ", age=" + age +
                ", role='" + role + '\'' +
                ", contact=" + contact +
                '}';
    }
}
