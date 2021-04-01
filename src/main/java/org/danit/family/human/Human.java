package org.danit.family.human;

import org.danit.family.Family;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Human {
    static public final String GREET_PET = "Привет, %s";
    static public final String TRICKY = "очень хитрый";
    static public final String NOT_TRICKY = "почти не хитрый";

    private String name;
    private String surname;
    private int year;
    private int iq;
    Family family;
    private Map<DayOfWeek, String> schedule;


    public Human(String name, String surname, int year, int iq, Family family, HashMap<DayOfWeek, String> schedule) {
        this.name = name;
        this.surname = surname;
        this.surname = surname;
        this.iq = iq;
        this.year = year;
        this.family = family;
        this.schedule = schedule;
    }

    public Human(String name, String surname, int year) {
        this(name, surname, year, 0, null, new HashMap<>());
    }

    public Human() {
        this("undefined", "undefined", 1970);
    }

    public Map<DayOfWeek, String> getSchedule() {
        return schedule;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getIq() {
        return iq;
    }

    public Family getFamily() {
        return family;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSchedule(HashMap<DayOfWeek, String> schedule) {
        this.schedule = schedule;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public void greetPet() {
        System.out.printf(GREET_PET, family.getPet().getNickname());
    }

    private String getPetTrickLevel() {
        return family.getPet().getTrickLevel() > 50 ? TRICKY : NOT_TRICKY;
    }

    public void describePet() {
        System.out.printf(
                "У меня есть %s, ему %s лет, он %s",
                family.getPet().getSpecies(),
                family.getPet().getAge(),
                getPetTrickLevel()
        );
    }

    @Override
    public String toString() {
        return String.format(
                "org.danit.family.human.Human{name='%s', surname='%s', year=%d, iq=%d, schedule=%s}",
                name,
                surname,
                year,
                iq,
                schedule.toString()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human human)) return false;
        return getYear() == human.getYear() && getIq() == human.getIq() && Objects.equals(getName(), human.getName()) && Objects.equals(getSurname(), human.getSurname()) && Objects.equals(getFamily(), human.getFamily()) && Objects.equals(getSchedule(), human.getSchedule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getYear(), getIq(), getFamily(), getSchedule());
    }
}
