package com.example.avoiddangerousroad.entity;

import java.util.*;

public class Dijkstra {

    private int n;
    private final double[][] weightList; //2차원 배열 weight에 각 꼭지점의 가중치를 저장
    private final Long[] saveRoute;
    private final Set<Long> vertexSet = new HashSet<>();
    private final List<Long> vertexList;

    public Dijkstra(List<Link> linkList) {
        super();
        for (Link link : linkList) {
            Node node1 = link.getNode1();
            Node node2 = link.getNode2();
            vertexSet.add(node1.getId());
            vertexSet.add(node2.getId());
        }

        // set을 list로 변환
        vertexList = new ArrayList<>(vertexSet);

        // n 주입
        this.n = vertexList.size();
        weightList = new double[n][n];
        saveRoute = new Long[n];

        for (Link link : linkList) {
            int x1 = longToInt(link.getNode1().getId());
            int x2 = longToInt(link.getNode2().getId());

            weightList[x1][x2] = link.getWeight();
            weightList[x2][x1] = link.getWeight();

        }


    }

    public int longToInt(Long l) {

        // id값인 Long을 int형으로 바꿔준다.
        int x = 0;
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).equals(l)) x = i;
        }
        return x;

    }

    public void algorithm(Node start, Node end) {
        boolean[] visited = new boolean[n]; //각 꼭지점의 방문 여부
        double[] distance = new double[n]; //시작 꼭지점에서부터 각 꼭지점까지의 거리

        //시작 꼭지점 a에서부터 각 꼭지점까지의 모든 거리 초기화
        for(int i=0; i<n; i++) {
            //이산수학 교재 251쪽에서는 ∞로 초기화했지만
            //여기에서는 int형의 가장 큰 값 2147483647로 초기화한다.
            distance[i] = Integer.MAX_VALUE;
        }

        int startIndex = longToInt(start.getId()); //문자열로 입력된 시작 꼭지점을 해당되는 숫자 인덱스 startIndex로 바꾸고
        int endIndex = longToInt(end.getId()); //문자열로 입력된 시작 꼭지점을 해당되는 숫자

        distance[startIndex] = 0;
        visited[startIndex] = true; //방문 꼭지점이므로 true값 저장
        saveRoute[startIndex] = vertexList.get(startIndex); //★시작 꼭지점의 경로는 자기 자신을 저장

        //시작 꼭지점 x에서부터 꼭지점 i까지의 거리를 갱신한다.
        for(int i=0; i<n; i++) {
            //방문하지 않았고 x에서 i까지의 가중치가 존재한다면, 거리 i에 x에서 i까지의 가중치 저장
            //즉 x에서 인접한 꼭지점까지의 거리를 갱신함.
            //(x와 인접하지 않은 꼭지점에는 Integer.MAX_VALUE가 계속 저장되어 있을 것이다.)
            // 방문하지 않고 인접행렬일 때
            // 거리를 갱신
            if (!visited[i] && weightList[startIndex][i] != 0) {
                distance[i] = weightList[startIndex][i];
                saveRoute[i] = vertexList.get(startIndex); //★시작 꼭지점과 인접한 꼭지점의 경로에 시작 꼭지점을 저장
            }
        }

        for(int i=0; i<n-1; i++) {

            double minDistance = Integer.MAX_VALUE; //최단거리 minDistance에 일단 가장 큰 정수로 저장하고,
            int minVertex = -1; //그 거리값이 있는 인덱스 minVertex에 -1을 저장해둔다.
            // 방문하지 않고 거리가 가장 짧은 거리인 nodeIndex를 구한다.
            for(int j=0; j<n; j++) {
                //방문하지 않았고 거리를 갱신한 꼭지점 중에서 가장 가까운 거리와 가장 가까운 꼭지점을 구한다.
                if(!visited[j] && distance[j]!=Integer.MAX_VALUE) {
                    if(distance[j]<minDistance) {
                        minDistance = distance[j];
                        minVertex = j;
                    }
                }
            }

            visited[minVertex] = true; //위의 반복문을 통해 도출된 가장 가까운 꼭지점에 방문 표시


            for(int j=0; j<n; j++) {
                //방문하지 않았고 minVertex와의 가중치가 존재하는(minVertex에서 연결된) 꼭지점이라면
                if(!visited[j] && weightList[minVertex][j]!=0) {
                    //지금 그 꼭지점이 가지고 있는 거리값이 minVertex와 가중치를 더한 값보다 크다면 최단거리 갱신
                    if(distance[j]>distance[minVertex]+weightList[minVertex][j]) {
                        distance[j] = distance[minVertex]+weightList[minVertex][j];
                        saveRoute[j] = vertexList.get(minVertex);; //최단거리가 갱신된 꼭지점의 경로에 minVertex에 해당하는 꼭지점 저장
                    }
                }
            }

            if (minVertex == endIndex) {
                break;
            }

        }

        //시작 꼭지점부터 특정 꼭지점까지의 거리 출력
        System.out.println("saveRoute = " + Arrays.toString(saveRoute));
        System.out.println("시작 꼭지점 " + start + "부터 꼭지점 " + vertexList.get(endIndex) + "까지의 거리 :" + distance[endIndex]);

        System.out.println("==================================");

        int tempIndex = endIndex;
        ArrayList<Long> arrayList= new ArrayList<Long>();
        while (tempIndex != startIndex) {

            arrayList.add(vertexList.get(tempIndex));
            tempIndex = longToInt(saveRoute[tempIndex]);
        }
        arrayList.add(vertexList.get(startIndex));
        System.out.println("arrayList.size() = " + arrayList.size());
        System.out.println("arrayList.get(1) = " + arrayList.get(1));

        for (int i = arrayList.size()-1; i >= 0; i--) {
            System.out.println(arrayList.size() - i + "번째 경로는 "  + arrayList.get(i) + "입니다.");
        }



        //시작 꼭지점부터 특정 꼭지점까지의 경로 출력
//        for(int i=0; i<n; i++) {
//            String route = "";
//            System.out.println("시작 꼭지점 "+start.getId()+"부터 꼭지점 "+vertexList.get(i)+"까지의 경로");
//            int index = i;
//            while(true) {
//                if(saveRoute[index] == vertexList.get(index)) break; //시작 꼭지점일 경우 break
//                route += " " + saveRoute[index];
//                index = longToInt(saveRoute[index]); //결정적인 역할을 한 꼭지점을 int형으로 바꿔서 index에 저장
//            }
//            StringBuilder sb = new StringBuilder(route);
//            System.out.println(sb.reverse() + String.valueOf(vertexList.get(i)));
//        }



    }


}
