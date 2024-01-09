import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Controller.Supplement;
import Controller.SupplementType;
import Controller.User;
import Controller.WorkDay;

public class UnitTests {

    User user;

    @BeforeEach
    public void Setup() {
        ArrayList<Supplement> supplements = new ArrayList<>() {{
            add(new Supplement(29.0, 18.0, 23.0, SupplementType.Weekday));
            add(new Supplement(38.95, 23.0, 23.59, SupplementType.Weekday));
            add(new Supplement(38.95, 0.0, 6.0, SupplementType.Weekday));
            add(new Supplement(51.0, 15.0, 24.0, SupplementType.Saturday));
            add(new Supplement(58.15, 0.0, 24.0, SupplementType.Sunday));
        }};
        user = new User(128.83, supplements);
    }

    @AfterEach void tearDown() {
        user = null;
    }

    @Test
    public void WeekDayWithBreakAndNoSupplement() {
        WorkDay workDay = new WorkDay("08:00", "16:00", "01:00", SupplementType.Weekday, user);
        workDay.calculateSalary();
        assertEquals(901.81, workDay.getSalary(), 0.1);
    }

    @Test
    public void WeekDayWithoutBreakAndEveningSupplement() {
        WorkDay workDay = new WorkDay("17:45", "21:15", "00:00", SupplementType.Weekday, user);
        workDay.calculateSalary();
        assertEquals(545.16, workDay.getSalary(), 0.1);
    }

    @Test
    public void SaturdatWithBreakAndSupplement() {
        WorkDay workDay = new WorkDay("16:45", "21:15", "00:30", SupplementType.Saturday, user);
        workDay.calculateSalary();
        assertEquals(719.32, workDay.getSalary(), 0.1);
    }

    @Test
    public void WeekDayWithBreakAndEveningSupplement() {
        WorkDay workDay = new WorkDay("14:00", "21:15", "00:30", SupplementType.Weekday, user);
        workDay.calculateSalary();
        assertEquals(719.32, workDay.getSalary(), 0.1);
    }

    @Test
    public void WeekDayWithoutBreakAndNightSupplementLateHours() {
        WorkDay workDay = new WorkDay("17:45", "23:59", "00:00", SupplementType.Weekday, user);
        workDay.calculateSalary();
        assertEquals(986.35, workDay.getSalary(), 0.1);
    }

    @Test 
    public void WeekDayWithBreakAndNightSupplementLateHours() {

    }


    @Test
    public void WeekDayWithoutBreakAndNightSupplementEarlyHours() {
        WorkDay workDay = new WorkDay("04:00", "14:00", "00:00", SupplementType.Weekday, user);
        workDay.calculateSalary();
        assertEquals(1366.2, workDay.getSalary(), 0.1);
    }

    @Test
    public void WeekDayWithBreakAndNightSupplementEarlyHours() {

    }

    @Test
    public void SaturdayWithoutBreakAndSupplement() {
        WorkDay workDay = new WorkDay("12:00", "20:00", "00:00", SupplementType.Saturday, user);
        workDay.calculateSalary();
        assertEquals(1285.64, workDay.getSalary(), 0.1);
    }

    @Test
    public void SaturdayWithBreakAndSupplement() {

    }

    @Test
    public void SundayWithoutBreakAndSupplement()  {
        WorkDay workDay = new WorkDay("13:00", "21:00", "00:00", SupplementType.Sunday, user);
        workDay.calculateSalary();
        assertEquals(1495.84, workDay.getSalary(), 0.1);
    }

    @Test
    public void SundayWithBreakAndSupplement() {

    }
}
