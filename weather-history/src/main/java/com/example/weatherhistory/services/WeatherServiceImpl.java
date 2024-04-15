/*
package com.example.weatherhistory.services;

import com.example.weatherhistory.entities.Weather;
import com.example.weatherhistory.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public Weather getWeatherByRegionId(Long regionId) {
        return weatherRepository.findByRegionId(regionId);
    }

    @Override
    public List<Weather> searchWeather(LocalDateTime startDateTime, LocalDateTime endDateTime, Long regionId, String weatherCondition, int from, int size) {
        return weatherRepository.searchWeather(startDateTime, endDateTime, regionId, weatherCondition, from, size);
    }

    @Override
    public Weather addWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    @Override
    public Weather updateWeather(Long regionId, Weather weather) {
        Weather existingWeather = weatherRepository.findByRegionId(regionId);
        if (existingWeather != null) {
            weather.setId(existingWeather.getId());
            return weatherRepository.save(weather);
        } else {
            return null; // Weather not found
        }
    }

    @Override
    public void deleteWeather(Long regionId) {
        Weather weather = weatherRepository.findByRegionId(regionId);
        if (weather != null) {
            weatherRepository.delete(weather);
        }
    }

    @Override
    public Weather addWeatherToRegion(Long regionId, Long weatherId) {
        // Implement logic to add weather to region
    }

    @Override
    public void deleteWeatherFromRegion(Long regionId, Long weatherId) {
        // Implement logic to delete weather from region
    }
}
*/
