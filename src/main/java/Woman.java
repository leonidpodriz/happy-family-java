public class Woman extends Human {
    @Override
    public void greetPet() {
        System.out.printf(GREET_PET, family.getPet().getNickname());
    }
}
