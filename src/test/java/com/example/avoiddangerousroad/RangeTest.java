package com.example.avoiddangerousroad;

import org.junit.jupiter.api.Test;


public class RangeTest {

    private class Location {

        double latitude;
        double longitude;

        Location(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    Location start = new Location(37.484406, 126.929628);
    Location end = new Location(37.487694, 126.928039);

    // 출발지와 도착지를 입력했을 때 값을 계산해주는 함수 작성
    private class RangeFormula {
        double leftLatitude;
        double rightLatitude;
        double leftLongitude;
        double rightLongitude;
        final double weight = 1.5;

        RangeFormula(Location start, Location end) {
            leftLatitude = calculateLeftLatitude(start.latitude, end.latitude);
            rightLatitude = calculateRightLatitude(start.latitude, end.latitude);
            leftLongitude = calculateLeftLongitude(start.longitude, end.longitude);
            rightLongitude = calculateRightLongitude(start.longitude, end.longitude);

        }

        double calculateLeftLatitude(double startLatitude, double endLatitude) {
            double midLatitude = (startLatitude + endLatitude) / 2;
            double rangeWeight = Math.abs(startLatitude - endLatitude) * weight;
            return midLatitude - rangeWeight;
        }

        double calculateRightLatitude(double startLatitude, double endLatitude) {
            double midLatitude = (startLatitude + endLatitude) / 2;
            double rangeWeight = Math.abs(startLatitude - endLatitude) * weight;
            return midLatitude + rangeWeight;
        }

        double calculateLeftLongitude(double startLongitude, double endLongitude) {
            double midLatitude = (startLongitude + endLongitude) / 2;
            double rangeWeight = Math.abs(startLongitude - endLongitude) * weight;
            return midLatitude - rangeWeight;
        }

        double calculateRightLongitude(double startLongitude, double endLongitude) {
            double midLatitude = (startLongitude + endLongitude) / 2;
            double rangeWeight = Math.abs(startLongitude - endLongitude) * weight;
            return midLatitude + rangeWeight;
        }
    }


    @Test
    void 범위_공식_테스트() {
        RangeFormula rangeFormula = new RangeFormula(start, end);
        System.out.println("rangeFormula.leftLatitude = " + rangeFormula.leftLatitude);
        System.out.println("rangeFormula.rightLatitude = " + rangeFormula.rightLatitude);
        System.out.println("rangeFormula.leftLongitude = " + rangeFormula.leftLongitude);
        System.out.println("rangeFormula.rightLongitude = " + rangeFormula.rightLongitude);

    }
}
