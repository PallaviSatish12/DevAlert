package com.decathlon.devalert.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDetails implements Serializable {

    private List<Developer> developers;
    private String teamName;
    private String address;
    private Long teamId;
}
