import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class FamilyTest {
    Human human1;
    Human human2;
    Family family;
    Pet pet;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void initialize() {
        human1 = new Human();
        human2 = new Human();
        pet = new RoboCat();
        family = new Family(human2, human2);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    public void assertSystemOut(String expected) {
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testSettersAndSetters() {
        family.setPet(null);
        assertNull(family.getPet());

        family.setPet(pet);
        assertEquals(pet, family.getPet());
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

        Human[] children = family.getChildren();
        assertEquals(child, children[children.length - 1]);
    }

    @Test
    public void testChildDeleteByIndex() {
        Human child1 = new Human("C", "1", 1990);
        Human child2 = new Human("C", "2", 1990);
        family.addChild(child1);
        family.addChild(child2);

        Human[] children = family.getChildren();
        assertEquals(child2, children[children.length - 1]);

        family.deleteChild(children.length - 1);
        Human[] children_ofter_delete = family.getChildren();
        assertNotEquals(child2, children_ofter_delete[children_ofter_delete.length - 1]);
    }

    @Test
    public void testChildDeleteByInstance() {
        Human child1 = new Human("C", "1", 1990);
        Human child2 = new Human("C", "2", 1990);
        Human not_child = new Human("NC", "3", 1990);
        family.addChild(child1);
        family.addChild(child2);

        Human[] children = family.getChildren();
        assertEquals(child2, children[children.length - 1]);

        boolean deleteChildStatus1 = family.deleteChild(child2);
        Human[] children_ofter_delete = family.getChildren();
        assertTrue(deleteChildStatus1);
        assertNotEquals(child2, children_ofter_delete[children_ofter_delete.length - 1]);

        boolean deleteChildStatus2 = family.deleteChild(not_child);
        assertFalse(deleteChildStatus2);
    }

    @Test
    public void testToString() {
        String expectedToString = String.format(
                "Family{mother=%s, father=%s, children=%s, pet=%s}",
                family.getMother(),
                family.getFather(),
                Arrays.deepToString(family.getChildren()),
                family.getPet()
        );

        System.out.println(family);

        assertEquals(expectedToString, family.toString());
        assertSystemOut(expectedToString);
    }
}
