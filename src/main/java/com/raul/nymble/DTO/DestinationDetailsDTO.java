package com.raul.nymble.DTO;

import com.raul.nymble.model.Activity;

import java.util.List;

public class DestinationDetailsDTO {
    private String Name;
    private List<Activity> activities;

    public DestinationDetailsDTO(String name, List<Activity> activities) {
        Name = name;
        this.activities = activities;
    }
}
