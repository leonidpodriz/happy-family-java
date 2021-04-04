package org.danit.family.human;

public class Man extends Human {
    public void repairCar() {
        System.out.print("repairCar");
    }

    @Override
    public String getChildName() {
        return "boy";
    }
}
