package com.example.avoiddangerousroad.controller;

import com.example.avoiddangerousroad.DTO.LinkDTO;
import com.example.avoiddangerousroad.entity.*;
import com.example.avoiddangerousroad.folmula.Dijkstra;
import com.example.avoiddangerousroad.repository.LinkNodeRepository;
import com.example.avoiddangerousroad.repository.LinkRepository;
import com.example.avoiddangerousroad.repository.NodeRepository;
import com.example.avoiddangerousroad.repository.SubNodeRepository;
import com.example.avoiddangerousroad.service.LinkNodeService;
import com.example.avoiddangerousroad.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    LinkNodeService linkNodeService;
    @Autowired
    LinkNodeRepository linkNodeRepository;
    @Autowired
    NodeService nodeService;
    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    LinkRepository linkRepository;
    @Autowired
    SubNodeRepository subNodeRepository;


    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }


    @RequestMapping(value = "/findRoad")
    public String findRoad(HttpServletRequest request, Model model) {


        double startLatitude = Double.parseDouble(request.getParameter("startLatitude"));
        double startLongitude = Double.parseDouble(request.getParameter("startLongitude"));
        double endLatitude = Double.parseDouble(request.getParameter("endLatitude"));
        double endLongitude = Double.parseDouble(request.getParameter("endLongitude"));
        String[] preferences = request.getParameterValues("preference[]");

        List<Link> all = linkRepository.findAll();
        List<LinkDTO> linkWeightDTOList = new ArrayList<>();
        List<LinkDTO> linkDistanceDTOList = new ArrayList<>();
        for (Link link : all) {
            List<LinkNode> allByLink = linkNodeRepository.findAllByLink(link);
            Node node1 = allByLink.get(0).getNode();
            Node node2 = allByLink.get(1).getNode();
            // 링크 디티오에 weight 대신에 거리변수 집어 넣는다.
            LinkDTO linkWeightDTO = new LinkDTO(link.getId(), node1, link.getWeight(), node2);
            LinkDTO linkDistanceDTO = new LinkDTO(link.getId(), node1, link.getDistance(), node2);
            linkWeightDTOList.add(linkWeightDTO);
            linkDistanceDTOList.add(linkDistanceDTO);
        }

        // 다익스트라 알고리즘 준비
        Dijkstra dijkstraWeight = new Dijkstra(linkWeightDTOList);
        Dijkstra dijkstraDistance = new Dijkstra(linkDistanceDTOList);

        List<Node> allNode = nodeRepository.findAll();

        Node startNode = nodeService.findStartNode(allNode, startLatitude, startLongitude);
        Node endNode = nodeService.findEndNode(allNode, endLatitude, endLongitude);

        ArrayList<Long> algorithmWeight = dijkstraWeight.algorithm(startNode, endNode);
        ArrayList<Long> algorithmDistance = dijkstraDistance.algorithm(startNode, endNode);

        List<Link> weightLinkList = new ArrayList<>();
        List<Link> distanceLinkList = new ArrayList<>();

        // weightLinkList 돌리기
        for (int i = 0; i < algorithmWeight.size() - 1; i++) {

            List<LinkNode> allByNodeId1 = linkNodeRepository.findAllByNodeId(algorithmWeight.get(i));
            List<LinkNode> allByNodeId2 = linkNodeRepository.findAllByNodeId(algorithmWeight.get(i + 1));

            for (LinkNode linkNode1 : allByNodeId1) {
                for (LinkNode linkNode2 : allByNodeId2) {
                    Link link1 = linkNode1.getLink();
                    Link link2 = linkNode2.getLink();
                    if (link1.getId() == link2.getId()) {
                        weightLinkList.add(link1);
                        break;
                    }
                }
            }
        }

        // distanceLinkList 돌리기
        for (int i = 0; i < algorithmDistance.size() - 1; i++) {

            List<LinkNode> allByNodeId1 = linkNodeRepository.findAllByNodeId(algorithmDistance.get(i));
            List<LinkNode> allByNodeId2 = linkNodeRepository.findAllByNodeId(algorithmDistance.get(i + 1));

            for (LinkNode linkNode1 : allByNodeId1) {
                for (LinkNode linkNode2 : allByNodeId2) {
                    Link link1 = linkNode1.getLink();
                    Link link2 = linkNode2.getLink();
                    if (link1.getId() == link2.getId()) {
                        distanceLinkList.add(link1);
                        break;
                    }
                }
            }
        }


        ArrayList<List<SubNode>> weightSubNodeLists = new ArrayList<List<SubNode>>();
        ArrayList<List<SubNode>> distanceSubNodeLists = new ArrayList<List<SubNode>>();


        for (Link link : weightLinkList) {
            List<SubNode> subNodeList = subNodeRepository.findAllByLink(link);
            weightSubNodeLists.add(subNodeList);
        }

        for (Link link : distanceLinkList) {
            List<SubNode> subNodeList = subNodeRepository.findAllByLink(link);
            distanceSubNodeLists.add(subNodeList);
        }
        model.addAttribute("weightSubNodeLists", weightSubNodeLists);
        model.addAttribute("distanceSubNodeLists", distanceSubNodeLists);
        model.addAttribute("startNode", startNode);
        model.addAttribute("endNode", endNode);
        model.addAttribute("startLatitude", startLatitude);
        model.addAttribute("startLongitude", startLongitude);
        model.addAttribute("endLatitude", endLatitude);
        model.addAttribute("endLongitude", endLongitude);
        return "findRoad";
    }
}
