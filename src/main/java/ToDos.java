public class ToDos extends Task {


    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return String.format("ToDos | %s | %s",(isDone ? "1" : "0"), description);
    }
}
