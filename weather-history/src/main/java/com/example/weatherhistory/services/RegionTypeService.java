package com.example.weatherhistory.services;

import com.example.weatherhistory.entities.RegionType;

public interface RegionTypeService {
    RegionType getRegionTypeById(Long typeId);
    RegionType addRegionType(RegionType regionType);
    RegionType updateRegionType(Long typeId, String newType);
    void deleteRegionType(Long typeId);
}
