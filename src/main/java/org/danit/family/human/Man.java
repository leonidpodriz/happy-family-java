package org.danit.family.human;

import java.util.GregorianCalendar;

public class Man extends Human {
    public Man() {
        super();
    }

    public Man(String menName, String surname, GregorianCalendar gregorianCalendar) {
        super(menName, surname, gregorianCalendar);
    }

    public void repairCar() {
        System.out.print("repairCar");
    }

    @Override
    public String getChildName() {
        return "boy";
    }
}
