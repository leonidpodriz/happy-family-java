package org.danit.family.human;

public class Woman extends Human {
    public Woman(String womanName, String surname, int year) {
        super(womanName, surname, year);
    }

    public Woman() {
        super();
    }

    public void makeup() {
        System.out.print("makeup");
    }

    @Override
    public String getChildName() {
        return "girl";
    }
}
