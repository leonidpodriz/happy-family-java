package org.danit.family.pet;

import org.danit.family.pet.Pet;
import org.danit.family.pet.PetSpecies;

import java.util.HashSet;

public class Fish extends Pet {

    static final String RESPOND = "Привет, хозяин. Я - %s. Я соскучился!";

    public Fish(String nickname, int age, int trickLevel, HashSet<String> habits) {
        super(nickname, age, trickLevel, habits);
        setSpecies(PetSpecies.FISH);
    }

    public Fish(String nickname) {
        this(nickname, 0, 0, new HashSet<String>());
    }

    public Fish() {
        this(DEFAULT_PET_NAME);
    }

    @Override
    public void respond() {
        System.out.printf(RESPOND, nickname);
    }

    @Override
    public PetSpecies getDefaultSpecies() {
        return PetSpecies.FISH;
    }
}
