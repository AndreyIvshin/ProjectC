package org.example.api;

import org.example.mapper.ItemMapper;
import org.example.model.Item;
import org.example.model.Unit;
import org.example.service.DealService;
import org.example.service.ItemService;
import org.example.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RestController
@RequestMapping("api/items")
public class ItemResource {

    @Autowired
    private ItemService itemService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private DealService dealService;
    @Autowired
    private ItemMapper itemMapper;


    @GetMapping
    public ResponseEntity<List<ItemMapper.ItemLite>> getItems() {
        return ResponseEntity.ok(itemService.findAll().stream().map(itemMapper::mapLite).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemMapper.ItemFull> getItem(@PathVariable Long id) {
        return itemService.find(id)
                .map(item -> ResponseEntity.ok(itemMapper.mapFull(item)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity postItem(@RequestBody @Valid ItemMapper.ItemToSave save) {
        return unitService.find(save.getUnit().getId())
                .map(unit1 -> ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(itemService.save(itemMapper.mapToSave(save)).getId()).toUri()).build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity putItem(@PathVariable Long id, @RequestBody @Valid ItemMapper.ItemToSave save) {
        return itemService.find(id)
                .map(item -> unitService.find(save.getUnit().getId()).map(unit -> {
                    Item toSave = itemMapper.mapToSave(save);
                    toSave.setId(id);
                    itemService.save(toSave);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteItem(@PathVariable Long id) {
        return itemService.find(id)
                .map(item -> {
                    if (dealService.findAllByItem_Id(item.getId()).isEmpty()) {
                        itemService.delete(item.getId());
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
