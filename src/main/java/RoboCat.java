import java.util.HashSet;
import java.util.Set;

public class RoboCat extends Pet implements FoulPet {
    static final String FOUL = "Нужно хорошо замести следы...";

    static final String RESPOND = "Привет, хозяин. Я - %s. Я соскучился!";

    public RoboCat(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        setSpecies(PetSpecies.ROBOT_CAT);
    }

    public RoboCat(String nickname) {
        this(nickname, 0, 0, new HashSet<String>());
    }

    public RoboCat() {
        this(DEFAULT_PET_NAME);
    }

    public void foul() {
        System.out.println(FOUL);
    }

    @Override
    public void respond() {
        System.out.printf(RESPOND, nickname);
    }

    @Override
    public PetSpecies getDefaultSpecies() {
        return PetSpecies.ROBOT_CAT;
    }
}
