package com.example.avoiddangerousroad.repository;

import com.example.avoiddangerousroad.entity.Link;
import com.example.avoiddangerousroad.entity.SubNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubNodeRepository extends JpaRepository<SubNode, Long> {

    List<SubNode> findAllByLink(Link link);
}
