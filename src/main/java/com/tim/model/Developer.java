package com.tim.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
    private Status status;
    private Specialty specialty;
    private List<Skill> skills;

    public Developer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        status = Status.ACTIVE;
        specialty = new Specialty();
        skills = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", specialty=" + specialty +
                ", skills=" + skills +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Developer)) return false;
        Developer developer = (Developer) o;
        return Objects.equals(getId(), developer.getId()) && Objects.equals(getFirstName(), developer.getFirstName()) && Objects.equals(getLastName(), developer.getLastName()) && getStatus() == developer.getStatus() && Objects.equals(getSpecialty(), developer.getSpecialty()) && Objects.equals(getSkills(), developer.getSkills());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getStatus(), getSpecialty(), getSkills());
    }
}
