package lukan.paymentforecast.DomainLogic;

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
    private SupplementType workdayType;
    private User user;
    private Double salary;
    private Date date;

    public WorkDay(Date date, List<TimeInterval> timeIntervals, SupplementType workdayType, User user) {
        this.date = date;
        this.timeIntervals = timeIntervals;
        this.workdayType = workdayType;
        this.user = user;
        this.salary = 0.0;
    }

    public void calculateSalary() {

        for (TimeInterval interval : timeIntervals) {
            
            if (!interval.isBreak) {
                
                salary += user.hourlySalary * ((interval.endTimeInSeconds - interval.startTimeInSeconds) / 3600.0); 
        
                for (Supplement s : user.supplements) {
                    if (s.supplementType.equals(workdayType)) {

                        if (interval.endTimeInSeconds > s.startTimeInSeconds && interval.startTimeInSeconds < s.endTimeInSeconds) {
                            int supplementTimeInSeconds = interval.endTimeInSeconds - interval.startTimeInSeconds;
                            
                            System.out.println("SupplementType: " + s.supplementType + ", with pay: " + s.supplementSalary + " is applied.");

                            if (interval.endTimeInSeconds > s.endTimeInSeconds)
                                supplementTimeInSeconds -= interval.endTimeInSeconds - s.endTimeInSeconds;

                            if (interval.startTimeInSeconds < s.startTimeInSeconds)
                                supplementTimeInSeconds -= s.startTimeInSeconds - interval.startTimeInSeconds;

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
    public SupplementType getType() { return workdayType; };
}