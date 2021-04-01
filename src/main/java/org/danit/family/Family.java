package org.danit.family;

import org.danit.family.human.Human;
import org.danit.family.pet.Pet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Family {
    private final Human mother;
    private final Human father;
    private final List<Human> children;
    private final Set<Pet> pets = new HashSet<>();

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<>();

        mother.setFamily(this);
        father.setFamily(this);
    }

    public List<Human> getChildren() {
        return children;
    }

    public Human getMother() {
        return mother;
    }

    public Human getFather() {
        return father;
    }

    public boolean deleteChild(int index) {
        if (index >= 0 && index < children.size()) {
            Human h = children.get(index);
            h.setFamily(null);
            children.remove(index);
            return true;
        } else {
            return false;
        }
    }

    private int findChildIndex(Human child) {
        for (int index = 0; index < children.size(); index++) {
            if (child == children.get(index)) {
                return index;
            }
        }

        return -1;
    }

    public boolean deleteChild(Human child) {
        int index = findChildIndex(child);
        return deleteChild(index);
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public int countFamily() {
        return 2 + children.size();
    }

    public void addChild(Human child) {
        children.add(child);
        child.setFamily(this);
    }

    @Override
    public String toString() {
        return "org.danit.family.Family{" +
                "mother=" + mother +
                ", father=" + father +
                ", children=" + children +
                ", pets=" + getPets() +
                '}';
    }
}
