package org.danit.family;

import org.danit.interfaces.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FamilyDao implements Dao<Family> {
    private final List<Family> families = new ArrayList<>();

    @Override
    public Optional<Family> get(int id) {
        return Optional.ofNullable(families.get(id));
    }

    @Override
    public void save(Family family) {
        if (!families.contains(family)) {
            families.add(family);
        }
    }

    @Override
    public List<Family> getAll() {
        return families;
    }

    @Override
    public boolean delete(int i) {
        boolean status = false;
        if (families.size() > i) {
            status = delete(families.get(i));
        }

        return status;
    }

    @Override
    public boolean delete(Family family) {
        return families.remove(family);
    }
}
