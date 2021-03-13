public class Man extends Human {
    @Override
    public void greetPet() {
        System.out.printf(GREET_PET, family.getPet().getNickname());
    }

    public void repairCar() {
        System.out.print("repairCar");
    }
}
