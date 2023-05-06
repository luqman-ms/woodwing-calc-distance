package com.woodwing.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/distance")
public class DistanceService {
    
    @GET
    @Path("/sum/{unit1}/{distance1}/{unit2}/{distance2}")
    @Produces("text/plain")
    public String getDistanceSum(@PathParam("unit1") String unit1,
                                 @PathParam("distance1") double distance1,
                                 @PathParam("unit2") String unit2,
                                 @PathParam("distance2") double distance2) {
        double distanceInMeters1 = convertToMeters(distance1, unit1);
        double distanceInMeters2 = convertToMeters(distance2, unit2);
        double distanceSumInMeters = distanceInMeters1 + distanceInMeters2;
        String unit = "meters";
        double distanceSum = distanceSumInMeters;
        if (unit1.equals("feet") || unit2.equals("feet")) {
            distanceSum = convertFromMeters(distanceSumInMeters, "feet");
            unit = "feet";
        } else if (unit1.equals("miles") || unit2.equals("miles")) {
            distanceSum = convertFromMeters(distanceSumInMeters, "miles");
            unit = "miles";
        }
        return String.format("%.2f %s", distanceSum, unit);
    }
    
    private double convertToMeters(double distance, String unit) {
        switch (unit) {
            case "meters":
                return distance;
            case "feet":
                return distance * 0.3048;
            case "miles":
                return distance * 1609.34;
            default:
                throw new IllegalArgumentException("Invalid distance unit: " + unit);
        }
    }
    
    private double convertFromMeters(double distanceInMeters, String unit) {
        switch (unit) {
            case "meters":
                return distanceInMeters;
            case "feet":
                return distanceInMeters / 0.3048;
            case "miles":
                return distanceInMeters / 1609.34;
            default:
                throw new IllegalArgumentException("Invalid distance unit: " + unit);
        }
    }
}
