package com.example.avoiddangerousroad.service;

import com.example.avoiddangerousroad.entity.Node;
import com.example.avoiddangerousroad.folmula.RangeFormula;
import com.example.avoiddangerousroad.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class NodeService {

    @Autowired
    NodeRepository nodeRepository;

    RangeFormula rangeFormula;

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

    public List<Node> findInRangeNodeList(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        rangeFormula = new RangeFormula(startLatitude, startLongitude, endLatitude, endLongitude);
        return nodeRepository.findAllByLatitudeBetweenAndLongitudeBetween(rangeFormula.leftLatitude, rangeFormula.rightLatitude, rangeFormula.leftLongitude, rangeFormula.rightLongitude);
    }


    public Node findStartNode(List<Node> inRangeNodeList, double startLatitude, double startLongitude) {

        int index = 0;
        double minimumDistance = Double.MAX_VALUE;
        for (int i = 0 ; i < inRangeNodeList.size(); i++) {
            double distance = getDistance(startLatitude, startLongitude, inRangeNodeList.get(i).getLatitude(), inRangeNodeList.get(i).getLongitude());
            if (distance < minimumDistance) {
                index = i;
                minimumDistance = distance;
            }
        }
        return inRangeNodeList.get(index);
    }

    public Node findEndNode(List<Node> inRangeNodeList, double endLatitude, double endLongitude) {
        int index = 0;
        double minimumDistance = Double.MAX_VALUE;
        for (int i = 0 ; i < inRangeNodeList.size(); i++) {
            double distance = getDistance(endLatitude, endLongitude, inRangeNodeList.get(i).getLatitude(), inRangeNodeList.get(i).getLongitude());
            if (distance < minimumDistance) {
                index = i;
                minimumDistance = distance;
            }
        }
        return inRangeNodeList.get(index);
    }

    public double getDistance(double standardLatitude, double standardLongitude, double inListLatitude, double inListLongitude) {
        double yDistance = Math.pow((standardLatitude - inListLatitude), 2);
        double xDistance = Math.pow((standardLongitude - inListLongitude), 2);
        return Math.sqrt(yDistance + xDistance);
    }

}
