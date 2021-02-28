import java.util.Arrays;
import java.util.Objects;

enum PetSpecies {
    DOG, ROBOT_CAT, UNKNOWN, DOMESTIC_CAT
}

abstract public class Pet {
    PetSpecies species;
    String nickname;
    int age;
    int trickLevel;
    String[] habits;

    static final String DEFAULT_PET_NAME = "no-name";
    static final String EAT = "Я кушаю!";
    static final String FOUL = "Нужно хорошо замести следы...";
    static final String RESPOND = "Привет, хозяин. Я - %s. Я соскучился!";


    public Pet(PetSpecies species, String nickname, int age, int trickLevel, String[] habits) {
        this.species = species;
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    public Pet(PetSpecies species, String nickname) {
        this(species, nickname, 0, 0, new String[]{});
    }

    public Pet() {
        this(PetSpecies.UNKNOWN, DEFAULT_PET_NAME);
    }

    public void eat() {
        System.out.println(EAT);
    }

    public void respond() {
        System.out.printf(RESPOND, nickname);
    }

    public void foul() {
        System.out.println(FOUL);
    }

    public String getNickname() {
        return nickname;
    }

    public int getTrickLevel() {
        return trickLevel;
    }

    public PetSpecies getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public void setSpecies(PetSpecies species) {
        this.species = species;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setHabits(String[] habits) {
        this.habits = habits;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTrickLevel(int trickLevel) {
        this.trickLevel = trickLevel;
    }

    public String[] getHabits() {
        return habits;
    }

    @Override
    public String toString() {
        return String.format(
                "%s{nickname='%s', age=%d, trickLevel=%d, habits=%s}",
                species,
                nickname,
                age,
                trickLevel,
                Arrays.toString(habits)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet pet)) return false;
        return getAge() == pet.getAge() && getTrickLevel() == pet.getTrickLevel() && getSpecies() == pet.getSpecies() && getNickname().equals(pet.getNickname()) && Arrays.equals(habits, pet.habits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getSpecies(), getNickname(), getAge(), getTrickLevel());
        result = 31 * result + Arrays.hashCode(habits);
        return result;
    }

    @Override
    protected void finalize() throws Throwable {
        GarbageCollectorUtils.prepareToDelete(this);
        super.finalize();
    }
}
