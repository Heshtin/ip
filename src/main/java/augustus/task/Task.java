package augustus.task;

/**
 * Represents a task with a description and completion status.
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markDone(){
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markNotDone(){
        this.isDone = false;
    }

    /**
     * Returns the display representation of this task.
     *
     * @return String containing the completion status and task description.
     */
    @Override
    public String toString() {
        String icon = isDone ? "X" : " ";
        return "[" + icon + "] " + description;
    }

    /**
     * Returns the representation of this task used for storage.
     *
     * @return String representation of the task for saving to a file.
     */
    public abstract String toFileString();

}
