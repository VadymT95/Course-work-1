package com.example.courseWork.model;

import java.util.Objects;

public class Participant {

    private String name;
    private String surname;
    private int numberOfvotes;

   public Participant(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.numberOfvotes = 0;
    }
    public int getNumberOfvotes() {
        return numberOfvotes;
    }
    public void setNumberOfvotes(int numberOfvotes) {
        this.numberOfvotes = numberOfvotes;
    }
    public void addOneVote(){numberOfvotes += 1;}

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return numberOfvotes == that.numberOfvotes && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, numberOfvotes);
    }
}