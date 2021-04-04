package org.danit.family;

import org.danit.console.Callback;
import org.danit.console.Console;
import org.danit.console.RunnableConsole;
import org.danit.family.human.Human;

import java.text.ParseException;
import java.util.function.Supplier;

public record FamilyController(FamilyService service) {

    public void console$generateTestData(Console console) {
    }

    public void console$displayAllFamilies(Console console) {
        service.displayAllFamilies();
    }


    public void console$displayAllFamiliesWithCountGreaterThan(Console console) {
        int count = console.readInt();
        service.displayAllFamilies(service.getFamiliesBiggerThan(count));
    }

    public void console$displayAllFamiliesWithCountLessThan(Console console) {
        int count = console.readInt();
        service.displayAllFamilies(service.getFamiliesLessThan(count));
    }

    public void console$countFamiliesWithMembersCount(Console console) {
        int count = console.readInt();
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

    public void console$createNewFamily(Console console) {
        Human mother = consoleHelper$createHuman("Mother", console);
        Human father = consoleHelper$createHuman("Father", console);

        service.createNewFamily(mother, father);
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
