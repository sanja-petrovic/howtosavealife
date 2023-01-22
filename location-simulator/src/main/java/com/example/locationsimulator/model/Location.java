package com.example.locationsimulator.model;

import jakarta.persistence.Embeddable;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Location {
    private double latitude;
    private double longitude;
}
