package com.example.weatherhistory.entities;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "WeatherData")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "region_id")
    private long regionId;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "humidity")
    private double humidity;

    @Column(name = "wind_speed")
    private double windSpeed;

    @Column(name = "pressure")
    private double pressure;

    @Column(name = "timestamp")
    private long timestamp;
}
