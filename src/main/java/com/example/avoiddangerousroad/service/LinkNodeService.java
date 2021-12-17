package com.example.avoiddangerousroad.service;

import com.example.avoiddangerousroad.entity.LinkNode;
import com.example.avoiddangerousroad.entity.Node;
import com.example.avoiddangerousroad.repository.LinkNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkNodeService {

    @Autowired
    LinkNodeRepository linkNodeRepository;

    public List<LinkNode> findAllByNode(Node node) {
        return linkNodeRepository.findAllByNode(node);
    }

    public List<LinkNode> findAll() {
        return linkNodeRepository.findAll();
    }
}
