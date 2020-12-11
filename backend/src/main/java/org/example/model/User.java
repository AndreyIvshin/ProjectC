package org.example.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@javax.persistence.Entity
@Table(name = "user_table")
public class User {

    @Id
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @Column(name = "user_id")
    Long id;

    @Column(name = "user_username")
    String username;

    @Column(name = "user_password")
    String password;

}
