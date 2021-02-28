public class DomesticCat extends Pet {
    public DomesticCat(PetSpecies species, String nickname, int age, int trickLevel, String[] habits) {
        super(species, nickname, age, trickLevel, habits);
    }

    public DomesticCat(PetSpecies species, String nickname) {
        this(species, nickname, 0, 0, new String[]{});
    }

    public DomesticCat() {
        this(PetSpecies.UNKNOWN, DEFAULT_PET_NAME);
    }
}
