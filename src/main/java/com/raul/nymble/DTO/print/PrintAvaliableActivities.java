package com.raul.nymble.DTO.print;

import com.raul.nymble.model.Activity;
import lombok.Data;

@Data
public class PrintAvaliableActivities {
    private String name;
    private String description;
    private int remainingCapasity;

    public PrintAvaliableActivities(Activity activity) {
        this.name = activity.getName();
        this.description = activity.getDescription();
        this.remainingCapasity = activity.getCapasity()-activity.getBookedCapasity();
    }
}
