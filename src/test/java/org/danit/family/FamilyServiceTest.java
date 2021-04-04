package org.danit.family;

import org.danit.family.human.Human;
import org.danit.family.pet.Fish;
import org.danit.family.pet.Pet;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FamilyServiceTest {
    FamilyService familyService = new FamilyService(new FamilyDao());
    Family family = new Family(new Human(), new Human());
    Family family1 = new Family(new Human(), new Human());
    Family family2 = new Family(new Human(), new Human()) {{
        addChild(new Human());
        addChild(new Human());
    }};
    Family family3 = new Family(new Human(), new Human()) {{
        addChild(new Human());
    }};

    FamilyDao familyDao = new FamilyDao() {{
        save(family);
        save(family1);
        save(family2);
        save(family3);
    }};
    FamilyService fulfilledFamilyService = new FamilyService(familyDao);

    @Test
    void getAllFamilies() {
        Family family = new Family(new Human(), new Human());
        Family family1 = new Family(new Human(), new Human());
        FamilyDao familyDao = new FamilyDao() {{
            save(family);
            save(family1);
        }};
        FamilyService familyService1 = new FamilyService(familyDao);

        assertEquals(2, familyService1.getAllFamilies().size());
    }

    @Test
    void displayAllFamilies$displayFamilyWithIndex() {
        OutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        System.setOut(ps);

        Human h1 = new Human();
        Human h2 = new Human();
        Family f = new Family(h1, h2);
        FamilyDao fd = new FamilyDao() {{
            save(f);
        }};
        FamilyService fs = new FamilyService(fd);
        fs.displayAllFamilies();

        String es = String.format("%d) %s\n", 1, f.toString());

        assertEquals(es, bos.toString());
    }

    @Test
    void getFamiliesBiggerThan() {
        assertEquals(2, fulfilledFamilyService.getFamiliesBiggerThan(2).size());
    }

    @Test
    void getFamiliesLessThan() {
        assertEquals(3, fulfilledFamilyService.getFamiliesLessThan(4).size());
    }

    @Test
    void countFamiliesWithMemberNumber() {
        assertEquals(2, fulfilledFamilyService.countFamiliesWithMemberNumber(2));
    }

    @Test
    void createNewFamily$deleteFamilyByIndex() {
        Family nf1 = familyService.createNewFamily(new Human(), new Human());
        Family nf2 = familyService.createNewFamily(new Human(), new Human());

        assertTrue(familyService.getAllFamilies().contains(nf1));
        assertTrue(familyService.getAllFamilies().contains(nf2));

        assertEquals(2, familyService.count());

        boolean b = familyService.deleteFamilyByIndex(0);
        boolean b1 = familyService.deleteFamilyByIndex(0);
        boolean b2 = familyService.deleteFamilyByIndex(0);

        assertTrue(b);
        assertTrue(b1);
        assertFalse(b2);

        assertEquals(0, familyService.count());
    }

    @Test
    void bornChild() {
        String[] names = {"Alex", "Kate"};
        for (int i = 0; i < 100; i++) {
            Family family = familyService.bornChild(this.family, names[0], names[1]);
            assertTrue(Arrays.asList(names).contains(family.getChildren().get(0).getName()));
        }
    }

    @Test
    void adoptChild$deleteAllChildrenOlderThen() {
        Human child1 = new Human("John", "Doe", 1990);
        Human child2 = new Human();
        Human father = new Human();
        Human mother = new Human();

        FamilyService fs = new FamilyService(new FamilyDao());

        Family family = fs.adoptChild(new Family(mother, father), child1);
        fs.adoptChild(family, child2);

        assertTrue(family.getChildren().contains(child1));
        assertTrue(family.getChildren().contains(child2));

        Family dcf = fs.deleteAllChildrenOlderThen(family, 18);
        assertFalse(dcf.getChildren().contains(child1));
        assertTrue(dcf.getChildren().contains(child2));
    }

    @Test
    void count() {
        assertEquals(4, fulfilledFamilyService.count());
    }

    @Test
    void getFamilyById() {
        assertEquals(family, fulfilledFamilyService.getFamilyById(0).orElse(null));
    }

    @Test
    void addPet$getPets() {
        Pet p = new Fish();
        fulfilledFamilyService.addPet(0, p);

        assertTrue(fulfilledFamilyService.getPets(0).orElse(new HashSet<>()).contains(p));
    }
}