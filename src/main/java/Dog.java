public class Dog extends Pet implements FoulPet {
    static final String FOUL = "Нужно хорошо замести следы...";
    static final String RESPOND = "Привет, хозяин. Я - %s. Я соскучился!";

    public Dog(PetSpecies species, String nickname, int age, int trickLevel, String[] habits) {
        super(species, nickname, age, trickLevel, habits);
    }

    public Dog(PetSpecies species, String nickname) {
        this(species, nickname, 0, 0, new String[]{});
    }

    public Dog() {
        this(PetSpecies.UNKNOWN, DEFAULT_PET_NAME);
    }

    public void foul() {
        System.out.println(FOUL);
    }

    @Override
    public void respond() {
        System.out.printf(RESPOND, nickname);
    }
}
