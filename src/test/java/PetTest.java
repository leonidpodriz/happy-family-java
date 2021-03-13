import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {
    Dog pet;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void initialize() {
        pet = new Dog();
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
        int age = 5;
        int trickLevel = 60;
        String nickname = "pet";
        String[] habits = {"habit1", "habit2"};
        PetSpecies species = PetSpecies.DOG;

        pet.setAge(age);
        pet.setNickname(nickname);
        pet.setSpecies(species);
        pet.setTrickLevel(60);
        pet.setHabits(habits);

        assertEquals(age, pet.getAge());
        assertEquals(trickLevel, pet.getTrickLevel());
        assertEquals(nickname, pet.getNickname());
        assertEquals(species, pet.getSpecies());
        assertArrayEquals(habits, pet.getHabits());
    }

    @Test
    public void testPetFoul() {
        pet.foul();
        assertSystemOut(Dog.FOUL);
    }

    @Test
    public void testPetEat() {
        pet.eat();
        assertSystemOut(Pet.EAT);
    }

    @Test
    public void testPetRespond() {
        pet.respond();
        assertSystemOut(String.format(Dog.RESPOND, pet.getNickname()));
    }

    @Test
    public void testToString() {
        String expectedToString = String.format(
                "%s{nickname='%s', age=%d, trickLevel=%d, habits=%s}",
                pet.getSpecies().name(),
                pet.getNickname(),
                pet.getAge(),
                pet.getTrickLevel(),
                Arrays.toString(pet.getHabits())
        );
        System.out.println(pet);

        assertEquals(expectedToString, pet.toString());
        assertSystemOut(expectedToString);
    }
}
