<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <script	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <title>지도시작하기</title>
        <style>

            html, body {
                height: 80%;
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
        </style>

        <script type="text/javascript">
            var subNodeLists = [];

            var loop = 1;

            var startLatitude = "${startLatitude}";
            var startLongitude = "${startLongitude}";
            var endLatitude = "${endLatitude}";
            var endLongitude = "${endLongitude}";
            var startNodeLatitude = "${startNode.latitude}";
            var startNodeLongitude = "${startNode.longitude}";
            var endNodeLatitude = "${endNode.latitude}";
            var endNodeLongitude = "${endNode.longitude}";


        </script>
    </head>
    <body>
        <div id="navbar">security map</div>
        <div id="map" style="width:100%;height:100%;float: right"></div>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=09f66f08e2c5ec1fa0fcd57b2d8d6582"></script>
        <script>
            var container = document.getElementById('map');
            var options = {
                // start 790번 노드
                center: new kakao.maps.LatLng(startLatitude, startLongitude),
                level: 4
                // end
                // 827
                // latitude : 37.48684056
                // longitude : 126.9266488
            };

            var map = new kakao.maps.Map(container, options);

            // var linePath2 = [];
            //
            // var i;
            // for (i = 1; i < subNodeList.length; i++) {
            //     //linePath2.push(new kakao.map.LatLng(subNodeList[i].latitude, subNodeList[i].longitude));
            //     console.log(subNodeList[i].latitude);
            //     console.log(subNodeList[i].longitude);
            //     linePath2.push(new kakao.maps.LatLng(subNodeList[i].latitude, subNodeList[i].longitude))
            // }

            // var i;
            // for (i = 1; i < subNodeList.length+1; i++) {
            //     linePath2.push(new kako.maps.LatLng(subNodeList[i].latitude, subNodeList[i].longitude));
            // }

            // var start = new kakao.maps.LatLng(37.484406, 126.929628);
            // var mid = new kakao.maps.LatLng(37.484767, 126.928876);
            // var end = new kakao.maps.LatLng(37.487694, 126.928039);

            // 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
            // var linePath = [
            //     start,
            //     mid,
            //     end
            // ];

            // #############################################

            <c:forEach items="${weightSubNodeLists}" var="weightSubNodeList">
            var linePath_1 = [];
            <c:forEach items="${weightSubNodeList}" var="weightSubNode">
            linePath_1.push(new kakao.maps.LatLng("${weightSubNode.latitude}", "${weightSubNode.longitude}"));
            </c:forEach>

            // 지도에 표시할 선을 생성합니다.
            var polyline_1 = new kakao.maps.Polyline({
                path: linePath_1, // 선을 구성하는 좌표배열 입니다
                strokeWeight: 5, // 선의 두께 입니다
                strokeColor: '#FFAE00', // 선의 색깔입니다
                strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
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

            // ######################################################## distance

            <c:forEach items="${distanceSubNodeLists}" var="distanceSubNodeList">
            var linePath_2 = [];
            <c:forEach items="${distanceSubNodeList}" var="distanceSubNode">
            linePath_2.push(new kakao.maps.LatLng("${distanceSubNode.latitude}", "${distanceSubNode.longitude}"));
            </c:forEach>

            // 지도에 표시할 선을 생성합니다.
            var polyline_2 = new kakao.maps.Polyline({
                path: linePath_2, // 선을 구성하는 좌표배열 입니다
                strokeWeight: 5, // 선의 두께 입니다
                strokeColor: '#5EFF00', // 선의 색깔입니다
                strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
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

        </script>
    </body>
</html>