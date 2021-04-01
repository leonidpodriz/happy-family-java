package org.danit.family.pet;

import org.danit.family.pet.Pet;
import org.danit.family.pet.PetSpecies;

import java.util.HashSet;

public class Dog extends Pet implements FoulPet {
    static public final String FOUL = "Нужно хорошо замести следы...";
    static public final String RESPOND = "Привет, хозяин. Я - %s. Я соскучился!";

    public Dog(String nickname, int age, int trickLevel, HashSet<String> habits) {
        super(nickname, age, trickLevel, habits);
    }

    public Dog(String nickname) {
        super(nickname);
    }

    public Dog() {
        super();
    }

    public void foul() {
        System.out.println(FOUL);
    }

    @Override
    public void respond() {
        System.out.printf(RESPOND, nickname);
    }

    @Override
    public PetSpecies getDefaultSpecies() {
        return PetSpecies.DOG;
    }
}
