package augustus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a deadline task with the given description and due date.
     *
     * @param description Description of the deadline task.
     * @param by Due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the display representation of this deadline task.
     *
     * @return String containing the task details and formatted due date.
     */
    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM d yyyy");
        return String.format("[D]%s (by: %s)",super.toString(),by.format(f));
    }

    /**
     * Returns the representation of this deadline task used for storage.
     *
     * @return String representation of the deadline task for saving to a file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %s | %s | %s",(isDone ? "1" : "0"), description, by);
    }
}
