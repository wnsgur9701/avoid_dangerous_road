package com.example.avoiddangerousroad.service;

import com.example.avoiddangerousroad.entity.Link;
import com.example.avoiddangerousroad.entity.LinkNode;
import com.example.avoiddangerousroad.entity.Node;
import com.example.avoiddangerousroad.entity.SubNode;
import com.example.avoiddangerousroad.repository.LinkRepository;
import com.example.avoiddangerousroad.repository.SubNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class SubNodeService {

    @Autowired
    SubNodeRepository subNodeRepository;

    @Autowired
    LinkRepository linkRepository;

    String line = "";

    public void saveSubNodeData() {
        try {
            BufferedReader br = new BufferedReader((new FileReader("src/main/resources/subNodeWithCoord.csv")));

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                long link_id = Long.parseLong(data[0]);
                long id = Long.parseLong(data[1]);
                double latitude = Double.parseDouble(data[2]);
                double longitude = Double.parseDouble(data[3]);

                Link link = linkRepository.findById(link_id)
                        .orElseThrow(IllegalArgumentException::new);

                SubNode subNode = new SubNode();
                subNode.setId(id);
                subNode.setLatitude(latitude);
                subNode.setLongitude(longitude);
                subNode.setLink(link);

                subNodeRepository.save(subNode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
