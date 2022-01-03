package com.example.avoiddangerousroad.service;

import com.example.avoiddangerousroad.DTO.LinkDTO;
import com.example.avoiddangerousroad.entity.*;
import com.example.avoiddangerousroad.folmula.Dijkstra;
import com.example.avoiddangerousroad.repository.LinkNodeRepository;
import com.example.avoiddangerousroad.repository.LinkRepository;
import com.example.avoiddangerousroad.repository.NodeRepository;
import com.example.avoiddangerousroad.repository.SubNodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class NodeServiceTest {

    @Autowired
    NodeService nodeService;

    @Autowired
    LinkNodeService linkNodeService;

    @Autowired
    LinkNodeRepository linkNodeRepository;

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    SubNodeRepository subNodeRepository;

    @Test
    void 노드_가져오기_테스트() {


        // 새로운 것(최종) - 후보 1
        // 출발지 1187
        // 도착지 720
        //  - 후보 2
        // 출발지 1061 도착지 715번
        // - 후보 3
        // 출발지 599번 도착지 974






        // 161657 시작점
        // 원래 790
        // 740
        double startLatitude = 37.48719506;
        double startLongitude = 126.9239876;

        // 827
        double endLatitude = 37.48684056;
        double endLongitude = 126.9266488;

        List<LinkDTO> linkDtOList = new ArrayList<>();
        List<Link> all = linkRepository.findAll();
        for (Link link : all) {
            List<LinkNode> allByLink = linkNodeRepository.findAllByLink(link);
            Node node1 = allByLink.get(0).getNode();
            Node node2 = allByLink.get(1).getNode();
            LinkDTO linkDto = new LinkDTO(link.getId(), node1, link.getWeight(), node2);
            linkDtOList.add(linkDto);
        }

        System.out.println("linkDtOList.size() = " + linkDtOList.size());

        Dijkstra dijkstra = new Dijkstra(linkDtOList);

        List<Node> allNode = nodeRepository.findAll();

        Node startNode = nodeService.findStartNode(allNode, startLatitude, startLongitude);
        System.out.println("startNode.getId() = " + startNode.getId());
        Node endNode = nodeService.findEndNode(allNode, endLatitude, endLongitude);
        System.out.println("endNode.getId() = " + endNode.getId());

        ArrayList<Long> algorithm = dijkstra.algorithm(startNode, endNode);
        for (Long aLong : algorithm) {
            System.out.println("aLong = " + aLong);
        }

        List<Link> linkList = new ArrayList<>();

        for (int i = 0; i < algorithm.size() - 1; i++) {

            List<LinkNode> allByNodeId1 = linkNodeRepository.findAllByNodeId(algorithm.get(i));
            List<LinkNode> allByNodeId2 = linkNodeRepository.findAllByNodeId(algorithm.get(i + 1));

            for (LinkNode linkNode1 : allByNodeId1) {
                for (LinkNode linkNode2 : allByNodeId2) {
                    Link link1 = linkNode1.getLink();
                    Link link2 = linkNode2.getLink();
                    if (link1.getId() == link2.getId()) {
                        linkList.add(link1);
                        System.out.println("link1.getId() = " + link1.getId());
                        break;
                    }
                }
            }
        }


        List<SubNode> subNodeList = new ArrayList<>();

        for (Link link : linkList) {
            List<SubNode> allByLink = subNodeRepository.findAllByLink(link);
            for (SubNode subNode : allByLink) {
                subNodeList.add(subNode);
            }
        }



    }


}
