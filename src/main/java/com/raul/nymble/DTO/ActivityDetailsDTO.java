package com.raul.nymble.DTO;

import com.raul.nymble.model.Activity;
import lombok.Data;

@Data
public class ActivityDetailsDTO {
    private String name;
    private String description;
    private String destination;
    private Double price;

    public ActivityDetailsDTO(Activity activity, String destination, Double discount){
        this.name = activity.getName();
        this.description = activity.getDescription();
        if(discount != null){
            this.price = activity.getCost() *(1 - discount);
        } else
            this.price = activity.getCost().doubleValue();
        this.destination = destination;
    }
}
