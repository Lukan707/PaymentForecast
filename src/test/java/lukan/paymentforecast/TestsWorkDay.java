package lukan.paymentforecast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lukan.paymentforecast.DomainLogic.*;

/*
 * This class contains Unit tets for the Workday class.
 */
public class TestsWorkDay {

    User user;
    User user2;

    @BeforeEach
    public void Setup() {
        
        user = new User("Test user", 128.83);
        ArrayList<Supplement> supplements = new ArrayList<Supplement>() {{
            // startTime = 18:00, endTime = 23:00
            add(new Supplement(29.0, 64800, 82800, SupplementType.Weekday));
            // startTime = 23:00, endTime = 23:59:59
            add(new Supplement(38.95, 82800, 86399, SupplementType.Weekday));
            // StartTime = 00:00, endTime = 06:00:59
            add(new Supplement(38.95, 0, 21600, SupplementType.Weekday));
            // startTIme = 15:00, endTime = 23:59:59
            add(new Supplement(51.0, 54000, 86399, SupplementType.Saturday));
            // startTime = 00:00, endTime = 23:59:59
            add(new Supplement(58.15, 0, 86399, SupplementType.Sunday));
            // startTime = 00:00, endTime = 23:59:59
            add(new Supplement(38.95, 0, 86399, SupplementType.Holiday));
            // has no specified start and end time, should be applied for all hours it is applied
            add(new Supplement(128.83, 0, 86399, SupplementType.Extraordinary));


        }};
        user.supplements = supplements;

        user2 = new User("test", 128.83);
        ArrayList<Supplement> supplements2 = new ArrayList<Supplement>() {{
            // startTime = 18:00, endTime = 23:00
            add(new Supplement(29.0, 64800, 82800, SupplementType.Weekday));
            // startTime = 23:00, endTime = 23:59:59    
            add(new Supplement(38.95, 82800, 86399, SupplementType.Weekday));
            // StartTime = 00:00, endTime = 06:00
            add(new Supplement(38.95, 0, 21600, SupplementType.Weekday));
            // startTIme = 15:00, endTime = 23:59:59
            add(new Supplement(51.0, 54000, 86399, SupplementType.Saturday));
            // startTime = 00:00, endTime = 23:59:59
            add(new Supplement(58.15, 0, 86399, SupplementType.Sunday));
            // startTime = 00:00, endTime = 23:59:59
            add(new Supplement(38.95, 0, 86399, SupplementType.Holiday));
            // has no specified start and end time, should be applied for all hours it is applied
            add(new Supplement(128.83, 0, 86399, SupplementType.Extraordinary));
        }};
        user2.supplements = supplements2;
        user2.senioritySupplement = 3.0;
    }

    @AfterEach
    void tearDown() {
        user = null;
        user2 = null;
    }

    @Test
    public void WeekDayWithoutBreakAndSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 14:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(515.32, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithBreakAndNoSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 08:00, endTime = 11:00
                add(new TimeInterval(28800, 39600, false));
                // startTime = 11:00, endTime = 12:00
                add(new TimeInterval(39600, 43200, true));
                // startTime = 12:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(901.81, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithoutBreakWithEveningSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 17:45, endTime = 21:15
                add(new TimeInterval(63900, 76500, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(545.16, workDay.getSalary(), 0.01);
    }

    @Test
    public void SaturdayWithBreakAndSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 16:45, endTime = 18:45
                add(new TimeInterval(60300, 67500, false));
                // startTime = 18:45, endTime = 19:15
                add(new TimeInterval(67500, 69300, true));
                // startTime = 19:15, endTime = 21:15
                add(new TimeInterval(69300, 76500, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Saturday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(719.32, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithBreakAndEveningSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 14:00, endTime = 18:00
                add(new TimeInterval(50400, 64800, false));
                // startTime = 18:00, endTime = 18:30
                add(new TimeInterval(64800, 66600, true));
                // startTime = 18:30, endTime = 21:15
                add(new TimeInterval(66600, 76500, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(949.3525, workDay.getSalary(), 0.01);
    }

    @Test

    public void WeekDayWithoutBreakWithNightSupplementLateHours() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 17:45, endTime = 23:59:59
                add(new TimeInterval(63900, 86399, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(989.0909, workDay.getSalary(), 0.01);
    }
    

    @Test
    public void WeekDayWithBreakWithNightSupplementLateHours() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 16:00, endTime = 23:00
                add(new TimeInterval(57600, 82800, false));
                // startTime = 23:00, endTime = 23:30
                add(new TimeInterval(82800, 84600, true));
                // startTime = 23:30, endTime = 23:59:59
                add(new TimeInterval(84600, 86399, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1130.65, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithoutBreakWithNightSupplementEarlyHours() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 04:00, endTime = 14:00
                add(new TimeInterval(14400, 50400, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1366.2, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithBreakWithNightSupplementEarlyHours() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 04:00, endTime = 05:30
                add(new TimeInterval(14400, 19800, false));
                // startTime = 05:30, endTime = 06:00
                add(new TimeInterval(19800, 21600, true));
                // startTime = 06:00, endTime = 10:00
                add(new TimeInterval(21600, 36000, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(766.99, workDay.getSalary(), 0.01);
    }

    @Test
    public void SaturdayWithoutBreakWithSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 12:00, endTime = 20:00
                add(new TimeInterval(43200, 72000, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Saturday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1285.64, workDay.getSalary(), 0.01);
    }

    @Test
    public void SaturdayWithBreakSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 14:00, endTime = 16:00
                add(new TimeInterval(50400, 57600, false));
                // startTime = 16:00, endTime = 16:30
                add(new TimeInterval(57600, 59400, true));
                // startTime = 16:30, endTime = 20:00
                add(new TimeInterval(59400, 72000, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Sunday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1028.39, workDay.getSalary(), 0.01);
    }

    @Test
    public void SundayWithoutBreakWithSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 13:00, endTime = 21:00
                add(new TimeInterval(46800, 75600, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Sunday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1495.84, workDay.getSalary(), 0.01);
    }

    @Test
    public void SundayWithBreakAndSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 13:00, endTime = 17:00
                add(new TimeInterval(46800, 61200, false));
                // startTime = 17:00, endTime = 17:30
                add(new TimeInterval(61200, 63000, true));
                // startTime = 17:30, endTime = 21:00
                add(new TimeInterval(63000, 75600, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Sunday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1402.35, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithBreakAndSenioritySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 08:00, endTime = 11:00
                add(new TimeInterval(28800, 39600, false));
                // startTime = 11:00, endTime = 12:00
                add(new TimeInterval(39600, 43200, true));
                // startTime = 12:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(922.81, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithoutBreakWithSenioritySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 12:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(527.32, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithoutBreakWithEveningAndSenioritySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 17:45, endTime = 21:15
                add(new TimeInterval(63900, 76500, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(555.66, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithBreakEveningAndSenioritySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 14:00, endTime = 18:00
                add(new TimeInterval(50400, 64800, false));
                // startTime = 18:00, endTime = 18:30
                add(new TimeInterval(64800, 66600, true));
                // startTime = 18:30, endTime = 21:15
                add(new TimeInterval(66600, 76500, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(969.6025, workDay.getSalary(), 0.01);
    }

    @Test

    public void WeekDayWithoutBreakWithNightAndSenioritySupplementLateHours() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 17:45, endTime = 23:59:59
                add(new TimeInterval(63900, 86399, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1007.84, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithBreakNightAndSenioritySupplementLateHours() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 16:00, endTime = 23:00
                add(new TimeInterval(57600, 82800, false));
                // startTime = 23:00, endTime = 23:30
                add(new TimeInterval(82800, 84600, true));
                // startTime = 23:30, endTime = 23:59:59
                add(new TimeInterval(84600, 86399, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1153.153, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithoutBreakWithNightAndSenioritySupplementEarlyHours() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 04:00, endTime = 14:00
                add(new TimeInterval(14400, 50400, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1396.2, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithBreakNightAndSenioritySupplementEarlyHours() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 04:00, endTime = 05:30
                add(new TimeInterval(14400, 19800, false));
                // startTime = 05:30, endTime = 06:00
                add(new TimeInterval(19800, 21600, true));
                // startTime = 06:00, endTime = 10:00
                add(new TimeInterval(21600, 36000, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(783.49, workDay.getSalary(), 0.01);
    }

    @Test
    public void SaturdayWithoutBreakWithSundayAndSenioritySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 12:00, endTime = 20:00
                add(new TimeInterval(43200, 72000, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Saturday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1309.64, workDay.getSalary(), 0.01);
    }

    @Test
    public void SaturdayWithBreakAndSenioritySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 14:00, endTime = 16:00
                add(new TimeInterval(50400, 57600, false));
                // startTime = 16:00, endTime = 16:30
                add(new TimeInterval(57600, 59400, true));
                // startTime = 16:30, endTime = 20:00
                add(new TimeInterval(59400, 72000, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Sunday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1044.89, workDay.getSalary(), 0.01);
    }

    @Test
    public void SundayWithoutBreakWithSundayAndSenioritySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 13:00, endTime = 21:00
                add(new TimeInterval(46800, 75600, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Sunday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1519.84, workDay.getSalary(), 0.01);
    }

    @Test
    public void SundayWithBreakSundayAndSenioritySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 13:00, endTime = 17:00
                add(new TimeInterval(46800, 61200, false));
                // startTime = 17:00, endTime = 17:30
                add(new TimeInterval(61200, 63000, true));
                // startTime = 17:30, endTime = 21:00
                add(new TimeInterval(63000, 75600, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Sunday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1424.85, workDay.getSalary(), 0.01);
    }

    @Test
    public void SundayWithBreakSundayAndSenioritySupplementLateHours() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 16:45, endTime = 18:45
                add(new TimeInterval(60300, 67500, false));
                // startTime = 18:45, endTime = 19:15
                add(new TimeInterval(67500, 69300, true));
                // startTime = 19:15, endTime = 21:15
                add(new TimeInterval(69300, 76500, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Saturday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(731.32, workDay.getSalary(), 0.01);
    }
    
    @Test
    public void HoliayWithoutBreakWithHolidaySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 14:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Holiday, user);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(671.12, workDay.getSalary(), 0.01);
    }

    @Test
    public void HolidayDayWithoutBreakWithHolidayAndSenioritySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 14:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };
        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Holiday, user2);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(683.12, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekdayWithoutBreakWithBonusSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 14:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user, 1000.0);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1515.32, workDay.getSalary(), 0.01);
    }

    @Test 
    public void WeekdayWithBreakAndBonusSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 10:00, endTime = 12:00
                add(new TimeInterval(36000, 43200, false));
                // startTime = 12:00, endTime = 14:00
                add(new TimeInterval(43200, 50400, true));
                // startTime = 14:00, endTime = 16:00
                add(new TimeInterval(50400, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user, 1000.0);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1515.32, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekdayWithBreakSeniorityAndBonusSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 10:00, endTime = 12:00
                add(new TimeInterval(36000, 43200, false));
                // startTime = 12:00, endTime = 14:00
                add(new TimeInterval(43200, 50400, true));
                // startTime = 14:00, endTime = 16:00
                add(new TimeInterval(50400, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user2, 1000.0);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1527.32, workDay.getSalary(), 0.01);
    }

    @Test
    public void SaturdayWithBreakSaturdayAndBonusSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 10:00, endTime = 12:00
                add(new TimeInterval(36000, 43200, false));
                // startTime = 12:00, endTime = 14:00
                add(new TimeInterval(43200, 50400, true));
                // startTime = 14:00, endTime = 16:00
                add(new TimeInterval(50400, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Saturday, user, 1000.0);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1566.32, workDay.getSalary(), 0.01);
    }

    @Test
    public void SundayWithBreakSundayAndBonusSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 10:00, endTime = 12:00
                add(new TimeInterval(36000, 43200, false));
                // startTime = 12:00, endTime = 14:00
                add(new TimeInterval(43200, 50400, true));
                // startTime = 14:00, endTime = 16:00
                add(new TimeInterval(50400, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Sunday, user, 1000.0);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1747.92, workDay.getSalary(), 0.01);
    }

    @Test 
    public void HolidayWithoutBreakWithHolidayAndBonusSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 12:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Holiday, user, 1000.0);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1671.12, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekdayWithoutBreakWithExtraodinarySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 12:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user, List.of(SupplementType.Extraordinary));

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1030.64, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekdayWithBreakWithExtraodinarySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 10:00, endTime = 12:00
                add(new TimeInterval(36000, 43200, false));
                // startTime = 12:00, endTime = 14:00
                add(new TimeInterval(43200, 50400, true));
                // startTime = 14:00, endTime = 16:00
                add(new TimeInterval(50400, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user, List.of(SupplementType.Extraordinary));

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1030.64, workDay.getSalary(), 0.01);
    }

    @Test
    public void SaturdayWithoutBreakWithSaturdayAndExtraordinarySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 12:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Saturday, user, List.of(SupplementType.Extraordinary));

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1081.64, workDay.getSalary(), 0.01);
    }

    @Test
    public void SundayWithoutBreakWithSundayAndExtraordinarySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 12:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Sunday, user, List.of(SupplementType.Extraordinary));

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1263.24, workDay.getSalary(), 0.01);
    }

    @Test
    public void HolidayWithoutBreakWithHolidayAndExtraordinarySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 12:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Holiday, user, List.of(SupplementType.Extraordinary));

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(1186.44, workDay.getSalary(), 0.01);
    }

    @Test
    public void WeekDayWithoutBreakWithBonusAndExtraordinarySupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<TimeInterval>() {
            {
                // startTime = 12:00, endTime = 16:00
                add(new TimeInterval(43200, 57600, false));
            }
        };

        WorkDay workDay = new WorkDay(new Date(), intervals, WorkDayType.Weekday, user, List.of(SupplementType.Extraordinary), 1000.0);

        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(2030.64, workDay.getSalary(), 0.01);
    }
}
