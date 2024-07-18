package lukan.paymentforecast.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import lukan.paymentforecast.Domain.TimeFormatParser;
import lukan.paymentforecast.Domain.Exceptions.InvalidTimeSlotException;

public class TestTimeFormatParser {
    
    @Test
    public void test0000() {
        // Arrange
        int seconds = 0;

        // Act
        try {
            seconds = TimeFormatParser.timeformatToSeconds("00:00");
        } catch (InvalidTimeSlotException e) {

        }

        // Assert
        assertEquals(0, seconds);
    }
    
    @Test
    public void test0100() {
        // Arrange
        int seconds = 0;

        // Act
        try {
            seconds = TimeFormatParser.timeformatToSeconds("01:00");
        } catch (InvalidTimeSlotException e) {

        }

        // Assert
        assertEquals(3600, seconds);
    }

    @Test
    public void test0030() {
        // Arrange
        int seconds = 0;

        // Act
        try {
            seconds = TimeFormatParser.timeformatToSeconds("00:30");
        } catch (InvalidTimeSlotException e) {

        }

        // Assert
        assertEquals(1800, seconds);
    }

    @Test
    public void test0122() {
        // Arrange
        int seconds = 0;

        // Act
        try {
            seconds = TimeFormatParser.timeformatToSeconds("01:22");
        } catch (InvalidTimeSlotException e) {

        }

        // Assert
        assertEquals(4920, seconds);
    }

    @Test
    public void test2359() {
        // Arrange
        int seconds = 0;

        // Act
        try {
            seconds = TimeFormatParser.timeformatToSeconds("23:59");
        } catch (InvalidTimeSlotException e) {

        }

        // Assert
        assertEquals(86340, seconds);
    }

    @Test
    public void testEmptyStringThrowsException() {
        InvalidTimeSlotException e = assertThrows(InvalidTimeSlotException.class, () -> TimeFormatParser.timeformatToSeconds(""));
        assertEquals("The given string is empty", e.getMessage());
    }

    @Test
    public void test0066ThrowsException() {
        InvalidTimeSlotException e = assertThrows(InvalidTimeSlotException.class, () -> TimeFormatParser.timeformatToSeconds("00:66"));
        assertEquals("The given minutes is not within a valid range (0 - 59)", e.getMessage());
    }

    @Test
    public void test2500ThrowsException() {
        InvalidTimeSlotException e = assertThrows(InvalidTimeSlotException.class, () -> TimeFormatParser.timeformatToSeconds("25:00"));
        assertEquals("The given hours is not within a valid range (0 - 23)", e.getMessage());
    }

    @Test
    public void testnegativeHoursThrowsException() {
        InvalidTimeSlotException e = assertThrows(InvalidTimeSlotException.class, () -> TimeFormatParser.timeformatToSeconds("-01:00"));
        assertEquals("The given hours is not within a valid range (0 - 23)", e.getMessage());
    }

    @Test
    public void testnegativeMinutesThrowsException() {
        InvalidTimeSlotException e = assertThrows(InvalidTimeSlotException.class, () -> TimeFormatParser.timeformatToSeconds("00:-01"));
        assertEquals("The given minutes is not within a valid range (0 - 59)", e.getMessage());
    }

    @Test
    public void testLettersThrowsException() {
        InvalidTimeSlotException e = assertThrows(InvalidTimeSlotException.class, () -> TimeFormatParser.timeformatToSeconds("aa:bb"));
        assertEquals("Timeslot contians illegal characters", e.getMessage());
    }
}
