package com.m2p.at.ftbtests.data.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "flightNumber")
public class Flight {
    @ManyToOne
    @JsonBackReference("aircraft-flights")
    Aircraft aircraft;

    @JsonIgnoreProperties({"flight"})
    @OneToMany(mappedBy = "flight", fetch = FetchType.EAGER)
    @Builder.Default
    @JsonManagedReference("flight-passengers")
    List<Passenger> passengers = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long flightId;
    private String flightNumber;

    @ManyToOne
    @JsonBackReference("airport-flights")
    private Airport departureAirport;

    @ManyToOne
    @JsonBackReference
    private Airport destinationAirport;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;
    private String departureTime;
    private String arrivalTime;
    private double flightCharge;

    public Flight(String flightNumber, Airport departureAirport, Airport destinationAirport,
                  double flightCharge, LocalDate departureDate, LocalDate arrivalDate) {

        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.flightCharge = flightCharge;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureAirport=" + (departureAirport == null ? null : departureAirport.getAirportCode()) +
                ", destinationAirport=" + (destinationAirport == null ? null : destinationAirport.getAirportCode()) +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", flightCharge=" + flightCharge +
                ", aircraft=" + (aircraft == null ? null : aircraft.getModel()) +
                ", passengers=" + passengers.stream().map(Passenger::getLastName).collect(Collectors.toList()) +
                '}';
    }

    public String toShortString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureAirport=" + (departureAirport == null ? null : departureAirport.getAirportCode()) +
                ", destinationAirport=" + (destinationAirport == null ? null : destinationAirport.getAirportCode()) +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", flightCharge=" + flightCharge +
                ", aircraft=" + (aircraft == null ? null : aircraft.getModel()) +
                '}';
    }
/*
    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getFlightCharge() {
        return flightCharge;
    }

    public void setFlightCharge(double flightCharge) {
        this.flightCharge = flightCharge;
    }*/
}
