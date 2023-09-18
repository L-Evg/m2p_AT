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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "airportCode")
public class Airport {
    @JsonIgnoreProperties({"departureAirport", "destinationAirport"})
    @OneToMany(mappedBy = "departureAirport", fetch = FetchType.EAGER)
    @Builder.Default
    @JsonManagedReference("airport-flights")
    List<Flight> flights = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long airportId;

    @Column(unique = true)
    private String airportCode;

    private String airportName;
    private String city;
    private String state;
    private String country;

    public Airport(String airportCode, String airportName, String city, String state, String country) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", airportCode='" + airportCode + '\'' +
                ", airportName='" + airportName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", flights=" + flights.stream()
                .filter(Objects::nonNull).map(Flight::getFlightNumber).collect(Collectors.toList()) +
                '}';
    }

    public String toShortString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", airportCode='" + airportCode + '\'' +
                ", airportName='" + airportName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
