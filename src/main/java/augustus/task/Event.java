package augustus.task;

/**
 * Represents a task that occurs between a start time and an end time.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Creates an event with the given description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from,String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the display representation of this event.
     *
     * @return String containing the event description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + from +" to: "+to+ ")";
    }

    /**
     * Returns the representation of this event used for storage.
     *
     * @return String representation of the event for saving to a file.
     */
    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s | %s",(isDone ? "1" : "0"), description, from,to);
    }
}
