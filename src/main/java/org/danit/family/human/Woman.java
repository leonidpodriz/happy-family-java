package org.danit.family.human;

import java.util.GregorianCalendar;

public class Woman extends Human {
    public Woman(String womanName, String surname, int year) {
        super(womanName, surname, year);
    }

    public Woman() {
        super();
    }

    public Woman(String womenName, String surname, GregorianCalendar gregorianCalendar) {
        super(womenName, surname, gregorianCalendar);
    }

    public void makeup() {
        System.out.print("makeup");
    }

    @Override
    public String getChildName() {
        return "girl";
    }
}
