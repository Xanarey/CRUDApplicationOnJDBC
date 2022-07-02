package com.tim.model;

import java.util.Objects;

public class Specialty {
    private String name;

    public Specialty(String name) {
        this.name = name;
    }

    public Specialty() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Specialty)) return false;
        Specialty specialty = (Specialty) o;
        return Objects.equals(getName(), specialty.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
