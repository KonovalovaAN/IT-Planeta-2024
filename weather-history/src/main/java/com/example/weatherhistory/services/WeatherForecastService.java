package com.example.weatherhistory.services;

import com.example.weatherhistory.entities.WeatherForecast;

public interface WeatherForecastService {
    WeatherForecast getWeatherForecastById(Long forecastId);
    WeatherForecast updateWeatherForecast(Long forecastId, WeatherForecast forecast);
    WeatherForecast addWeatherForecast(WeatherForecast forecast);
    void deleteWeatherForecast(Long forecastId);
}
