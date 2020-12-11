package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T, ID, R extends CrudRepository<T, ID>> {

    @Autowired
    protected R r;

    public <S extends T> List<S> saveAll(Iterable<S> var1) {
        return (List<S>) r.saveAll(var1);
    }

    public <S extends T> S save(S var1) {
        return r.save(var1);
    }

    public Optional<T> find(ID var1) {
        return r.findById(var1);
    }

    public List<T> findAll() {
        return (List<T>) r.findAll();
    }

    public void delete(ID var1) {
        r.deleteById(var1);
    }
}
