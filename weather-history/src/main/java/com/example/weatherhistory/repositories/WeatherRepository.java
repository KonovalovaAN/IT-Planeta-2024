package com.example.weatherhistory.repositories;

import com.example.weatherhistory.entities.Weather;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findByRegionId(Long regionId);

    List<Weather> findByMeasurementDateTimeBetweenAndRegionIdAndWeatherCondition(
            LocalDateTime startDateTime, LocalDateTime endDateTime, Long regionId, String weatherCondition, Pageable pageable);

    void deleteByRegionIdAndId(Long regionId, Long id);
}
