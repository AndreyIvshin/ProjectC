package org.example.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@javax.persistence.Entity
@Table(name = "entity_table")
public class Entity {

    @Id
    @SequenceGenerator(name = "entity_seq_gen", sequenceName = "entity_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_gen")
    @Column(name = "entity_id")
    Long id;

    @Column(name = "entity_name")
    String name;

}
