package com.example.weatherhistory.services;

import com.example.weatherhistory.entities.Region;

public interface RegionService {
    Region getRegionById(Long regionId);
    Region addRegion(Region region);
    Region updateRegion(Long regionId, Region region);
    void deleteRegion(Long regionId);
}
