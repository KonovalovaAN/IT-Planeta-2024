package com.example.weatherhistory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String parentRegion;

    @ManyToOne
    @JoinColumn(name = "region_type_id", referencedColumnName = "id")
    private RegionType regionTypeId;
    private Double latitude;
    private Double longitude;
}
