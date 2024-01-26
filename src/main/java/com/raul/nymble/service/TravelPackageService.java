package com.raul.nymble.service;

import com.raul.nymble.DTO.TravelPackageDTO;

import java.util.List;

public interface TravelPackageService {
    List<TravelPackageDTO> getAllTravelPackages();

    TravelPackageDTO getTravelPackageById(Long id);
}
