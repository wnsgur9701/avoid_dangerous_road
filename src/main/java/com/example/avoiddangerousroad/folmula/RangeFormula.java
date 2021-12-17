package com.example.avoiddangerousroad.folmula;

public class RangeFormula {

    public double leftLatitude;
    public double rightLatitude;
    public double leftLongitude;
    public double rightLongitude;
    public static double weight = 1.5;

    public RangeFormula(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        this.leftLatitude = calculateLeftLatitude(startLatitude, endLatitude);
        this.rightLatitude = calculateRightLatitude(startLatitude, endLatitude);
        this.leftLongitude = calculateLeftLongitude(startLongitude, endLongitude);
        this.rightLongitude = calculateRightLongitude(startLongitude, endLongitude);

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
