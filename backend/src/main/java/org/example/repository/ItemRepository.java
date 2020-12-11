package org.example.repository;

import org.example.model.Item;
import org.example.model.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findAllByUnit_Id(Long id);
}
