package com.example.weatherhistory.entities;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "RegionTypes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;
}
