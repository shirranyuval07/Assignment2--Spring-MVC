package taskmanager.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Flight implements Serializable, Comparable<Flight> {
    private static final long serialVersionUID = 1L;

    // Getters and Setters
    private Long id;
    private String origin;
    private String destination;
    private int maxCapacity;

    public Flight() {
    }

    public Flight(Long id, String origin, String destination, int maxCapacity) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.maxCapacity = maxCapacity;
    }

    // מיון לפי יעד (Destination)
    @Override
    public int compareTo(Flight other) {
        return this.destination.compareTo(other.destination);
    }

    // Helper specifically for HTML forms to display title
    public String getDisplayString() {
        return origin + " -> " + destination;
    }
}