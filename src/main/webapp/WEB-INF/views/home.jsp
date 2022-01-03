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

        #input-search {
            position: absolute;
            width: 550px;
            padding: 15px;
            text-align: center;

        }

        #basic-addon1 {
            background: white;
        }
        #basic-addon2 {
            background: white;
        }

        #basic-addon3 {
            background: white;
        }
        #basic-addon4 {
            background: white;
        }

        .form-control {
            height: 50px;
        }

        .searchBackground {
            width: 550px;
            height: 100%;
            background: white;
            top: 50px;
            z-index: 218372189739217;
            position: absolute;
            visibility: hidden;
        }

        #searchListBack {
            -webkit-box-flex: 1;
            top: 90px;
            flex-grow: 1;
            height: 100%;
            width: 100%;
            border-top: 1px solid rgb(232, 232, 232);
            position: absolute;
            visibility: hidden;
        }

        .sc-dPaNSN + .sc-dPaNSN {
            border-top: 1px solid rgb(232, 232, 232);
        }

        .KQWTL {
            width: 100%;
            padding: 12px 15px;
            cursor: pointer;
        }

        .bhjKRs {
            font-size: 17px;
            font-weight: 500;
        }

        .iGliNw {
            font-size: 13px;
            color: rgb(134,134,147);
        }



    </style>
</head>
<body>
    <div id="navbar">CARE ROAD</div>
    <div id="input-start" class="input-group">
        <input type="text" id="start-search" class="form-control" placeholder="출발지를 입력해 주세요"
               aria-describedby="basic-addon2" value="서울특별시 관악구 서원길 5">
        <span class="input-group-addon" id="basic-addon1">
            <img width="20" height="20" alt="testA" src="https://coronamap.site/images/search_icon.png">
        </span>
    </div>

    <div id="input-end" class="input-group">
        <input type="text" id="end-search" class="form-control" placeholder="도착지를 입력해 주세요"
               aria-describedby="basic-addon2">
        <span class="input-group-addon" id="basic-addon2">
            <img width="20" height="20" alt="testA" src="https://coronamap.site/images/search_icon.png" onclick="onClickEndSearch()">
        </span>
    </div>

    <div class="searchBackground" id="search-back">
        <div id="input-search" class="input-group">
            <span class="input-group-addon" id="basic-addon3">
                <img width="20" height="20" alt="testA" src="https://coronamap.site/images/backArrow.png" style="cursor: pointer" onclick="backToHome()">
            </span>
            <input type="text" id="search" class="form-control" aria-describedby="basic-addon2">
            <span class="input-group-addon" id="basic-addon4">
                <img width="20" height="20" alt="testA" src="https://coronamap.site/images/search_icon.png" onclick="onClickSearchList()">
            </span>
        </div>
        <div class="sc-jNnnWF fCQiqA" id = "searchListBack">
            <div class="sc-dPaNSN KQWTL">
                <div class="sc-bBjRzc bhjKRs">
                    코끼리마트 장안점
                </div>
                <div class="sc-oOigif iGliNw">
                    서울 동대문구 사가정로23길 32
                </div>
            </div>
            <div class="sc-dPaNSN KQWTL">
                <div class="sc-bBjRzc bhjKRs">
                    코끼리할인마트
                </div>
                <div class="sc-oOigif iGliNw">
                    서울 동대문구 안암로 80-1
                </div>
            </div>
            <div class="sc-dPaNSN KQWTL">
                <div class="sc-bBjRzc bhjKRs">
                    코끼리마트 광장점
                </div>
                <div class="sc-oOigif iGliNw">
                    서울 광진구 아차산로 537-17
                </div>
            </div>
            <div class="sc-dPaNSN KQWTL" onclick="location.href='http://localhost:8080/findRoad?startLatitude=37.48527205&startLongitude=126.9295703&endLatitude=37.48732899&endLongitude=126.9247235'" style="cursor:pointer;">
                <div class="sc-bBjRzc bhjKRs">
                    코끼리마트
                </div>
                <div class="sc-oOigif iGliNw">
                    서울 관악구 관천로16길 41
                </div>
            </div>
        </div>


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

        function onClickEndSearch() {
            document.getElementById("search-back").style.visibility = "visible";
        }

        function backToHome() {
            document.getElementById("search-back").style.visibility = "hidden";
            document.getElementById("searchListBack").style.visibility = "hidden";
        }

        function onClickSearchList() {
            document.getElementById("searchListBack").style.visibility = "visible";
        }
    </script>
</body>
</html>
