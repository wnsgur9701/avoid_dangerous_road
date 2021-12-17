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

        #basic-addon1 {
            background: white;
        }
        #basic-addon2 {
            background: white;
        }

        .form-control {
            height: 50px;
        }



    </style>
</head>
<body>
    <div id="navbar">CARE ROAD</div>
    <div id="input-start" class="input-group">
        <input type="text" id="start-search" class="form-control" placeholder="출발지를 입력해 주세요"
               aria-describedby="basic-addon2" value="신림역">
        <span class="input-group-addon" id="basic-addon1">
            <img width="20" height="20" alt="testA" src="https://coronamap.site/images/search_icon.png">
        </span>
    </div>

    <div id="input-end" class="input-group">
        <input type="text" id="end-search" class="form-control" placeholder="도착지를 입력해 주세요"
               aria-describedby="basic-addon2">
        <span class="input-group-addon" id="basic-addon2">
            <img width="20" height="20" alt="testA" src="https://coronamap.site/images/search_icon.png">
        </span>
    </div>
    <div id="map" style="width:100%;height:100%;float: right";></div>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=09f66f08e2c5ec1fa0fcd57b2d8d6582"></script>
    <script>
        var container = document.getElementById('map');
        var options = {
            center: new kakao.maps.LatLng(37.484406, 126.929628),
            level: 4
        };

        var map = new kakao.maps.Map(container, options);

        var startMarkerPosition  = new kakao.maps.LatLng(37.48695301, 126.933479);
        var startMarker = new kakao.maps.Marker({
            position: startMarkerPosition
        });
        startMarker.setMap(map);
    </script>
</body>
</html>