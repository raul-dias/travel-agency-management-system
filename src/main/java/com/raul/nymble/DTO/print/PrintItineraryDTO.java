package com.raul.nymble.DTO.print;

import com.raul.nymble.DTO.DestinationDetailsDTO;
import com.raul.nymble.DTO.TravelPackageDTO;
import lombok.Data;

import java.util.List;

@Data
public class PrintItineraryDTO {
    private String itineraryName;
    private List<DestinationDetailsDTO> destinations;
}
