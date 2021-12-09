package com.example.avoiddangerousroad.entity;

import lombok.Getter;

@Getter
public class Link {

    private long id;
    private double weight;
    private Node node1;
    private Node node2;

    public Link(long id, Node node1,double weight, Node node2) {
        this.id = id;
        this.weight = weight;
        this.node1 = node1;
        this.node2 = node2;
    }
}
