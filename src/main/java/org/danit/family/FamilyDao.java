package org.danit.family;

import org.danit.interfaces.Dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FamilyDao implements Dao<Family> {
    private final ArrayList<Family> families = new ArrayList<>();
    private final String filePath = "db.bin";

    public FamilyDao() {
        loadData(filePath);
    }

    @Override
    public Optional<Family> get(int id) {
        return Optional.ofNullable(families.get(id));
    }

    @Override
    public void save(Family family) {
        if (!families.contains(family)) {
            families.add(family);
        }

        saveData(filePath);
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

    public boolean saveData(String filePath) {

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(families);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean loadData(String filePath) {
        ObjectInputStream ois;
        ArrayList<Family> familyList;

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }

        try {
            ois = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            return false;
        }

        try {
            familyList = (ArrayList<Family>) ois.readObject();
            families.addAll(familyList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }
}
