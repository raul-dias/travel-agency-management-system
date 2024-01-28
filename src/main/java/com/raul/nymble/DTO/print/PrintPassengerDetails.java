package com.raul.nymble.DTO.print;

import com.raul.nymble.DTO.ActivityDetailsDTO;
import com.raul.nymble.model.Passenger;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PrintPassengerDetails {
    private String name;
    private int number;
    private Long balance;
    private List<ActivityDetailsDTO> activityDetailsList;

    public PrintPassengerDetails(Passenger passenger) {
        this.name = passenger.getName();
        this.number = passenger.getNumber();
        this.balance = passenger.getBalance();
        this.activityDetailsList = new ArrayList<>();
    }
}
