package com.example.avoiddangerousroad.repository;

import com.example.avoiddangerousroad.entity.Link;
import com.example.avoiddangerousroad.entity.LinkNode;
import com.example.avoiddangerousroad.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkNodeRepository extends JpaRepository<LinkNode, Long> {
    List<LinkNode> findAllByNode(Node node);

    List<LinkNode> findAllByNodeId(long nodeId);

    List<LinkNode> findAllByLink(Link link);
}
