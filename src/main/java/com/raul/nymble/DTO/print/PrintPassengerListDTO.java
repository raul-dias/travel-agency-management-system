package com.raul.nymble.DTO.print;

import com.raul.nymble.DTO.PassengerDetailsDTO;
import com.raul.nymble.model.Passenger;
import lombok.Data;

import java.util.List;

@Data
public class PrintPassengerListDTO {
    private String packageName;
    private int packagePassengerCapasity;
    private int bookedPassenger;
    private List<PassengerDetailsDTO> passengerList;
}
