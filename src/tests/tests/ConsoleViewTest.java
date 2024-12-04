package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ConsoleView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleViewTest {
    private ConsoleView consoleView;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        consoleView = new ConsoleView();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testShowMessage() {
        // Arrange
        String message = "Hello, World!";

        // Act
        consoleView.showMessage(message);

        // Assert
        String actualOutput = outputStream.toString().trim();
        assertEquals(message, actualOutput, "showMessage should print the message as-is.");
    }

    @Test
    public void testUpdate() {
        // Arrange
        String updateMessage = "Observer notified";

        // Act
        consoleView.update(updateMessage);

        // Assert
        String actualOutput = outputStream.toString().trim();
        assertEquals("Notification: " + updateMessage, actualOutput, "update should prepend 'Notification: ' to the message.");
    }

    @Test
    public void testShowMessageWithMultipleMessages() {
        // Arrange
        String[] messages = {"First message", "Second message", "Third message"};

        // Act
        for (String message : messages) {
            consoleView.showMessage(message);
        }

        // Assert
        String actualOutput = outputStream.toString().trim();
        String[] outputLines = actualOutput.split(System.lineSeparator());
        assertEquals(messages.length, outputLines.length, "The number of messages should match the output lines.");
        for (int i = 0; i < messages.length; i++) {
            assertEquals(messages[i], outputLines[i], "Message at index " + i + " should be printed correctly.");
        }
    }

    @Test
    public void testUpdateWithMultipleMessages() {
        // Arrange
        String[] updateMessages = {"Update 1", "Update 2", "Update 3"};

        // Act
        for (String update : updateMessages) {
            consoleView.update(update);
        }

        // Assert
        String actualOutput = outputStream.toString().trim();
        String[] outputLines = actualOutput.split(System.lineSeparator());
        assertEquals(updateMessages.length, outputLines.length, "The number of updates should match the output lines.");
        for (int i = 0; i < updateMessages.length; i++) {
            assertEquals("Notification: " + updateMessages[i], outputLines[i], "Update at index " + i + " should be prefixed with 'Notification: '.");
        }
    }
}
