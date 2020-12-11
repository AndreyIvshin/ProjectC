package org.example.api;

import org.example.mapper.UnitMapper;
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
import java.util.stream.Collectors;

@Transactional
@RestController
@RequestMapping("api/units")
public class UnitResource {

    @Autowired
    private UnitService unitService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UnitMapper unitMapper;

    @GetMapping
    public ResponseEntity<List<UnitMapper.UnitLite>> getUnits() {
        return ResponseEntity.ok(unitService.findAll().stream().map(unitMapper::mapLite).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<UnitMapper.UnitFull> getUnit(@PathVariable Long id) {
        return unitService.find(id)
                .map(unit -> ResponseEntity.ok(unitMapper.mapFull(unit)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity postUnit(@RequestBody @Valid UnitMapper.UnitToSave save) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(unitService.save(unitMapper.mapToSave(save)).getId()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity putUnit(@PathVariable Long id, @RequestBody @Valid UnitMapper.UnitToSave save) {
        return unitService.find(id)
                .map(unit -> {
                    Unit toSave = unitMapper.mapToSave(save);
                    toSave.setId(id);
                    unitService.save(toSave);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUnit(@PathVariable Long id) {
        return unitService.find(id)
                .map(unit -> {
                    if (itemService.findAllByUnit_Id(unit.getId()).isEmpty()) {
                        unitService.delete(unit.getId());
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
