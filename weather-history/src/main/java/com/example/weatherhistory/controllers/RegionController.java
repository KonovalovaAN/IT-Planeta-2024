package com.example.weatherhistory.controllers;

import com.example.weatherhistory.entities.Region;
import com.example.weatherhistory.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping("/{regionId}")
    public ResponseEntity<Region> getRegionById(@PathVariable("regionId") Long regionId) {
        Region region = regionService.getRegionById(regionId);
        if (region != null) {
            return new ResponseEntity<>(region, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Region> addRegion(@RequestBody Region region) {
        Region addedRegion = regionService.addRegion(region);
        return new ResponseEntity<>(addedRegion, HttpStatus.CREATED);
    }

    @PutMapping("/{regionId}")
    public ResponseEntity<Region> updateRegion(@PathVariable("regionId") Long regionId, @RequestBody Region region) {
        Region updatedRegion = regionService.updateRegion(regionId, region);
        if (updatedRegion != null) {
            return new ResponseEntity<>(updatedRegion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{regionId}")
    public ResponseEntity<Void> deleteRegion(@PathVariable("regionId") Long regionId) {
        regionService.deleteRegion(regionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
