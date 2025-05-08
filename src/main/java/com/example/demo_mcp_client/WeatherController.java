package com.example.demo_mcp_client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/temperature")
    public String getTemperature(@RequestParam double latitude, @RequestParam double longitude) {
        return weatherService.temperature(latitude, longitude);
    }
    @GetMapping("/weather")
    public WeatherResponse getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        return weatherService.getWeather(latitude, longitude);
    }
}