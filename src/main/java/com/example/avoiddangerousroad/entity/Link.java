package com.example.avoiddangerousroad.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Link {

    @Id
    private long id;
    private double weight;

    @OneToMany(mappedBy = "link")
    private List<SubNode> subNodeList = new ArrayList<>();

}
