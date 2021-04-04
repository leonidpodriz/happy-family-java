package org.danit.family;

import org.danit.family.human.Human;
import org.danit.family.human.Woman;
import org.danit.family.pet.Pet;
import org.danit.interfaces.Dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FamilyService {
    private final Dao<Family> dao;

    public FamilyService(Dao<Family> dao) {
        this.dao = dao;
    }

    public List<Family> getAllFamilies() {
        return dao.getAll();
    }

    private void displayFamilyWithIndex(Family family, int index) {
        System.out.printf("%d) %s\n", index + 1, family);
    }

    public void displayAllFamilies(List<Family> allFamilies) {
        IntStream.range(0, allFamilies.size()).forEach(i -> displayFamilyWithIndex(allFamilies.get(i), i));
    }

    public void displayAllFamilies() {
        List<Family> allFamilies = getAllFamilies();
        displayAllFamilies(allFamilies);
    }

    public List<Family> getFamiliesBiggerThan(int peoplesCount) {
        return getAllFamilies().stream().filter(f -> f.countFamily() > peoplesCount).collect(Collectors.toList());
    }

    public List<Family> getFamiliesLessThan(int peoplesCount) {
        return getAllFamilies().stream().filter(f -> f.countFamily() < peoplesCount).collect(Collectors.toList());
    }

    public int countFamiliesWithMemberNumber(int peoplesCount) {
        return (int) getAllFamilies()
                .stream()
                .filter(f -> f.countFamily() == peoplesCount)
                .count();
    }

    public Family createNewFamily(Human human1, Human human2) {
        Family newFamily = new Family(human1, human2);
        dao.save(newFamily);

        return newFamily;
    }

    public boolean deleteFamilyByIndex(int index) {
        return dao.delete(index);
    }

    private boolean randomYesNo() {
        return Math.random() > .5;
    }

    private Human bornRandomGenderChild(String surname, String menName, String womanName) {
        Human child;

        if (randomYesNo()) {
            child = new Woman(
                    womanName,
                    surname,
                    LocalDate.now().getYear()
            );
        } else {
            child = new Woman(
                    menName,
                    surname,
                    LocalDate.now().getYear()
            );
        }

        return child;
    }

    private void addChild(Family family, Human child) {
        family.addChild(child);
        dao.save(family);
    }

    public Family bornChild(Family family, String menName, String womanName) {
        String fatherSurname = family.getFather().getSurname();
        Human child = bornRandomGenderChild(fatherSurname, menName, womanName);
        addChild(family, child);

        return family;
    }

    public Family adoptChild(Family family, Human child) {
        addChild(family, child);
        return family;
    }

    public Family deleteAllChildrenOlderThen(Family family, int age) {
        family.getChildren().stream()
                .filter(h -> h.getAge() > age)
                .collect(Collectors.toSet()) // Avoiding ConcurrentModificationException or null in the collection
                .forEach(family::deleteChild);
        dao.save(family);
        return family;
    }


    public int count() {
        return dao.getAll().size();
    }

    public Optional<Family> getFamilyById(int index) {
        return dao.get(index);
    }

    public Optional<Set<Pet>> getPets(int familyIndex) {
        return dao.get(familyIndex).map(Family::getPets);
    }

    public void addPet(int familyIndex, Pet pet) {
        dao.get(familyIndex).ifPresent(family -> {
            family.addPet(pet);
            dao.save(family);
        });
    }
}
