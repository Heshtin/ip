import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM d yyyy");
        return String.format("%s (by: %s)",super.toString(),by.format(f));
    }

    @Override
    public String toFileString() {
        return String.format("D | %s | %s | %s",(isDone ? "1" : "0"), description, by);
    }
}
