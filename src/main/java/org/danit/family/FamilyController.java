package org.danit.family;

import org.danit.console.Callback;
import org.danit.console.Console;
import org.danit.console.RunnableConsole;
import org.danit.family.human.Human;
import org.danit.family.human.Man;
import org.danit.family.human.Woman;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Supplier;

public record FamilyController(FamilyService service) {
    static String[] menNames = new String[]{"James", "John", "Robert", "Michael", "William"};
    static String[] womenNames = new String[]{"Mary", "Patricia", "Jennifer", "Linda", "Elizabeth"};
    static String[] surnames = new String[]{"Adin", "Baer", "Borror", "Fikes", "Gorelik"};
    static int MAX_GENERATION_COUNT = 10;

    public void console$generateTestData(Console console) {
        for (int i = 0; i < MAX_GENERATION_COUNT; i++) {
            String menName = menNames[(int) Math.round(Math.random() * (menNames.length - 1))];
            String womenName = womenNames[(int) Math.round(Math.random() * (womenNames.length - 1))];
            String surname = surnames[(int) Math.round(Math.random() * (surnames.length - 1))];
            Man man = new Man(menName, surname, new GregorianCalendar(1, Calendar.JANUARY, 1970));
            Woman woman = new Woman(womenName, surname, new GregorianCalendar(1, Calendar.JANUARY, 1970));
            consoleHelper$createNewFamily(woman, man, console);
        }
    }

    public void console$displayAllFamilies(Console console) {
        service.displayAllFamilies();
    }

    public int getNumber(Console console) {
        console.printLine("Enter number to search:");
        return console.readInt();
    }


    public void console$displayAllFamiliesWithCountGreaterThan(Console console) {
        int count = getNumber(console);
        service.displayAllFamilies(service.getFamiliesBiggerThan(count));
    }

    public void console$displayAllFamiliesWithCountLessThan(Console console) {
        int count = getNumber(console);
        service.displayAllFamilies(service.getFamiliesLessThan(count));
    }

    public void console$countFamiliesWithMembersCount(Console console) {
        int count = getNumber(console);
        console.printLine(String.format("Count: %s", service.countFamiliesWithMemberNumber(count)));
    }

    public Human consoleHelper$createHuman(String type, Console console) {
        Human h = null;

        while (h == null) {
            try {
                h = new Human(
                        prompt(console, String.format("%s's first name", type), console::readString),
                        prompt(console, String.format("%s's last name", type), console::readString),
                        prompt(console, String.format("%s's birth date [dd/MM/yyyy]", type), console::readString),
                        prompt(console, String.format("%s's IQ", type), console::readInt)
                );
            } catch (ParseException e) {
                console.printLine("Invalid date value: dd/MM/yyyy");
            }
        }
        return h;
    }

    public void consoleHelper$createNewFamily(Human mother, Human father, Console console) {
        try {
            Family newFamily = service.createNewFamily(mother, father);
            console.printLine(String.format("Generated: %s", newFamily));
        } catch (RuntimeException e) {
            console.printLine("Family can not be created!");
        }
    }

    public void console$createNewFamily(Console console) {
        Human mother = consoleHelper$createHuman("Mother", console);
        Human father = consoleHelper$createHuman("Father", console);

        consoleHelper$createNewFamily(mother, father, console);
    }

    public void console$removeFamilyByIndex(Console console) {
        int index = prompt(console, "Family index?", console::readInt) - 1;
        service.deleteFamilyByIndex(index);
    }

    public void console$removeAllChildrenOlderThan(Console console) {
        int age = prompt(console, "Child age?", console::readInt);
        service.getAllFamilies().forEach(f -> service.deleteAllChildrenOlderThen(f, age));
    }

    public void console$adoptChild(Console console) {
        int index = prompt(console, "Family index?", console::readInt) - 1;
        service.getFamilyById(index).ifPresent(family ->
                service.adoptChild(family, consoleHelper$createHuman("Child", console))
        );
    }

    public void console$bornChild(Console console) {
        int index = prompt(console, "Family index?", console::readInt) - 1;
        service.getFamilyById(index).ifPresent(family ->
                service.bornChild(
                        family,
                        prompt(console, "Man's name", console::readString),
                        prompt(console, "Woman's name", console::readString)
                )
        );
    }

    public void console$editFamilyByIndex(Console console) {
        RunnableConsole runnableConsole = new RunnableConsole(
                new Callback("Born child", this::console$bornChild),
                new Callback("Adopt child", this::console$adoptChild),
                new Callback("Back", Console::prepareToExit)
        );

        runnableConsole.run();
    }

    private <T> T prompt(Console console, String text, Supplier<T> s) {
        console.printLine(text);
        return s.get();
    }
}
