package com.example.avoiddangerousroad.service;

import com.example.avoiddangerousroad.entity.Link;
import com.example.avoiddangerousroad.entity.LinkNode;
import com.example.avoiddangerousroad.entity.Node;
import com.example.avoiddangerousroad.repository.LinkNodeRepository;
import com.example.avoiddangerousroad.repository.LinkRepository;
import com.example.avoiddangerousroad.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class LinkService {

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    LinkNodeRepository linkNodeRepository;

    String line = "";

    public void saveLinkData() {
        try {
            BufferedReader br = new BufferedReader((new FileReader("src/main/resources/link_to_node.csv")));

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                long id = Long.parseLong(data[0]);
                long node1_id = Long.parseLong(data[1]);
                long node2_id = Long.parseLong(data[2]);
                double weight = Double.parseDouble(data[3]);
                double distance = Double.parseDouble(data[4]);

                Link link = new Link();
                link.setId(id);
                link.setWeight(weight);
                link.setDistance(distance);
                linkRepository.save(link);

                Node node1 = nodeRepository.findById(node1_id)
                        .orElseThrow(IllegalArgumentException::new);
                Node node2 = nodeRepository.findById(node2_id)
                        .orElseThrow(IllegalArgumentException::new);

                LinkNode linkNode1 = new LinkNode();
                linkNode1.setLink(link);
                linkNode1.setNode(node1);
                linkNodeRepository.save(linkNode1);

                LinkNode linkNode2 = new LinkNode();
                linkNode2.setLink(link);
                linkNode2.setNode(node2);
                linkNodeRepository.save(linkNode2);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
