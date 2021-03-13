public class Fish extends Pet {

    static final String RESPOND = "Привет, хозяин. Я - %s. Я соскучился!";

    @Override
    public void respond() {
        System.out.printf(RESPOND, nickname);
    }
}
