import java.util.Arrays;

public class Family {
    private final Human mother;
    private final Human father;
    private Human[] children;
    private Pet pet;

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.children = new Human[]{};

        mother.setFamily(this);
        father.setFamily(this);
    }

    public Human[] getChildren() {
        return children;
    }

    public Human getMother() {
        return mother;
    }

    public Human getFather() {
        return father;
    }

    public boolean deleteChild(int index) {
        if (index >= 0 && index < children.length) {
            Human h = children[index];
            h.setFamily(null);
            children = removeByIndex(children, index);
            return true;
        } else {
            return false;
        }
    }

    private int findChildIndex(Human child) {
        for (int index = 0; index < children.length; index++){
            if (child == children[index]) {
                return index;
            }
        }

        return -1;
    }

    public boolean deleteChild(Human child) {
        int index = findChildIndex(child);
        return deleteChild(index);
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    private static Human[] removeByIndex(Human[] a, int index) {
        if (a == null || index < 0 || index >= a.length) {
            return a;
        }

        Human[] result = new Human[a.length - 1];

        System.arraycopy(a, 0, result, 0, index);
        System.arraycopy(a, index + 1, result, index, a.length - index - 1);

        return result;
    }

    public Pet getPet() {
        return pet;
    }

    public int countFamily() {
        return 2 + children.length;
    }

    public void addChild(Human child) {
        children = Arrays.copyOf(children, children.length + 1);
        children[children.length - 1] = child;
        child.setFamily(this);
    }

    @Override
    public String toString() {
        return "Family{" +
                "mother=" + mother +
                ", father=" + father +
                ", children=" + Arrays.toString(children) +
                ", pet=" + pet +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        GarbageCollectorUtils.prepareToDelete(this);
        super.finalize();
    }
}
