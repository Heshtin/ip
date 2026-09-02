package augustus.task;

public class Event extends Task {

    protected String startTime;
    protected String endTime;

    public Event(String description, String from, String to) {
        super(description);
        this.startTime = from;
        this.endTime = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s | %s", (isDone ? "1" : "0"), description, startTime, endTime);
    }
}
