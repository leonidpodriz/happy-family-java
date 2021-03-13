import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class HumanTest {
    Human human;
    Family family;
    Pet pet;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void initialize() {
        human = new Human();
        pet = new DomesticCat();
        family = new Family(human, new Human());
        family.setPet(pet);
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
        String name = "Test name";
        String surname = "Test surname";
        int year = 1980;
        int iq = 90;
        HashMap<DayOfWeek, String> schedule = new HashMap<DayOfWeek, String>() {{
            put(DayOfWeek.MONDAY, "Nothing");
            put(DayOfWeek.TUESDAY, "Nothing");
        }};

        human.setName(name);
        human.setSurname(surname);
        human.setYear(year);
        human.setIq(iq);
        human.setSchedule(schedule);

        assertEquals(name, human.getName());
        assertEquals(surname, human.getSurname());
        assertEquals(year, human.getYear());
        assertEquals(iq, human.getIq());
        assertEquals(schedule.toString(), human.getSchedule().toString());
        assertEquals(family, human.getFamily());
    }

    @Test
    public void testPetGreet() {
        human.greetPet();
        String expectedPetGreet = String.format(Human.GREET_PET, family.getPet().getNickname());

        assertSystemOut(expectedPetGreet);
    }

    @Test
    public void testDescribeNotTrickyPet() {
        pet.setTrickLevel(40);

        human.describePet();
        String expectedPetDescribe = String.format("У меня есть %s, ему %s лет, он %s",
                family.getPet().getSpecies(),
                family.getPet().getAge(),
                Human.NOT_TRICKY
        );

        assertSystemOut(expectedPetDescribe);
    }

    @Test
    public void testDescribeTrickyPet() {
        pet.setTrickLevel(60);

        human.describePet();
        String expectedPetDescribe = String.format("У меня есть %s, ему %s лет, он %s",
                family.getPet().getSpecies(),
                family.getPet().getAge(),
                Human.TRICKY
        );

        assertSystemOut(expectedPetDescribe);
    }

    @Test
    public void testToString() {
        String expectedToString = String.format(
                "Human{name='%s', surname='%s', year=%d, iq=%d, schedule=%s}",
                human.getName(),
                human.getSurname(),
                human.getYear(),
                human.getIq(),
                human.getSchedule().toString()
        );
        System.out.println(human);

        assertEquals(expectedToString, human.toString());
        assertSystemOut(expectedToString);
    }
}
