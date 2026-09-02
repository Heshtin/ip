package augustus.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import augustus.exception.AugustusException;

public class ParserTest {

    @Test
    public void parseDeadline_validInput_returnsDescriptionAndDate()
            throws AugustusException {
        String input = "deadline return book /by 2026-09-10";

        String[] result = Parser.parseDeadline(input);

        String[] expected = {"return book", "2026-09-10"};
        assertArrayEquals(expected, result);
    }

    @Test
    public void parseDeadline_missingBy_exceptionThrown() {
        String input = "deadline return book";

        assertThrows(AugustusException.class, () ->
                Parser.parseDeadline(input));
    }

    @Test
    public void parseDeadline_emptyDescription_exceptionThrown() {
        String input = "deadline /by 2026-09-10";

        assertThrows(AugustusException.class, () ->
                Parser.parseDeadline(input));
    }

    @Test
    public void parseDeadline_emptyDate_exceptionThrown() {
        String input = "deadline return book /by ";

        assertThrows(AugustusException.class, () ->
                Parser.parseDeadline(input));
    }

    @Test
    public void parseEvent_missingFrom_exceptionThrown() {
        String input = "event project meeting /to 4pm";

        assertThrows(AugustusException.class, () ->
                Parser.parseEvent(input));
    }
    @Test
    public void parseEvent_missingTo_exceptionThrown() {
        String input = "event project meeting /from 2pm";

        assertThrows(AugustusException.class, () ->
                Parser.parseEvent(input));
    }

    @Test
    public void parseEvent_emptyDescription_exceptionThrown() {
        String input = "event /from 2pm /to 4pm";

        assertThrows(AugustusException.class, () ->
                Parser.parseEvent(input));
    }

    @Test
    public void parseEvent_emptyEndTime_exceptionThrown() {
        String input = "event project meeting /from 2pm /to ";

        assertThrows(AugustusException.class, () ->
                Parser.parseEvent(input));
    }
    @Test
    public void parseEvent_validInput_returnsDescriptionFromAndTo()
            throws AugustusException {
        String input = "event project meeting /from 2pm /to 4pm";

        String[] result = Parser.parseEvent(input);

        String[] expected = {"project meeting", "2pm", "4pm"};
        assertArrayEquals(expected, result);
    }
    @Test
    public void parseTaskNum_validTaskNumber_returnsTaskNumber()
            throws AugustusException {
        int result = Parser.parseTaskNum("mark 2");

        assertEquals(2, result);
    }

    @Test
    public void parseTaskNum_missingTaskNumber_exceptionThrown() {
        assertThrows(AugustusException.class, () ->
                Parser.parseTaskNum("mark"));
    }

    @Test
    public void parseTaskNum_invalidTaskNumber_exceptionThrown() {
        assertThrows(AugustusException.class, () ->
                Parser.parseTaskNum("mark abc"));
    }

}