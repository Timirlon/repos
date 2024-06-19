package Lesson21_staticFinal.classwork.task4;

public class Task {
    private TaskPriority priority;
    private String description;

    public Task (TaskPriority priority, String description) {
        this.priority = priority;
        this.description = description;
    }

    public TaskPriority getPriority () {
        return priority;
    }

    public String getDescription () {
        return description;
    }
}
