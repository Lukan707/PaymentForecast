package Controller;

import java.util.ArrayList;

/*
 * This class represent a day where a user is working.
 * It holds the hours worked, hourly salary, associated User and day of the week.
 * It contains method for calculating the salary of the day, and for getting the salary.
 * The methods are split, since the retrieval of the attribute happens more
 * frequently than calculating it.
*/
public class WorkDay {

    private ArrayList<TimeInterval> timeIntervals;
    private SupplementType workdayType;
    private User user;
    private Double salary;

    public WorkDay(ArrayList<TimeInterval> timeIntervals, SupplementType workdayType, User user) {
        this.timeIntervals = timeIntervals;
        this.workdayType = workdayType;
        this.user = user;
        this.salary = 0.0;
    }

    public void calculateSalary() {

        for (TimeInterval interval : timeIntervals) {
            
            if (!interval.isBreak) {
                
                salary += user.hourlySalary * ((interval.endTimeInSeconds - interval.startTimeInSeconds) / 60); 
        
                for (Supplement s : user.supplements) {
                    if (s.supplementType.equals(workdayType)) {
                        
                        if (interval.endTimeInSeconds > s.startTimeInSeconds && interval.startTimeInSeconds < s.endTimeInSeconds) {
                            int supplementTimeInSeconds = interval.endTimeInSeconds - interval.startTimeInSeconds;
                            
                            if (interval.endTimeInSeconds > s.endTimeInSeconds)
                                supplementTimeInSeconds -= interval.endTimeInSeconds - s.endTimeInSeconds;

                            if (interval.startTimeInSeconds < s.startTimeInSeconds)
                                supplementTimeInSeconds -= s.startTimeInSeconds - interval.startTimeInSeconds;

                            salary += s.supplementPay * (supplementTimeInSeconds / 60);
                        }
                    }
                }
            }
        }
    }

    public Double getSalary() { return salary; }

    public static Double timeformatToHours(String time) {
        String[] timeArray = time.split(":");
        return Integer.parseInt(timeArray[0]) + Integer.parseInt(timeArray[1]) / 60.0;
    }
}