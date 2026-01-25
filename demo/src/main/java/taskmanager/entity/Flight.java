package taskmanager.entity;

import java.io.Serializable;

public class Flight implements Serializable, Comparable<Flight> {
    private static final long serialVersionUID = 1L;

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

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    // Helper specifically for HTML forms to display title
    public String getDisplayString() {
        return origin + " -> " + destination;
    }
}