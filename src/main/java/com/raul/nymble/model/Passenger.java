package com.raul.nymble.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * This is a Model that implements a Passenger. We are not concerned
 * with having duplicate passengers here as this is purely to maintain
 * a record of passengers on different travel packages
 */
@Entity
@Data
public class Passenger {
    public static enum tierEnum {
        STANDARD, GOLD, PREMIUM
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int number;
    private tierEnum tier;
    private Double balance;

    public Passenger() { }

    public Passenger(Passenger passenger) {
        this.name = passenger.name;
        this.number = passenger.number;
        this.tier = passenger.tier;
        this.balance = passenger.balance;
    }

    public Passenger(String name, int number, int tier, Double balance) {
        this.name = name;
        this.number = number;
        switch (tier) {
            case 1: this.tier = tierEnum.STANDARD; break;
            case 2: this.tier = tierEnum.GOLD; break;
            case 3: this.tier = tierEnum.PREMIUM; break;
            default: this.tier = tierEnum.STANDARD; break;
        }
        this.balance = balance;
    }
}
