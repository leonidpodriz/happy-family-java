import org.danit.family.Family;
import org.danit.family.human.Human;
import org.danit.family.pet.DomesticCat;
import org.danit.family.pet.Pet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
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
        family.addPet(pet);
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
        HashMap<DayOfWeek, String> schedule = new HashMap<>() {{
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
    public void testToString() {
        String expectedToString = String.format(
                "org.danit.family.human.Human{name='%s', surname='%s', year=%d, iq=%d, schedule=%s}",
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
