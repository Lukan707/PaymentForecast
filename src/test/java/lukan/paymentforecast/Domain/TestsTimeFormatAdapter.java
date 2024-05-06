package lukan.paymentforecast.Domain;

import org.junit.jupiter.api.Test;

import lukan.paymentforecast.Domain.Exceptions.InvalidTimeSlotException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/*
 * This class contains unit test for the TimeFormatAdapter class.
 */
public class TestsTimeFormatAdapter {

    @Test
    public void TimeformatToSeconds_1600() {
        // Arrange
        int actual = 0;
        
        // Act
        try {
            actual = TimeFormatParser.timeformatToSeconds("16:00");
        } catch(InvalidTimeSlotException e) {

        }

        // Assert
        assertEquals(57600, actual);
    }

    @Test
    public void TimeFormatToSeconds_2015() {
        // Arrange
        int actual = 0;

        // Act
        try {
            actual = TimeFormatParser.timeformatToSeconds("20:15");
        } catch(InvalidTimeSlotException e) {

        }

        // Assert
        assertEquals(72900, actual);
    }

    @Test
    public void TimeFormatToSeconds_2130() {
        // Arrange
        int actual = 0;
        
        // Act
        try {
            actual = TimeFormatParser.timeformatToSeconds("21:30");
        } catch(InvalidTimeSlotException e) {

        }

        // Assert
        assertEquals(77400, actual);
    }

    @Test
    public void TimeformatToSeconds_2229() {
        // Arrange
        int actual = 0;

        // Act
        try {
            actual = TimeFormatParser.timeformatToSeconds("22:29");
        } catch(InvalidTimeSlotException e) {

        }

        // Assert
        assertEquals(80940, actual);

    }

    @Test
    public void TimeformatToSeconds_2359() {
        // Arrange
        int actual = 0;

        // Act
        try {
            actual = TimeFormatParser.timeformatToSeconds("23:59");
        } catch(InvalidTimeSlotException e) {
 
        }

        // Assert
        assertEquals(86340, actual);
    }

    @Test
    public void TimeformatToSeconds_2400_ThrowsException() {
        // Arrange
        String time = "24:00";

        // Assert
        assertThrowsExactly(InvalidTimeSlotException.class, () -> TimeFormatParser.timeformatToSeconds(time));
    }

    @Test
    public void TimeformatToSeconds_minus0100_ThrowsException() {
        // Arrange
        String time = "-01:00";

        // Assert
        assertThrowsExactly(InvalidTimeSlotException.class, () -> TimeFormatParser.timeformatToSeconds(time));
    }

    @Test
    public void TimeformatToSeconds_0060_ThrowsException() {
        // Arrange
        String time = "00:60";

        // Assert
        assertThrowsExactly(InvalidTimeSlotException.class, () -> TimeFormatParser.timeformatToSeconds(time));
    }

    @Test
    public void TimeformatToSeconds_00minus01_ThrowsException() {
        // Arrange
        String time = "00:-01";

        // Assert
        assertThrowsExactly(InvalidTimeSlotException.class, () -> TimeFormatParser.timeformatToSeconds(time));
    }
}
