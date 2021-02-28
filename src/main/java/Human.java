import java.util.Arrays;
import java.util.Objects;

public class Human {
    static final String GREET_PET = "Привет, %s";
    static final String TRICKY = "очень хитрый";
    static final String NOT_TRICKY = "почти не хитрый";

    private String name;
    private String surname;
    private int year;
    private int iq;
    private Family family;
    private String[][] schedule;


    public Human(String name, String surname, int year, int iq, Family family, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.surname = surname;
        this.iq = iq;
        this.year = year;
        this.family = family;
        this.schedule = schedule;
    }

    public Human(String name, String surname, int year) {
        this(name, surname, year, 0, null, new String[][]{{}});
    }

    public Human() {
        this("undefined", "undefined", 1970);
    }

    public String[][] getSchedule() {
        return schedule;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getIq() {
        return iq;
    }

    public Family getFamily() {
        return family;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = schedule;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public void greetPet() {
        System.out.printf(GREET_PET, family.getPet().getNickname());
    }

    private String getPetTrickLevel() {
        return family.getPet().getTrickLevel() > 50 ? TRICKY : NOT_TRICKY;
    }

    public void describePet() {
        System.out.printf(
                "У меня есть %s, ему %s лет, он %s",
                family.getPet().getSpecies(),
                family.getPet().getAge(),
                getPetTrickLevel()
        );
    }

    @Override
    public String toString() {
        return String.format(
                "Human{name='%s', surname='%s', year=%d, iq=%d, schedule=%s}",
                name,
                surname,
                year,
                iq,
                Arrays.deepToString(schedule)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human human)) return false;
        return getYear() == human.getYear() && getIq() == human.getIq() && getName().equals(human.getName()) && getSurname().equals(human.getSurname()) && Objects.equals(getFamily(), human.getFamily()) && Arrays.equals(getSchedule(), human.getSchedule());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getSurname(), getYear(), getIq(), getFamily());
        result = 31 * result + Arrays.hashCode(getSchedule());
        return result;
    }

    @Override
    protected void finalize() throws Throwable {
        GarbageCollectorUtils.prepareToDelete(this);
        super.finalize();
    }
}
