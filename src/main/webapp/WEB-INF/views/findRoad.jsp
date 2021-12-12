<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

        <title>지도시작하기</title>
        <style>

            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }

            body {
                overflow: hidden;

            }

        </style>
    </head>
    <body>

        <div id="map" style="width:100%;height:100%;float: right"></div>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=09f66f08e2c5ec1fa0fcd57b2d8d6582"></script>
        <script>
            var container = document.getElementById('map');
            var options = {
                center: new kakao.maps.LatLng(37.484406, 126.929628),
                level: 4
            };

            var map = new kakao.maps.Map(container, options);

            var start = new kakao.maps.LatLng(37.484406, 126.929628);
            var mid = new kakao.maps.LatLng(37.484767, 126.928876);
            var end = new kakao.maps.LatLng(37.487694, 126.928039);

            // 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
            var linePath = [
                start,
                mid,
                end
            ];

            // 지도에 표시할 선을 생성합니다
            var polyline = new kakao.maps.Polyline({
                path: linePath, // 선을 구성하는 좌표배열 입니다
                strokeWeight: 5, // 선의 두께 입니다
                strokeColor: '#FFAE00', // 선의 색깔입니다
                strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                strokeStyle: 'solid' // 선의 스타일입니다
            });

            // 지도에 선을 표시합니다
            polyline.setMap(map);

            // 마커 생성
            var marker1 = new kakao.maps.Marker({
                position: start
            });
            var marker2 = new kakao.maps.Marker({
                position: mid
            });
            var marker3 = new kakao.maps.Marker({
                position: end
            });

            marker1.setMap(map);
            marker2.setMap(map);
            marker3.setMap(map);
        </script>
    </body>
</html>