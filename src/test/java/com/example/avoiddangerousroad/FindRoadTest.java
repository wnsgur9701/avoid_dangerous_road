package com.example.avoiddangerousroad;

import com.example.avoiddangerousroad.entity.Dijkstra;
import com.example.avoiddangerousroad.entity.Link;
import com.example.avoiddangerousroad.entity.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindRoadTest {

    // LIST들 선언
    private List<Node> nodeList = new ArrayList<Node>();
    private List<Link> linkList = new ArrayList<Link>();

    // Node 선언
    private Node a;
    private Node b;
    private Node c;
    private Node d;
    private Node e;
    private Node f;
    private Node g;
    private Node z;

    // Link 선언
    private Link link1;
    private Link link2;
    private Link link3;
    private Link link4;
    private Link link5;
    private Link link6;
    private Link link7;
    private Link link8;
    private Link link9;
    private Link link10;
    private Link link11;
    private Link link12;



    @BeforeAll
    void setUp() {
        // 노드 8개 생성
        // 각각 id와 위도 경도를 가지고 있다.
        a = new Node(11, 37.484406, 126.929628);
        b = new Node(22, 37.484407, 126.929630);
        c = new Node(33, 37.484408, 126.929637);
        d = new Node(44, 37.484409, 126.929636);
        e = new Node(55, 37.484410, 126.929633);
        f = new Node(66, 37.484411, 126.929625);
        g = new Node(77, 37.484412, 126.929620);
        z = new Node(88, 37.488830, 126.922804);

        // List에 추가
        // 출발지와 도착지를 기준으로 node추가한것
        nodeList.add(a);
        nodeList.add(b);
        nodeList.add(c);
        nodeList.add(d);
        nodeList.add(e);
        nodeList.add(f);
        nodeList.add(g);
        nodeList.add(z);

        // 12개의 link 생성성
        link1 = new Link(1, a, 4, b);
        link2 = new Link(2, a, 3, c);
        link3 = new Link(3, b, 2, c);
        link4 = new Link(4, b, 5, d);
        link5 = new Link(5, c, 3, d);
        link6 = new Link(6, c, 6, e);
        link7 = new Link(7, d, 1, e);
        link8 = new Link(8, d, 5, f);
        link9 = new Link(9, e, 5, g);
        link10 = new Link(10, f, 2, g);
        link11 = new Link(11, f, 7, z);
        link12 = new Link(12, g, 4, z);

        // 노드를 기준으로 link를 찾았다고 가정.
        linkList.add(link1);
        linkList.add(link2);
        linkList.add(link3);
        linkList.add(link4);
        linkList.add(link5);
        linkList.add(link6);
        linkList.add(link7);
        linkList.add(link8);
        linkList.add(link9);
        linkList.add(link10);
        linkList.add(link11);
        linkList.add(link12);

        System.out.println("link12.getWeight() = " + link12.getWeight());
        System.out.println("link12.getId() = " + link12.getId());
    }

    @Test
    @Transactional
    void 노드_사이즈_링크_사이즈_확인() {
        Assertions.assertEquals(8,nodeList.size());
        Assertions.assertEquals(12, linkList.size());
    }

    @Test
    @Transactional
    void 다익스트라_링크_입력_확인() {

        Dijkstra dijkstra = new Dijkstra(linkList);
        ArrayList<Long> passList = dijkstra.algorithm(a, g);
    }




}
