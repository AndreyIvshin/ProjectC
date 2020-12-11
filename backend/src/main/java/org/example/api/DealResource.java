package org.example.api;

import org.example.mapper.DealMapper;
import org.example.model.Deal;
import org.example.model.Entity;
import org.example.model.Item;
import org.example.model.Unit;
import org.example.service.DealService;
import org.example.service.EntityService;
import org.example.service.ItemService;
import org.example.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("api/deals")
public class DealResource {

    @Autowired
    private DealService dealService;
    @Autowired
    private EntityService entityService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private DealMapper dealMapper;

    @GetMapping
    public ResponseEntity<List<DealMapper.DealLite>> getDeals() {
        return ResponseEntity.ok(dealService.findAll().stream().map(dealMapper::mapLite).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<DealMapper.DealFull> getDeal(@PathVariable Long id) {
        return dealService.find(id)
                .map(deal -> ResponseEntity.ok(dealMapper.mapFull(deal)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity postDeal(@RequestBody @Valid DealMapper.DealToSave save) {
        Deal map = dealMapper.mapToSave(save);
        Optional<Item> item = itemService.find(map.getItem().getId());
        Optional<Entity> seller = entityService.find(map.getSeller().getId());
        Optional<Entity> buyer = entityService.find(map.getBuyer().getId());
        if (seller.equals(buyer)) {
            return ResponseEntity.badRequest().build();
        }
        if (item.isPresent() && seller.isPresent() && buyer.isPresent()) {
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dealService.save(map).getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity putDeal(@PathVariable Long id, @RequestBody @Valid DealMapper.DealToSave save) {
        Deal map = dealMapper.mapToSave(save);
        Optional<Item> item = itemService.find(map.getItem().getId());
        Optional<Entity> seller = entityService.find(map.getSeller().getId());
        Optional<Entity> buyer = entityService.find(map.getBuyer().getId());
        if (seller.equals(buyer)) {
            return ResponseEntity.badRequest().build();
        }
        if (item.isPresent() && seller.isPresent() && buyer.isPresent()) {
            return dealService.find(id)
                    .map(deal -> {
                        Deal toSave = dealMapper.mapToSave(save);
                        toSave.setId(id);
                        dealService.save(toSave);
                        return ResponseEntity.ok().build();
                    }).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteDeal(@PathVariable Long id) {
        return dealService.find(id)
                .map(deal -> {
                    dealService.delete(deal.getId());
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
