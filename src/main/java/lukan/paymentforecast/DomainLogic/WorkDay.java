package lukan.paymentforecast.DomainLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * This class represent a day where a user is working.
 * It holds the hours worked, hourly salary, associated User and day of the week.
 * It contains method for calculating the salary of the day, and for getting the salary.
 * The methods are split, since the retrieval of the attribute happens more
 * frequently than calculating it.
*/
public class WorkDay {

    private List<TimeInterval> timeIntervals;
    private WorkDayType workdayType;
    private User user;
    private Double salary;
    private Date date;
    private List<SupplementType> supplements;

    public WorkDay(Date date, List<TimeInterval> timeIntervals, WorkDayType workdayType, User user) {
        this(date, timeIntervals, workdayType, user, new ArrayList<SupplementType>(), 0.0);
    }

    public WorkDay(Date date, List<TimeInterval> timeIntervals, WorkDayType workdayType, User user, List<SupplementType> supplements) {
        this(date, timeIntervals, workdayType, user, supplements, 0.0);
    }

    public WorkDay(Date date, List<TimeInterval> timeIntervals, WorkDayType workdayType, User user, Double bonus) {
        this(date, timeIntervals, workdayType, user, new ArrayList<SupplementType>(), bonus);
    }

    public WorkDay(Date date, List<TimeInterval> timeIntervals, WorkDayType workdayType, User user, List<SupplementType> supplements, Double bonus) {
        this.date = date;
        this.timeIntervals = timeIntervals;
        this.workdayType = workdayType;
        this.user = user;
        this.salary = bonus;
        this.supplements = supplements;
    }

    public void calculateSalary() {

        for (TimeInterval interval : timeIntervals) {
            
            if (!interval.isBreak) {
                
                // Applying every interval, that isn't a break, using the salary and if 0< sentiority supplement times the interval in hours (converted from seconds)
                salary += (user.hourlySalary + user.senioritySupplement) * ((interval.endTimeInSeconds - interval.startTimeInSeconds) / 3600.0); 
                
                for (Supplement s : user.supplements) {
                    
                    // Applying the supplement given the parameters (pay, start- and end-time) if it matches the current type of day, or is specified in the workday
                    if (s.supplementType.label.equals(workdayType.label) || this.supplements.contains(s.supplementType)) {

                        // If the current interval ends after the supplement starts and the interval starts before the supplement ends the supplement is applied further
                        if (interval.endTimeInSeconds > s.startTimeInSeconds && interval.startTimeInSeconds < s.endTimeInSeconds) {
                            int supplementTimeInSeconds = interval.endTimeInSeconds - interval.startTimeInSeconds;
                            
                            // If the interval ends before the specified end time of the supplement, the last unused hours of the supplement is subtracted
                            if (interval.endTimeInSeconds > s.endTimeInSeconds)
                                supplementTimeInSeconds -= interval.endTimeInSeconds - s.endTimeInSeconds;

                            // If the interval starts after the specified start time of the supplement, the first unused hours of the supplement is subtracted
                            if (interval.startTimeInSeconds < s.startTimeInSeconds)
                                supplementTimeInSeconds -= s.startTimeInSeconds - interval.startTimeInSeconds;

                            // Apply the used time of the supplement in hours times the supplement salary to the salary of the workday
                            salary += s.supplementSalary * (supplementTimeInSeconds / 3600.0);
                        }
                    }
                }
            }
        }
    }
    public Double getSalary() { return salary; }
    public Date getDate() { return date; }
    public User getUser() { return user; }
    public WorkDayType getType() { return workdayType; };
}