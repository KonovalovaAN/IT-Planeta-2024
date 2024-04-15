package com.example.weatherhistory.entities;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Weather")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "regionId")
    private Long regionId;

    @Column(name = "regionName")
    private String regionName;

    @Column(name = "temperature")
    private Float temperature;

    @Column(name = "humidity")
    private Float humidity;

    @Column(name = "windSpeed")
    private Float windSpeed;

    @Column(name = "weatherCondition")
    private String weatherCondition;

    @Column(name = "precipitationAmount")
    private Float precipitationAmount;

    @Column(name = "measurementDateTime")
    private LocalDateTime measurementDateTime;

    // weatherForecast field can be added if necessary
}
