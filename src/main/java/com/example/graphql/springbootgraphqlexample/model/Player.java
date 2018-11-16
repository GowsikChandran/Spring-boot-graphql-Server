package com.example.graphql.springbootgraphqlexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Player {

    @Id
    private String id;
    private String name;
    private String country;
    private String[] clubsPlayed;
    private String dateOfBirth;
    private String age;


}
