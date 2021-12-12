package com.example.avoiddangerousroad.service;

import com.example.avoiddangerousroad.entity.Node;
import com.example.avoiddangerousroad.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class NodeService {

    @Autowired
    NodeRepository nodeRepository;

    String line = "";

    public void saveNodeData() {
        try {
            BufferedReader br = new BufferedReader((new FileReader("src/main/resources/NodeIdWithCoord.csv")));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                long id = Long.parseLong(data[0]);
                double latitude = Double.parseDouble(data[1]);
                double longitude = Double.parseDouble(data[2]);
                Node node = new Node(id, latitude, longitude);
                nodeRepository.save(node);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
