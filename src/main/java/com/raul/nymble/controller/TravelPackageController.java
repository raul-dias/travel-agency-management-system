package com.raul.nymble.controller;

import com.raul.nymble.DTO.TravelPackageDTO;
import com.raul.nymble.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TravelPackageController {

    @Autowired
    TravelPackageService travelPackageService;

    @GetMapping("/travelPackages")
    public List<TravelPackageDTO> travelPackages(){

        return travelPackageService.getAllTravelPackages();
    }
    public void addTravelPackage(){

    }

    public void getTravelPackage(){

    }
}
