package taskmanager.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Passenger implements Serializable, Comparable<Passenger> {
    private static final long serialVersionUID = 1L;

    // Getters and Setters
    private Long id;
    private String firstName;
    private String lastName;
    private String passportId;
    private Long flightId;
    private Integer seatNumber; // שדה חדש למושב

    public Passenger() {}

    public Passenger(Long id, String firstName, String lastName, String passportId, Long flightId, Integer seatNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportId = passportId;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
    }

    @Override
    public int compareTo(Passenger other) {
        return this.lastName.compareTo(other.lastName);
    }

}