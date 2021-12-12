package com.example.avoiddangerousroad.controller;

import com.example.avoiddangerousroad.service.LinkService;
import com.example.avoiddangerousroad.service.NodeService;
import com.example.avoiddangerousroad.service.SubNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntityRegisterController {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private SubNodeService subNodeService;

    @PostMapping("/saveNode")
    public void setNodeInDB() {
        nodeService.saveNodeData();
    }

    @PostMapping("/saveLink")
    public void setLinkInDB() {
        linkService.saveLinkData();
    }

    @PostMapping("/saveSubNode")
    public void setSubNodeInDB() {
        subNodeService.saveSubNodeData();
    }
}
