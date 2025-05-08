package com.example.demo_mcp_client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {

    private double latitude;
    private double longitude;
    private double elevation;

    @JsonProperty("hourly_units")
    private HourlyUnits hourlyUnits;

    private Hourly hourly;

    @Data
    public static class HourlyUnits {
        private String time;
        @JsonProperty("temperature_2m")
        private String temperature2m;
    }

    @Data
    public static class Hourly {
        private List<Long> time;
        @JsonProperty("temperature_2m")
        private List<Double> temperature2m;
    }

}