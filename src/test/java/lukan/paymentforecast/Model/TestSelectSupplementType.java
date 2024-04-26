package lukan.paymentforecast.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lukan.paymentforecast.Domain.SupplementType;

public class TestSelectSupplementType {
    
    @Test
    public void selectSupplementType_Weekday() {
        // Act
        SupplementType result = DataService.selectSupplementType("Weekday");
        
        // Assert
        assertEquals(SupplementType.Weekday, result);
    }

    @Test
    public void selectSupplementType_Saturday() {
        // Act
        SupplementType result = DataService.selectSupplementType("Saturday");
        
        // Assert
        assertEquals(SupplementType.Saturday, result);
    }

    @Test
    public void selectSupplementType_Sunday() {
        // Act
        SupplementType result = DataService.selectSupplementType("Sunday");
        
        // Assert
        assertEquals(SupplementType.Sunday, result);
    }

    @Test
    public void selectSupplementType_Holiday() {
        // Act
        SupplementType result = DataService.selectSupplementType("Holiday");
        
        // Assert
        assertEquals(SupplementType.Holiday, result);
    }

    @Test
    public void selectSupplementType_Extraordinary() {
        // Act
        SupplementType result = DataService.selectSupplementType("Extraordinary");
        
        // Assert
        assertEquals(SupplementType.Extraordinary, result);
    }
}
