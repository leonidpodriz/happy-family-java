public class GarbageCollectorUtils {
    static <T> String getInstanceInfo(T instance) {
        return instance.toString();
    }

    static <T> void prepareToDelete(T instance) {
        System.out.printf("DELETING: %s\n", getInstanceInfo(instance));
    }
}
