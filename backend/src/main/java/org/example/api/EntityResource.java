package org.example.api;

import org.example.mapper.EntityMapper;
import org.example.model.Entity;
import org.example.service.DealService;
import org.example.service.EntityService;
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
@RequestMapping("api/entities")
public class EntityResource {

    @Autowired
    private EntityService entityService;
    @Autowired
    private DealService dealService;
    @Autowired
    private EntityMapper entityMapper;

    @GetMapping
    public ResponseEntity<List<EntityMapper.EntityLite>> getEntities() {
        return ResponseEntity.ok(entityService.findAll().stream().map(entityMapper::mapLite).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityMapper.EntityFull> getEntity(@PathVariable Long id) {
        return entityService.find(id)
                .map(entity -> ResponseEntity.ok(entityMapper.mapFull(entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity postEntity(@RequestBody @Valid EntityMapper.EntityToSave save) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(entityService.save(entityMapper.mapToSave(save)).getId()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity putEntity(@PathVariable Long id, @RequestBody @Valid EntityMapper.EntityToSave save) {
        return entityService.find(id)
                .map(entity -> {
                    Entity toSave = entityMapper.mapToSave(save);
                    toSave.setId(id);
                    entityService.save(toSave);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEntity(@PathVariable Long id) {
        return entityService.find(id)
                .map(entity -> {
                    if (dealService.findAllByBuyer_Id(entity.getId()).isEmpty()
                            && dealService.findAllBySeller_Id(entity.getId()).isEmpty()) {
                        entityService.delete(entity.getId());
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
