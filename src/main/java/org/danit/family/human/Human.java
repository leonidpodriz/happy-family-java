package org.danit.family.human;

import org.danit.family.Family;
import org.danit.family.PrettyFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class Human implements PrettyFormat, Serializable {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String name;
    private String surname;
    private long birthDate;
    private int iq;
    Family family;
    private Map<DayOfWeek, String> schedule;


    public Human(String name, String surname, long birthDate, int iq, Family family, HashMap<DayOfWeek, String> schedule) {
        this.name = name;
        this.surname = surname;
        this.surname = surname;
        this.iq = iq;
        this.birthDate = birthDate;
        this.family = family;
        this.schedule = schedule;
    }

    public Human(String name, String surname, long birthDate) {
        this(name, surname, birthDate, 0, null, new HashMap<>());
    }

    public Human(String name, String surname, Calendar birthDate) {
        this(name, surname, birthDate.getTime().getTime(), 0, null, new HashMap<>());
    }

    public Human(String name, String surname, String birthDate) throws ParseException {
        this(name, surname, sdf.parse(birthDate).getTime(), 0, null, new HashMap<>());
    }

    public Human(String name, String surname, String birthDate, int iq) throws ParseException {
        this(name, surname, sdf.parse(birthDate).getTime(), iq, null, new HashMap<>());
    }

    public Human() {
        this("undefined", "undefined", (new Date()).getTime());
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

    public long getBirthDate() {
        return birthDate;
    }

    public int getIq() {
        return iq;
    }

    public Family getFamily() {
        return family;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return LocalDate.now().getYear() - new Timestamp(birthDate).toLocalDateTime().getYear();
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

    @Override
    public String toString() {
        return String.format(
                "org.danit.family.human.Human{name='%s', surname='%s', birthDate=%s, iq=%d, schedule=%s}",
                name,
                surname,
                sdf.format(new Date(birthDate)),
                iq,
                schedule.toString()
        );
    }

    private LocalDate getBirthLocalDate() {
        return new Timestamp(birthDate).toLocalDateTime().toLocalDate();
    }

    public String describeAge() {
        LocalDate t = LocalDate.now();
        LocalDate b = getBirthLocalDate();
        long ds = (new Date().getTime() - birthDate) / 86400000;
        long y = ds / 365;
        long m = ds % 365 / 30;
        long d = ds % 365 % 30;
        return String.format("%d years, %d months, %d days", y, m, d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human human)) return false;
        return getBirthDate() == human.getBirthDate() && getIq() == human.getIq() && Objects.equals(getName(), human.getName()) && Objects.equals(getSurname(), human.getSurname()) && Objects.equals(getFamily(), human.getFamily()) && Objects.equals(getSchedule(), human.getSchedule());
    }

    @Override
    public String prettyFormat() {
        return String.format(
                "{name='%s', surname='%s', birthDate='%s', iq=%d, schedule=%s}",
                name, surname, sdf.format(birthDate), iq, schedule.toString()
        );
    }

    public String getChildName() {
        return "child";
    }
}
