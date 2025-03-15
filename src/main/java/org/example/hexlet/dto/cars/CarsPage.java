package org.example.hexlet.dto.cars;

import org.example.hexlet.model.Car;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class CarsPage {
    private List<Car> cars;
}
