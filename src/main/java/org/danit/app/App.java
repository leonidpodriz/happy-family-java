package org.danit.app;

import org.danit.console.Callback;
import org.danit.console.Console;
import org.danit.console.RunnableConsole;
import org.danit.family.Family;
import org.danit.family.FamilyController;
import org.danit.family.FamilyDao;
import org.danit.family.FamilyService;
import org.danit.interfaces.Dao;

import java.util.ArrayList;
import java.util.List;

public class App {
    static Console console = new RunnableConsole(App::getOptions);
    static Dao<Family> dao = new FamilyDao();
    static FamilyService familyService = new FamilyService(dao);
    static FamilyController familyController = new FamilyController(familyService);

    public static List<Callback> getOptions() {
        List<Callback> callbacks = new ArrayList<>();

        callbacks.add(new Callback(
                "Generate test data",
                familyController::console$generateTestData
        ));
        callbacks.add(new Callback(
                "Display all families",
                familyController::console$displayAllFamilies
        ));
        callbacks.add(new Callback(
                "Display all families with count greater than",
                familyController::console$displayAllFamiliesWithCountGreaterThan
        ));
        callbacks.add(new Callback(
                "Display all families with count less than",
                familyController::console$displayAllFamiliesWithCountLessThan
        ));
        callbacks.add(new Callback(
                "Display all families with specific count",
                familyController::console$countFamiliesWithMembersCount
        ));
        callbacks.add(new Callback(
                "Create new family",
                familyController::console$createNewFamily
        ));
        callbacks.add(new Callback(
                "Remove family by index",
                familyController::console$removeFamilyByIndex
        ));
        callbacks.add(new Callback(
                "Remove all children older than",
                familyController::console$removeAllChildrenOlderThan
        ));
        callbacks.add(new Callback("Exit", Console::prepareToExit));

        return callbacks;
    }

    public static void main(String[] args) {
        console.run();
    }
}
