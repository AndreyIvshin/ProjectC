package org.example.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@javax.persistence.Entity
@Table(name = "deal_table")
public class Deal {

    @Id
    @SequenceGenerator(name = "deal_seq_gen", sequenceName = "deal_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deal_seq_gen")
    @Column(name = "deal_id")
    Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "deal_date")
    Date date;

    @Column(name = "deal_price")
    BigDecimal price;

    @Column(name = "deal_quantity")
    BigDecimal quantity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deal_item_item_fk")
    Item item;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deal_seller_entity_fk")
    Entity seller;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deal_buyer_entity_fk")
    Entity buyer;

}
