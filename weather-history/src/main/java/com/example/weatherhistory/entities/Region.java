package com.example.weatherhistory.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Regions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "region_type")
    private long regionType;

    @Column(name = "account_id")
    private long accountId;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_region")
    private String parentRegion;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;
}
