<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

        <!-- 합쳐지고 최소화된 최신 CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

        <!-- 부가적인 테마 -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

        <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

        <script	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <title>CARE ROAD</title>
        <style>

            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }

            body {
                overflow: hidden;
            }

            #navbar {
                height: 50px;
                width: 100%;
                background: #262626;
                color: white;
                font-weight: bolder;
                font-size: 25px;
                display: flex;
                justify-content: center;
                align-items: center;

            }

            #input-start {
                position: absolute;
                top: 70px;
                left: 30px;
                width: 500px;
                padding: 15px;
                z-index: 10000;
                text-align: center;
            }

            #input-end {
                position: absolute;
                top: 125px;
                left: 30px;
                width: 500px;
                padding: 15px;
                z-index: 10000;
                text-align: center;
            }

            #resultList {
                position: absolute;
                z-index: 999999999999;
                top: 265px;
                left: 45px;
                width: 470px;
                padding: 15px;
                background: white;
                height: 430px;
                border-top: 1px solid rgb(232, 232, 232);
                border-left: 1px solid rgb(232, 232, 232);
                border-right: 1px solid rgb(232, 232, 232);
                border-bottom: 1px solid rgb(232, 232, 232);
                border-radius: 5px;

            }

            #basic-addon1 {
                background: white;
            }
            #basic-addon2 {
                background: white;
            }

            .form-control {
                height: 50px;
            }


            .KQWTL {
                width: 100%;
                height: 200px;
                padding: 12px 15px;
                cursor: pointer;
                border-top: 1px solid rgb(232, 232, 232);
            }

            #recommend {
                background: white;
            }

            #shortest {
                background: white;
            }

            .bhjKRs {
                font-size: 20px;
                font-weight: bold;
            }

            .iGliNw {
                padding-top: 5px;
                padding-bottom: 5px;
            }

            .specific-information-title {
                padding-top: 10px;
            }

            #rank {
                width: 470px;
                float: left;
                margin-left: 45px ;
                margin-top: 145px;
            }

            #image {
                position: absolute;
                z-index: 100000;
            }

            .specific-information-title {
                text-decoration: underline;
                color: #00BFFF;
            }

            .specific-information-list {
                padding-top: 10px;
            }






        /*    1등급: #FFFFB2  2등급: #FEE88B   3등급: #FED165  4등급: #FDB751
              5등급: #FE9C43  6등급: #FA7A17   7등급: #F45629  8등급: #EA3420
              9등급: #D31A23  10등급: #BD0026   */
        </style>
        <script type="text/javascript">
            var subNodeLists = [];

            var colorList = ["###","#FFFFB2", "#FEE88B", "#FED165", "#FDB751", "#FE9C43",
                "#FA7A17", "#F45629", "#F45629", "#EA3420", "#D31A23", "#BD0026"]

            var colorLists1 = [];


            var loop = 1;

            var startLatitude = "${startLatitude}";
            var startLongitude = "${startLongitude}";
            var endLatitude = "${endLatitude}";
            var endLongitude = "${endLongitude}";
            var startNodeLatitude = "${startNode.latitude}";
            var startNodeLongitude = "${startNode.longitude}";
            var endNodeLatitude = "${endNode.latitude}";
            var endNodeLongitude = "${endNode.longitude}";
            var weightLinkSize = "${weightLinkSize}";
            var distanceLinkSize = "${distanceLinkSize}";
            var d = "${d}";


        </script>
    </head>
    <body>
        <div id="navbar">CARE ROAD</div>
        <div id = "image">
            <img src="../../rankListFinal.PNG" id = "rank" alt="등급 표 입니다">
        </div>
        <div id="input-start" class="input-group">
            <input type="text" id="start-search" class="form-control" aria-describedby="basic-addon2" value="서울특별시 관악구 서원길 5">
            <span class="input-group-addon" id="basic-addon1">
            <img width="20" height="20" alt="testA" src="https://coronamap.site/images/search_icon.png">
        </span>
        </div>

        <div id="input-end" class="input-group">
            <input type="text" id="end-search" class="form-control" aria-describedby="basic-addon2" value="코끼리 마트">
            <span class="input-group-addon" id="basic-addon2">
            <img width="20" height="20" alt="testA" src="https://coronamap.site/images/search_icon.png">
        </span>
        </div>

        <div id="resultList">
            <div class="sc-dPaNSN KQWTL" id="recommend" onclick="onClickRecommend()">
                <div class="sc-bBjRzc bhjKRs">
                    추천 안심 경로
                </div>
                <div class="sc-oOigif iGliNw">
                    14분 | 820m
                </div>
                <div class="specific-information-title">
                    상세정보
                </div>
                <div class="specific-information-list">
                    <div class="cctv">자연감시 시설(cctv, 편의점): 20개</div>
                    <div class="light"> 가로등: 13개</div>
                    <div class="secure-store">치안시설(안심벨, 경찰서): 0개</div>
                    <div class="harmful-store">유해 업소(모텔, 성인오락): 1개</div>
                </div>
            </div>
            <div class="sc-dPaNSN KQWTL" id="shortest" onclick="onClickShortest()">
                <div class="sc-bBjRzc bhjKRs">
                    최단 경로
                </div>
                <div class="sc-oOigif iGliNw">
                    10분 | 674m
                </div>
                <div class="specific-information-title">
                    상세정보
                </div>
                <div class="specific-information-list">
                    <div class="cctv">자연감시 시설(cctv, 편의점): 13개</div>
                    <div class="light"> 가로등: 2개</div>
                    <div class="secure-store">치안시설(안심벨, 경찰서): 0개</div>
                    <div class="harmful-store">유해 업소(모텔, 성인오락): 0개</div>
                </div>

            </div>
        </div>
        <div id="map" style="width:100%;height:100%;float: right"></div>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=09f66f08e2c5ec1fa0fcd57b2d8d6582"></script>
        <script>
            var container = document.getElementById('map');
            var options = {
                // start 790번 노드
            // (startLatitude, startLongitude)
                center: new kakao.maps.LatLng(37.487002, 126.925546),
                level: 3
                // end
                // 827
                // latitude : 37.48684056
                // longitude : 126.9266488
            };

            var map = new kakao.maps.Map(container, options);

            var startPath = [
                new kakao.maps.LatLng(startLatitude, startLongitude),
                new kakao.maps.LatLng(startNodeLatitude, startNodeLongitude)
            ];

            var endPath = [
                new kakao.maps.LatLng(endLatitude, endLongitude),
                new kakao.maps.LatLng(endNodeLatitude, endNodeLongitude)
            ];

            var startPolyline = new kakao.maps.Polyline({
                path: startPath,
                strokeWeight: 5,
                strokeColor: '#FF0800',
                strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                strokeStyle: 'solid' // 선의 스타일입니다
            });

            var endPolyline = new kakao.maps.Polyline({
                path: endPath,
                strokeWeight: 5,
                strokeColor: '#FF0800',
                strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                strokeStyle: 'solid' // 선의 스타일입니다
            });

            startPolyline.setMap(map);
            endPolyline.setMap(map);




            // 마커 생성
            // var marker1 = new kakao.maps.Marker({
            //     position: start
            // });
            // var marker2 = new kakao.maps.Marker({
            //     position: mid
            // });
            // var marker3 = new kakao.maps.Marker({
            //     position: end
            // });
            //
            // marker1.setMap(map);
            // marker2.setMap(map);
            // marker3.setMap(map);

            var startImageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_b.png', // 마커이미지의 주소입니다
                startImageSize = new kakao.maps.Size(50, 45), // 마커이미지의 크기입니다
                startImageOption = {offset: new kakao.maps.Point(15, 32)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

            // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
            var startMarkerImage = new kakao.maps.MarkerImage(startImageSrc, startImageSize, startImageOption),
                startMarkerPosition = new kakao.maps.LatLng(startLatitude, startLongitude); // 마커가 표시될 위치입니다

            // 마커를 생성합니다
            var startMarker = new kakao.maps.Marker({
                position: startMarkerPosition,
                image: startMarkerImage // 마커이미지 설정
            });

            startMarker.setMap(map);

            var endImageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_b.png', // 마커이미지의 주소입니다
                endImageSize = new kakao.maps.Size(50, 45), // 마커이미지의 크기입니다
                endImageOption = {offset: new kakao.maps.Point(15, 43)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

            // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
            var endMarkerImage = new kakao.maps.MarkerImage(endImageSrc, endImageSize, endImageOption),
                endMarkerPosition = new kakao.maps.LatLng(endLatitude, endLongitude); // 마커가 표시될 위치입니다

            // 마커를 생성합니다
            var endMarker = new kakao.maps.Marker({
                position: endMarkerPosition,
                image: endMarkerImage // 마커이미지 설정
            });

            endMarker.setMap(map);

            function onClickRecommend() {
                document.getElementById("recommend").style.background = "#E1F6FA";
                document.getElementById("shortest").style.background = "white";
                <c:forEach items="${distanceSubNodeLists}" var="distanceSubNodeList">
                var linePath_2 = [];
                <c:forEach items="${distanceSubNodeList}" var="distanceSubNode">
                linePath_2.push(new kakao.maps.LatLng("${distanceSubNode.latitude}", "${distanceSubNode.longitude}"));
                </c:forEach>

                // 지도에 표시할 선을 생성합니다.
                var polyline_2 = new kakao.maps.Polyline({
                    path: linePath_2, // 선을 구성하는 좌표배열 입니다
                    strokeWeight: 6, // 선의 두께 입니다
                    strokeColor: '#808080', // 선의 색깔입니다
                    strokeOpacity: 0.9, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                    strokeStyle: 'solid' // 선의 스타일입니다
                });

                <%--subNodeList[loop] = {--%>
                <%--    latitude: "${item.latitude}",--%>
                <%--    longitude: "${item.longitude}"--%>
                <%--}--%>
                <%--loop++;--%>
                // 지도에 선을 표시합니다
                polyline_2.setMap(map);
                </c:forEach>


                <c:forEach items="${weightSubNodeLists}" var="weightSubNodeList">
                var linePath_1 = [];
                <c:forEach items="${weightSubNodeList}" var="weightSubNode">
                linePath_1.push(new kakao.maps.LatLng("${weightSubNode.latitude}", "${weightSubNode.longitude}"));
                </c:forEach>

                // 지도에 표시할 선을 생성합니다.
                var polyline_1 = new kakao.maps.Polyline({
                    path: linePath_1, // 선을 구성하는 좌표배열 입니다
                    strokeWeight: 6, // 선의 두께 입니다
                    strokeColor: colorList[rand(1,5)], // 선의 색깔입니다
                    strokeOpacity: 0.9, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                    strokeStyle: 'solid' // 선의 스타일입니다
                });

                <%--subNodeList[loop] = {--%>
                <%--    latitude: "${item.latitude}",--%>
                <%--    longitude: "${item.longitude}"--%>
                <%--}--%>
                <%--loop++;--%>
                // 지도에 선을 표시합니다
                polyline_1.setMap(map);
                </c:forEach>
            }

            function onClickShortest() {
                document.getElementById("shortest").style.background = "#E1F6FA";
                document.getElementById("recommend").style.background = "white";
                <c:forEach items="${weightSubNodeLists}" var="weightSubNodeList">
                var linePath_1 = [];
                <c:forEach items="${weightSubNodeList}" var="weightSubNode">
                linePath_1.push(new kakao.maps.LatLng("${weightSubNode.latitude}", "${weightSubNode.longitude}"));
                </c:forEach>

                // 지도에 표시할 선을 생성합니다.
                var polyline_1 = new kakao.maps.Polyline({
                    path: linePath_1, // 선을 구성하는 좌표배열 입니다
                    strokeWeight: 6, // 선의 두께 입니다
                    strokeColor: '#808080', // 선의 색깔입니다
                    strokeOpacity: 0.9, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                    strokeStyle: 'solid' // 선의 스타일입니다
                });
                <%--subNodeList[loop] = {--%>
                <%--    latitude: "${item.latitude}",--%>
                <%--    longitude: "${item.longitude}"--%>
                <%--}--%>
                <%--loop++;--%>
                // 지도에 선을 표시합니다
                polyline_1.setMap(map);
                </c:forEach>

                <c:forEach items="${distanceSubNodeLists}" var="distanceSubNodeList">
                var linePath_2 = [];
                <c:forEach items="${distanceSubNodeList}" var="distanceSubNode">
                linePath_2.push(new kakao.maps.LatLng("${distanceSubNode.latitude}", "${distanceSubNode.longitude}"));
                </c:forEach>

                // 지도에 표시할 선을 생성합니다.
                var polyline_2 = new kakao.maps.Polyline({
                    path: linePath_2, // 선을 구성하는 좌표배열 입니다
                    strokeWeight: 6, // 선의 두께 입니다
                    strokeColor: colorList[rand(3,9)], // 선의 색깔입니다
                    strokeOpacity: 0.9, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                    strokeStyle: 'solid' // 선의 스타일입니다
                });

                <%--subNodeList[loop] = {--%>
                <%--    latitude: "${item.latitude}",--%>
                <%--    longitude: "${item.longitude}"--%>
                <%--}--%>
                <%--loop++;--%>
                // 지도에 선을 표시합니다
                polyline_2.setMap(map);
                </c:forEach>
            }

            function rand(min, max) {
                return Math.floor(Math.random() * (max - min + 1)) + min;
            }
            console.log(weightLinkSize);
            console.log(distanceLinkSize);
            console.log(d);
        </script>
    </body>
</html>