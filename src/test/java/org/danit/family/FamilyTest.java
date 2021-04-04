package org.danit.family;

import org.danit.family.human.Human;
import org.danit.family.pet.Pet;
import org.danit.family.pet.RoboCat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FamilyTest {
    Human human1 = new Human();
    Human human2 = new Human();
    Family family = new Family(human2, human2);
    Pet pet = new RoboCat();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void initialize() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    public void assertSystemOut(String expected) {
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testSettersAndSetters() {
        family.addPet(pet);
        assertTrue(family.getPets().contains(pet));
    }

    @Test
    public void testFamilyCount() {
        assertEquals(2, family.countFamily());

        family.addChild(new Human());
        assertEquals(3, family.countFamily());

        family.addChild(new Human());
        family.addChild(new Human());
        family.addChild(new Human());
        assertEquals(6, family.countFamily());

        family.deleteChild(0);
        assertEquals(5, family.countFamily());
    }

    @Test
    public void testChildAdd() {
        Human child = new Human();
        family.addChild(child);

        List<Human> children = family.getChildren();
        assertEquals(child, children.get(children.size() - 1));
    }

    @Test
    public void testChildDeleteByIndex() {
        Human child1 = new Human("C", "1", 1990);
        Human child2 = new Human("C", "2", 1990);
        family.addChild(child1);
        family.addChild(child2);

        List<Human> children = family.getChildren();
        assertEquals(child2, children.get(children.size() - 1));

        family.deleteChild(children.size() - 1);
        List<Human> children_ofter_delete = family.getChildren();
        assertNotEquals(child2, children_ofter_delete.get(children_ofter_delete.size() - 1));
    }

    @Test
    public void testChildDeleteByInstance() {
        Human child1 = new Human("C", "1", 1990);
        Human child2 = new Human("C", "2", 1990);
        Human not_child = new Human("NC", "3", 1990);
        family.addChild(child1);
        family.addChild(child2);

        List<Human> children = family.getChildren();
        assertEquals(child2, children.get(children.size() - 1));

        boolean deleteChildStatus1 = family.deleteChild(child2);
        List<Human> children_ofter_delete = family.getChildren();
        assertTrue(deleteChildStatus1);
        assertNotEquals(child2, children_ofter_delete.get(children_ofter_delete.size() - 1));

        boolean deleteChildStatus2 = family.deleteChild(not_child);
        assertFalse(deleteChildStatus2);
    }

    @Test
    public void testToString() {
        String expectedToString = String.format(
                "org.danit.family.Family{mother=%s, father=%s, children=%s, pets=%s}",
                family.getMother(),
                family.getFather(),
                family.getChildren(),
                family.getPets()
        );

        System.out.println(family);

        assertEquals(expectedToString, family.toString());
        assertSystemOut(expectedToString);
    }
}
