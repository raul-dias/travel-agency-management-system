package com.raul.nymble.controller;

import com.raul.nymble.DTO.TravelPackageDTO;
import com.raul.nymble.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TravelPackageController {

    @Autowired
    TravelPackageService travelPackageService;

    @GetMapping("/travelPackage")
    public List<TravelPackageDTO> travelPackages(){
        return travelPackageService.getAllTravelPackages();
    }
    @GetMapping("/travelPackage/{id}")
    public TravelPackageDTO getTravelPackage(@PathVariable Long id){
        return travelPackageService.getTravelPackageById(id);
    }
    @PostMapping("/travelPackage")
    public void addTravelPackage(@RequestBody TravelPackageDTO travelPackageDTO){
        travelPackageService.addTravelPackage(travelPackageDTO);
    }
    @DeleteMapping("/travelPackage/{id}")
    public void deleteTravelPackage(@PathVariable Long id){
        travelPackageService.deleteTravelPackage(id);
    }
    @PutMapping("/travelPackage/{id}")
    public void updateTravelPackage(@RequestBody TravelPackageDTO travelPackageDTO, @PathVariable Long id){
        travelPackageService.updateTravelPackage(travelPackageDTO, id);
    }
}
