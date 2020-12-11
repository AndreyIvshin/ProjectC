package org.example.repository;

import org.example.model.Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends CrudRepository<Entity, Long> {
}
