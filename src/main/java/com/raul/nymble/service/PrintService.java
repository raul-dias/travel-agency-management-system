package com.raul.nymble.service;

import com.raul.nymble.DTO.print.PrintAvaliableActivities;
import com.raul.nymble.DTO.print.PrintItineraryDTO;
import com.raul.nymble.DTO.print.PrintPassengerDetails;
import com.raul.nymble.DTO.print.PrintPassengerListDTO;

import java.util.List;

public interface PrintService {
    PrintItineraryDTO printItinerary(Long id);

    PrintPassengerListDTO printPassengerList(Long id);

    PrintPassengerDetails printPassengerDetails(Long id);

    List<PrintAvaliableActivities> printAvaliableActivities();
}
