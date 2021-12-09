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

        form {
            width: 20%;
            height: 100%;
            margin: 0;
            float: left;
        }
        .form-group {
            padding-top: 50px;
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
</head>
<body>
    <div id="navbar">security map</div>
    <form action="/findRoad" method="get">
        <div class="form-group">
            <div class="start">
                출발지
                <input type="text" class="form-control" name="startLatitude" placeholder="출발지 위도를 입력하세요">
                <input type="text" class="form-control" name="startLongitude" placeholder="출발지 경도를 입력하세요">
            </div>
        </div>
            <div class="end">
                도착지
                <input type="text" class="form-control" name="endLatitude" placeholder="도착지 위도를 입력하세요">
                <input type="text" class="form-control" name="endLongitude" placeholder="도착지 경도를 입력하세요">
            </div>
        </div>
        <h2>피하고 싶은 길 설정</h2>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="preference[]" value="질문1"> 질문1
            </label>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="preference[]" value="질문2"> 질문2
            </label>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="preference[]" value="질문3"> 질문3
            </label>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="preference[]" value="질문4"> 질문4
            </label>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="preference[]" value="질문5"> 질문5
            </label>
        </div>
        <button type="submit" class="btn btn-default">길찾기</button>
    </form>


    <div id="map" style="width:80%;height:100%;float: right";></div>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=09f66f08e2c5ec1fa0fcd57b2d8d6582"></script>
    <script>
        var container = document.getElementById('map');
        var options = {
            center: new kakao.maps.LatLng(37.484406, 126.929628),
            level: 4
        };

        var map = new kakao.maps.Map(container, options);
    </script>
</body>
</html>