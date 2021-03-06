package org.danit.family.pet;

import org.danit.family.pet.Pet;
import org.danit.family.pet.PetSpecies;

import java.util.HashSet;

public class DomesticCat extends Pet implements FoulPet {
    static final String FOUL = "Нужно хорошо замести следы...";

    static final String RESPOND = "Привет, хозяин. Я - %s. Я соскучился!";

    public DomesticCat(String nickname, int age, int trickLevel, HashSet<String> habits) {
        super(nickname, age, trickLevel, habits);
        setSpecies(PetSpecies.DOMESTIC_CAT);
    }

    public DomesticCat(String nickname) {
        super(nickname);
    }

    public DomesticCat() {
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
        return PetSpecies.DOMESTIC_CAT;
    }
}
