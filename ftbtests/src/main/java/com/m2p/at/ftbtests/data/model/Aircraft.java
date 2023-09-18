package com.m2p.at.ftbtests.data.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "aircraftId")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aircraftId;
    private String manufacturer;
    private String model;
    private Integer numberOfSeats;

    @JsonIgnoreProperties({"aircraft"})
    @OneToMany(mappedBy = "aircraft", fetch = FetchType.EAGER)
    @Builder.Default
    @JsonManagedReference("aircraft-flights")
    private List<Flight> flights = new ArrayList<>();

    public Aircraft(String manufacturer, String model, Integer numberOfSeats) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.numberOfSeats = numberOfSeats;
    }

    public Aircraft(long id, String manufacturer, String model, Integer numberOfSeats) {
        this.aircraftId = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "aircraftId=" + aircraftId +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", flights=" + flights.stream()
                .filter(Objects::nonNull).map(Flight::getFlightNumber).collect(Collectors.toList()) +
                '}';
    }

    public String toShortString() {
        return "Aircraft{" +
                "aircraftId=" + aircraftId +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
/*
    public long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }*/
}
