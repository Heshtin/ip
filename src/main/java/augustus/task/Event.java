package augustus.task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from,String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + from +" to: "+to+ ")";
    }
    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s | %s",(isDone ? "1" : "0"), description, from,to);
    }
}
