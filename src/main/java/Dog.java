public class Dog extends Pet {
    public Dog(PetSpecies species, String nickname, int age, int trickLevel, String[] habits) {
        super(species, nickname, age, trickLevel, habits);
    }

    public Dog(PetSpecies species, String nickname) {
        this(species, nickname, 0, 0, new String[]{});
    }

    public Dog() {
        this(PetSpecies.UNKNOWN, DEFAULT_PET_NAME);
    }
}
