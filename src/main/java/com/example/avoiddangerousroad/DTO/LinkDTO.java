package com.example.avoiddangerousroad.DTO;

import com.example.avoiddangerousroad.entity.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LinkDTO {
    private long id;
    private Node node1;
    private double weight;
    private Node node2;
}
