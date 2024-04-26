package lukan.paymentforecast.Model;

import org.junit.jupiter.api.Test;

import lukan.paymentforecast.Domain.WorkDayType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSelectWorkDayType {
    
    @Test
    public void selectWorkDayType_Weekday() {
        // Act
        WorkDayType result = DataService.selectWorkDayType("Weekday");

        // Assert
        assertEquals(WorkDayType.Weekday, result);
    }

    @Test
    public void selectWorkDayType_Saturday() {
        // Act
        WorkDayType result = DataService.selectWorkDayType("Saturday");

        // Assert
        assertEquals(WorkDayType.Saturday, result);
    }

    @Test
    public void selectWorkDayType_Sunday() {
        // Act
        WorkDayType result = DataService.selectWorkDayType("Sunday");

        // Assert
        assertEquals(WorkDayType.Sunday, result);
    }

    @Test
    public void selectWorkDayType_Holiday() {
        // Act
        WorkDayType result = DataService.selectWorkDayType("Holiday");

        // Assert
        assertEquals(WorkDayType.Holiday, result);
    }
}
