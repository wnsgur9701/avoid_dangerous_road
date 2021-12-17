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


        // 161657 시작점
        // 790

        // 164785 끝점
        // 1239
        double startLatitude = 35.21412803;
        double startLongitude = 129.6691841;

        // 827
        double endLatitude = 35.21530327;
        double endLongitude = 129.6655137;

//        // 일정한 거리 내에 있는 노드 가져오느 공식
//        RangeFormula rangeFormula = new RangeFormula(startLatitude, startLongitude, endLatitude, endLongitude);
//        List<Node> inRangeNodeList = nodeService.findInRangeNodeList(startLatitude, startLongitude, endLatitude, endLongitude);
//        boolean isTrue = true;
//        System.out.println("inRangeNodeList = " + inRangeNodeList.size());
//        for (Node node : inRangeNodeList) {
//            if ((node.getLatitude() >= rangeFormula.leftLatitude) && (node.getLatitude() <= rangeFormula.rightLatitude)
//                    && (node.getLongitude() >= rangeFormula.leftLongitude) && (node.getLongitude() <= rangeFormula.rightLongitude)) {
//                Assertions.assertTrue(isTrue);
//            } else {
//                isTrue = false;
//                Assertions.assertTrue(isTrue);
//            }
//        }

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

//        // 2. 범위 안에 있는 link들도 다 가져오기 위해서 linkNode를 가져온다.
//        List<LinkNode> inRangeLinkNodeList = new ArrayList<>();
//
//        for (Node node : inRangeNodeList) {
//            List<LinkNode> linkNodeList = linkNodeService.findAllByNode(node);
//            for (LinkNode linkNode : linkNodeList) {
//                inRangeLinkNodeList.add(linkNode);
//            }
//        }
//
//        for (LinkNode linkNode : inRangeLinkNodeList) {
//
//            Link link = linkNode.getLink();
//            List<LinkNode> linkNodeListByLink = linkNodeRepository.findAllByLink(link);
//            Node node1 = linkNodeListByLink.get(0).getNode();
//            Node node2 = linkNodeListByLink.get(1).getNode();
//
//            LinkDTO linkDto = new LinkDTO(link.getId(), node1, link.getWeight(), node2);
//            linkDtOList.add(linkDto);
//        }

        
// ==========================================================        
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
