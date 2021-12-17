package com.example.avoiddangerousroad.repository;

import com.example.avoiddangerousroad.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node,Long> {
    List<Node> findAllByLatitudeBetweenAndLongitudeBetween(double leftLatitude, double rightLatitude, double leftLongitude, double rightLongitude);
}
