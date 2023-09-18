package com.m2p.at.ftbtests.api.rest.v0.model;

import lombok.*;

@Data
@Builder(builderMethodName = "of")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreateAircraftDto {
    private String manufacturer;
    private String model;
    private Integer numberOfSeats;
}
