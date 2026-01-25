package taskmanager.entity;

import java.io.Serializable;

public class Passenger implements Serializable, Comparable<Passenger> {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String passportId;
    private Long flightId; // Foreign Key

    public Passenger() {}

    public Passenger(Long id, String firstName, String lastName, String passportId, Long flightId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportId = passportId;
        this.flightId = flightId;
    }

    @Override
    public int compareTo(Passenger other) {
        return this.lastName.compareTo(other.lastName);
    }

    // Getters and Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPassportId() { return passportId; }
    public void setPassportId(String passportId) { this.passportId = passportId; }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
}