package com.decathlon.devalert.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Developer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long developerId;

    private String name;
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teamId", referencedColumnName = "teamId")
    private Team team;


}
