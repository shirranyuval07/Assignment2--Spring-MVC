package taskmanager.entity;

import java.io.Serializable;
import java.util.UUID;

public class Task implements Serializable, Comparable<Task> {
    private String id;
    private String name;
    private String description;
    private Priority priority;

    public Task() {
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }

    public Task(String name, String description, Priority priority) {
        this();
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    public enum Priority {
        VERY_HIGH("Very High"),
        HIGH("High"),
        MEDIUM("Medium"),
        LOW("Low"),
        VERY_LOW("Very Low");

        private final String displayValue;

        Priority(String displayValue) {
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }

    // Compare logic for sorting by Priority
    @Override
    public int compareTo(Task other) {
        return this.priority.compareTo(other.priority);
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
}