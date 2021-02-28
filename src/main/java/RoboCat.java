public class RoboCat extends Pet {
    public RoboCat(PetSpecies species, String nickname, int age, int trickLevel, String[] habits) {
        super(species, nickname, age, trickLevel, habits);
    }

    public RoboCat(PetSpecies species, String nickname) {
        this(species, nickname, 0, 0, new String[]{});
    }

    public RoboCat() {
        this(PetSpecies.UNKNOWN, DEFAULT_PET_NAME);
    }
}
