package com.woodwing.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distance")
public class DistanceService {

    @GetMapping("/sum/{unit1}/{distance1}/{unit2}/{distance2}/{returnUnit}")
    public String getDistanceSum(@PathVariable String unit1,
            @PathVariable double distance1,
            @PathVariable String unit2,
            @PathVariable double distance2,
            @PathVariable String returnUnit) {

        try {
            unit1 = unit1.toLowerCase();
            unit2 = unit2.toLowerCase();
            returnUnit = returnUnit.toLowerCase();

            double distanceInMeters1 = convertToMeters(distance1, unit1);
            double distanceInMeters2 = convertToMeters(distance2, unit2);
            double distanceSumInMeters = distanceInMeters1 + distanceInMeters2;
            double distanceSum = distanceSumInMeters;
            
            distanceSum = convertFromMeters(distanceSumInMeters, returnUnit);

            return String.format("%.2f %s", distanceSum, returnUnit);

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private double convertToMeters(double distance, String unit) {
        switch (unit) {
            case "meters":
                return distance;
            case "yards":
                return distance * 0.9144;
            default:
                throw new IllegalArgumentException(
                        "Invalid distance unit: " + unit + "<br>Only 'Meters' and 'Yards' is allowed");
        }
    }

    private double convertFromMeters(double distanceInMeters, String unit) {
        switch (unit) {
            case "meters":
                return distanceInMeters;
            case "yards":
                return distanceInMeters / 0.9144;
            default:
                throw new IllegalArgumentException(
                        "Invalid return unit: " + unit + "<br>Only 'Meters' and 'Yards' is allowed");
        }
    }
}
