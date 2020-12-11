package org.example.service;

import org.example.model.Item;
import org.example.model.Unit;
import org.example.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService extends AbstractService<Item, Long, ItemRepository> {

    public List<Item> findAllByUnit_Id(Long id) {
        return r.findAllByUnit_Id(id);
    }

}
