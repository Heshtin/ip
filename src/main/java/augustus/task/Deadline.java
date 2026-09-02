package augustus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate dueDate;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.dueDate = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return String.format("[D]%s (by: %s)", super.toString(), dueDate.format(formatter));
    }

    @Override
    public String toFileString() {
        return String.format("D | %s | %s | %s", (isDone ? "1" : "0"), description, dueDate);
    }
}
