package com.example.demo_mcp_client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private static final String API_URL = "https://api.open-meteo.com/v1/forecast";
    private static final String API_REQUEST_PARAMS = "?latitude=%s&longitude=%s&hourly=temperature_2m&forecast_days=1&format=json&timeformat=unixtime";

    private final RestTemplate restTemplate;

    @Tool(description = "Get current temperature for a specific latitude/longitude")
    public String temperature(double latitude, double longitude) {
        log.info("Get current temperature for latitude {} and longitude {}", latitude, longitude);
        // input validation
        if (latitude > 90 || latitude < -90 || longitude > 90 || longitude < -90) {
            return "Error: invalid latitude/longitude";
        }

        var weatherResponse = getWeather(latitude, longitude);
        return weatherResponse.getHourly().getTemperature2m().getFirst().toString();
    }

    public WeatherResponse getWeather(Double latitude, Double longitude) {
        String url = String.format(API_URL + API_REQUEST_PARAMS,
                                   latitude.toString().replace(",", "."),
                                   longitude.toString().replace(",", "."));
        log.info(url);
        return restTemplate.getForObject(url, WeatherResponse.class);

        // For a list of Response
        //                restTemplate.exchange(
        //                        url,
        //                        HttpMethod.GET,
        //                        null,
        //                        new ParameterizedTypeReference<List<WeatherResponse>>() {
        //                        }
        //                ).getBody().getFirst();
    }


}