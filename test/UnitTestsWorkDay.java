import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Controller.*;

public class UnitTestsWorkDay {

    User user;

    @BeforeEach
    public void Setup() {
        ArrayList<Supplement> supplements = new ArrayList<>() {{
            add(SupplementAdapter.Adapt(29.0, "18:00", "23:00", "weekday"));
            add(SupplementAdapter.Adapt(38.95, "23:00", "23:59", "weekday"));
            add(SupplementAdapter.Adapt(38.95, "00:00", "06:00", "weekday"));
            add(SupplementAdapter.Adapt(51.0, "15:00", "24:00", "saturday"));
            add(SupplementAdapter.Adapt(58.15, "00:00", "24:00", "sunday"));
        }};
        user = new User(128.83, supplements);
    }

    @AfterEach void tearDown() {
        user = null;
    }

    @Test
    public void WeekDayWithBreakAndNoSupplement() {
        // Arrange
        ArrayList<TimeInterval> intervals = new ArrayList<>() {{
            add(TimeIntervalAdapter.Adapt("08:00", "11:00", ""));
            add(TimeIntervalAdapter.Adapt("11:00", "12:00", "b"));
            add(TimeIntervalAdapter.Adapt("12:00", "16:00", ""));
        }};

        WorkDay workDay = new WorkDay(intervals, SupplementType.Weekday, user);
        
        // Act
        workDay.calculateSalary();

        // Assert
        assertEquals(901.81, workDay.getSalary(), 0.2);
    }

    @Test
    public void WeekDayWithoutBreakAndEveningSupplement() {
        WorkDay workDay = new WorkDay(new String[]{"17:45", "21:15", ""}, SupplementType.Weekday, user);
        workDay.calculateSalary();
        assertEquals(545.16, workDay.getSalary(), 0.2);
    }

    @Test
    public void SaturdatWithBreakAndSupplement() {
        WorkDay workDay = new WorkDay(new String[]{"16:45", "18:45", "", "18:45", "19:15", "b", "19:15", "21:15", ""}, SupplementType.Saturday, user);
        workDay.calculateSalary();
        assertEquals(719.32, workDay.getSalary(), 0.2);
    }

    @Test
    public void WeekDayWithBreakAndEveningSupplement() {
        WorkDay workDay = new WorkDay(new String[]{"14:00", "18:00", "", "18:00", "18:30", "b", "18:30", "21:15", ""}, SupplementType.Weekday, user);
        workDay.calculateSalary();
        assertEquals(949.3525, workDay.getSalary(), 0.2);
    }

    @Test
    public void WeekDayWithoutBreakAndNightSupplementLateHours() {
        WorkDay workDay = new WorkDay(new String[]{"17:45", "23:59", ""}, SupplementType.Weekday, user);
        workDay.calculateSalary();
        assertEquals(986.339, workDay.getSalary(), 0.2);
    }

    @Test 
    public void WeekDayWithBreakAndNightSupplementLateHours() {
        WorkDay workDay = new WorkDay(new String[]{"16:00", "23:00", "", "23:00", "23:30", "b", "23:30", "23:59", ""}, SupplementType.Weekday, user);
        workDay.calculateSalary();
        assertEquals(1127.898, workDay.getSalary(), 0.2);
    }


    @Test
    public void WeekDayWithoutBreakAndNightSupplementEarlyHours() {
        WorkDay workDay = new WorkDay(new String[]{"04:00", "14:00", ""}, SupplementType.Weekday, user);
        workDay.calculateSalary();
        assertEquals(1366.2, workDay.getSalary(), 0.2);
    }

    @Test
    public void WeekDayWithBreakAndNightSupplementEarlyHours() {
        WorkDay workDay = new WorkDay(new String[]{"04:00", "05:30", "", "05:30", "06:00", "b", "06:00", "10:00", ""}, SupplementType.Weekday, user);
        workDay.calculateSalary();
        assertEquals(766.99, workDay.getSalary(), 0.2);
    }

    @Test
    public void SaturdayWithoutBreakAndSupplement() {
        WorkDay workDay = new WorkDay(new String[]{"12:00", "20:00", ""}, SupplementType.Saturday, user);
        workDay.calculateSalary();
        assertEquals(1285.64, workDay.getSalary(), 0.2);
    }

    @Test
    public void SaturdayWithBreakAndSupplement() {
        WorkDay workDay = new WorkDay(new String[]{"14:00", "16:00", "", "16:00", "16:30", "b", "16:30", "20:00", ""}, SupplementType.Sunday, user);
        workDay.calculateSalary();
        assertEquals(1028.39, workDay.getSalary(), 0.2);
    }

    @Test
    public void SundayWithoutBreakAndSupplement()  {
        WorkDay workDay = new WorkDay(new String[]{"13:00", "21:00", ""}, SupplementType.Sunday, user);
        workDay.calculateSalary();
        assertEquals(1495.84, workDay.getSalary(), 0.2);
    }

    @Test
    public void SundayWithBreakAndSupplement() {
        WorkDay workDay = new WorkDay(new String[]{"13:00", "17:00", "", "17:00", "17:30", "b", "17:30", "21:00", ""}, SupplementType.Sunday, user);
        workDay.calculateSalary();
        assertEquals(1402.35, workDay.getSalary(), 0.2);
    }

    @Test
    public void TimeformatToHours_1600() {
        Double actual = WorkDay.timeformatToHours("16:00");
        assertEquals(16, actual);
    }

    @Test
    public void TimeformatToHours_2015() {
        Double actual = WorkDay.timeformatToHours("20:15");
        assertEquals(20.25, actual);
    }

    @Test
    public void TimeformatToHours_2130() {
        Double actual = WorkDay.timeformatToHours("21:30");
        assertEquals(21.5, actual);
    }

    @Test
    public void TimeformatToHours_2220() {
        Double actual = WorkDay.timeformatToHours("22:20");
        assertEquals(22.333, actual, 0.001);
    }

    @Test
    public void TimeformatToHours_2359() {
        Double actual = WorkDay.timeformatToHours("23:59");
        assertEquals(23.983, actual, 0.001);
    }
}
