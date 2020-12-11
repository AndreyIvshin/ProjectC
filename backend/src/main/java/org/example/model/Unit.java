package org.example.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@javax.persistence.Entity
@Table(name = "unit_table")
public class Unit {

    @Id
    @SequenceGenerator(name = "unit_seq_gen", sequenceName = "unit_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_seq_gen")
    @Column(name = "unit_id")
    Long id;

    @Column(name = "unit_name")
    String name;

}
