package com.example.weatherhistory.services;

import com.example.weatherhistory.entities.Region;
import com.example.weatherhistory.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Region getRegionById(Long regionId) {
        return regionRepository.findById(regionId).orElse(null);
    }

    @Override
    public Region addRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Region updateRegion(Long regionId, Region region) {
        Region existingRegion = regionRepository.findById(regionId).orElse(null);
        if (existingRegion != null) {
            existingRegion.setName(region.getName());
            existingRegion.setParentRegion(region.getParentRegion());
            existingRegion.setRegionType(region.getRegionType());
            existingRegion.setLatitude(region.getLatitude());
            existingRegion.setLongitude(region.getLongitude());
            return regionRepository.save(existingRegion);
        } else {
            return null;
        }
    }

    @Override
    public void deleteRegion(Long regionId) {
        regionRepository.deleteById(regionId);
    }
}
