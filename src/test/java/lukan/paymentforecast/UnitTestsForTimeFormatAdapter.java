import Controller.TimeFormatAdapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTestsForTimeFormatAdapter {

    @Test
    public void TimeformatToSeconds_1600() {
        // Act
        int actual = TimeFormatAdapter.timeformatToSeconds("16:00");

        // Assert
        assertEquals(57600, actual);
    }

    @Test
    public void TimeFormatToSeconds_2015() {
        // Act
        int actual = TimeFormatAdapter.timeformatToSeconds("20:15");

        // Assert
        assertEquals(72900, actual);
    }

    @Test
    public void TimeFormatToSeconds_2130() {
        // Act
        int actual = TimeFormatAdapter.timeformatToSeconds("21:30");

        // Assert
        assertEquals(77400, actual);
    }

    @Test
    public void TimeformatToSeconds_2229() {
        // Act
        int actual = TimeFormatAdapter.timeformatToSeconds("22:29");

        // Assert
        assertEquals(80940, actual);

    }

    @Test
    public void TimeformatToSeconds_2359() {
        // Act
        int actual = TimeFormatAdapter.timeformatToSeconds("23:59");

        // Assert
        assertEquals(86340, actual);
    }
}
