package com.example.avoiddangerousroad.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubNode {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "LINK_ID")
    private Link link;

    private double latitude;
    private double longitude;

    public void setLink(Link link) {
        this.link = link;
        link.getSubNodeList().add(this);
    }
}
