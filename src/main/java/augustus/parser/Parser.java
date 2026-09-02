package augustus.parser;

import augustus.exception.AugustusException;

/**
 * Parses user input and extracts commands and command parameters
 */
public class Parser {

    /**
     * Returns the command word from the user's input
     *
     * @param input User input
     * @return the command word
     */
    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Extracts the task number from a command
     *
     * @param input User input
     * @return The task number from the commands mark or unmark or delete
     * @throws AugustusException if the task number is missing or invalid
     */
    public static int parseTaskNum(String input) throws AugustusException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new AugustusException("A task number is required");
        }
        try {
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new AugustusException("Task number must be number");
        }
    }

    /**
     * Extracts the description of todo task
     *
     * @param input User input containing todo command
     * @return the todo description
     * @throws AugustusException If the description is empty
     */
    public static String parseTodo(String input) throws AugustusException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new AugustusException("You cannot enter the empire without a description");
        }
        return description;
    }

    /**
     * Parses a deadline command into its description and due date
     *
     * @param input User input containing the deadline command
     * @return an array containing description and the due date
     * @throws AugustusException if the deadline format is invalid
     */
    public static String[] parseDeadline(String input) throws AugustusException {
        int index = input.indexOf(" /by ");
        if (index == -1) {
            throw new AugustusException("A deadline must contain /by followed by the date");
        }

        String description = input.substring(8, index).trim();
        String date = input.substring(index + 5).trim();

        if (description.isEmpty()) {
            throw new AugustusException("The deadline must have a description");
        }
        if (date.isEmpty()) {
            throw new AugustusException("Write when is the deadline is due");
        }
        return new String[]{description, date};
    }

    /**
     * Parses an event command into its description, start time and end time.
     *
     * @param input User input containing event command
     * @return An array containing the description, start time and end time.
     * @throws AugustusException if the event format is invalid
     */
    public static String[] parseEvent(String input) throws AugustusException {
        int fromIndex = input.indexOf(" /from ");
        int toIndex = input.indexOf(" /to ");

        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            throw new AugustusException("The message should include /from and /to");
        }
        int fromStart = fromIndex + 7;
        if (fromStart > toIndex) {
            throw new AugustusException("Write when this event starts and ends");
        }

        String description = input.substring(5, fromIndex).trim();
        String from = input.substring(fromStart, toIndex).trim();
        String to = input.substring(toIndex + 5).trim();

        if (description.isEmpty()) {
            throw new AugustusException("The event must have a description");
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new AugustusException("Write when this event starts and ends");
        }

        return new String[]{description, from, to};
    }

    public static String parseFind(String input) throws AugustusException {
        String keyword = input.substring(4).trim();

        if (keyword.isEmpty()) {
            throw new AugustusException("A keyword is required");
        }

        return keyword;
    }
}
