package org.danit.interfaces;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(int id);

    List<T> getAll();

    void save(T t);

    boolean delete(T t);

    boolean delete(int i);
}
