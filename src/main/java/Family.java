import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Family {
    private final Human mother;
    private final Human father;
    private List<Human> children;
    private Pet pet;

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<Human>();

        mother.setFamily(this);
        father.setFamily(this);
    }

    public List<Human> getChildren() {
        return children;
    }

    public Human getMother() {
        return mother;
    }

    public Human getFather() {
        return father;
    }

    public boolean deleteChild(int index) {
        if (index >= 0 && index < children.size()) {
            Human h = children.get(index);
            h.setFamily(null);
            children.remove(index);
            return true;
        } else {
            return false;
        }
    }

    private int findChildIndex(Human child) {
        for (int index = 0; index < children.size(); index++){
            if (child == children.get(index)) {
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
        return 2 + children.size();
    }

    public void addChild(Human child) {
        children.add(child);
        child.setFamily(this);
    }

    @Override
    public String toString() {
        return "Family{" +
                "mother=" + mother +
                ", father=" + father +
                ", children=" + children +
                ", pet=" + pet +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        GarbageCollectorUtils.prepareToDelete(this);
        super.finalize();
    }
}
