package org.example.hexlet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class Car {
    private Long id;

    @ToString.Include
    private String make;

    private String model;

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
    }
}
