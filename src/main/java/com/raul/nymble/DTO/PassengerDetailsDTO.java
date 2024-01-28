package com.raul.nymble.DTO;

import com.raul.nymble.model.Passenger;
import lombok.Data;

@Data
public class PassengerDetailsDTO {
    private String name;
    private int number;

    public PassengerDetailsDTO(Passenger passenger){
        this.name = passenger.getName();
        this.number = passenger.getNumber();
    }
}
