package com.example.avoiddangerousroad.entity;

import lombok.Getter;


@Getter
public class Node {

    private long id;
    private double latitude;
    private double longitude;

    public Node(long id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
