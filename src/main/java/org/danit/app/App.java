package org.danit.app;

import org.danit.family.Family;
import org.danit.family.human.Human;
import org.danit.family.pet.Dog;
import org.danit.family.pet.Fish;
import org.danit.family.pet.Pet;
import org.danit.family.pet.RoboCat;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.HashSet;

public class App {
    public static void petCreating() {
        Pet p1 = new Fish();
        Pet p2 = new RoboCat("pet1");
        Pet p3 = new Dog("pet2", 2, 90, new HashSet<String>());

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
    }

    public static void humanCreating() {
        Human h1 = new Human();
        Human h2 = new Human("N", "S", 1980);
        Human h3 = new Human("N", "S", 1980);

        Human h4 = new Human("N", "S", 1980, 90, null, new HashMap<DayOfWeek, String>() {{
            put(DayOfWeek.MONDAY, "Nothing");
        }});

        System.out.println(h1);
        System.out.println(h2);
        System.out.println(h3);
        System.out.println(h4);
    }

    public static void familyCreating() {
        Human h1 = new Human();
        Human h2 = new Human("N", "S", 1980);
        Human h3 = new Human("N", "S", 1980);
        Human h4 = new Human("N", "S", 1980, 90, null, new HashMap<DayOfWeek, String>() {{
            put(DayOfWeek.MONDAY, "Nothing");
        }});

        Family f1 = new Family(h1, h2);
        Family f2 = new Family(h3, h4);

        System.out.println(f1);
        System.out.println(f2);
        System.out.printf("org.danit.family.Family #1 count: %d\n", f1.countFamily());
        System.out.printf("org.danit.family.Family #2 count: %d\n", f2.countFamily());
    }

    public static void familyChildManagement() {
        Human h1 = new Human();
        Human h2 = new Human("N", "S", 1980);
        Human h3 = new Human("N", "S", 1980);

        Family f1 = new Family(h1, h2);


        f1.addChild(h3);
        System.out.println(f1);
        System.out.printf("org.danit.family.Family #1 count: %d\n", f1.countFamily());

        boolean b = f1.deleteChild(0);
        System.out.println(f1);
        System.out.printf("[%s] org.danit.family.Family #1 count: %d\n", b, f1.countFamily());
    }

    public static void overloadMemoryUsingHuman(int creatingCount) {
        for (int i = 0; i < creatingCount; i++) {
            new Human();
        }
    }

    public static void main(String[] args) {
        petCreating();
        humanCreating();
        familyCreating();
        familyChildManagement();
        overloadMemoryUsingHuman((int) Math.pow(2, 20));
    }
}
