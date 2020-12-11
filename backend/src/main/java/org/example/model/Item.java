package org.example.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@javax.persistence.Entity
@Table(name = "item_table")
public class Item {

    @Id
    @SequenceGenerator(name = "item_seq_gen", sequenceName = "item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq_gen")
    @Column(name = "item_id")
    Long id;

    @Column(name = "item_name")
    String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_unit_unit_fk")
    Unit unit;

}
