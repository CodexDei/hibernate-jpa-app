package org.codexdei.hibernateapp.repositories;

import java.util.List;

public interface CrudRepository <T> {

        List<T> list();
        T findId(Long id);
        void save(T t);
        void delete(Long id);
    }
