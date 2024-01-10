package Controller;

/*
 * This class represent a day where a user is working.
 * It holds the hours worked, hourly salary, associated User and day of the week.
 * It contains method for calculating the salary of the day, and for getting the salary.
 * The methods are split, since the retrieval of the attribute happens more
 * frequently than calculating it.
*/
public class WorkDay {

    private String[] timeIntervals;
    private SupplementType workdayType;
    private User user;
    private Double salary;

    public WorkDay(String[] timeIntervals, SupplementType workdayType, User user) {
        this.timeIntervals = timeIntervals;
        this.workdayType = workdayType;
        this.user = user;
        this.salary = 0.0;
    }

    public void calculateSalary() {

        for (int i = 0; i < timeIntervals.length; i += 3) {
            
            if (!timeIntervals[i+2].equals("b")) {
                
                Double startTimeInHours = timeformatToHours(timeIntervals[i]);
                Double endTimeInHours = timeformatToHours(timeIntervals[i+1]);
                salary += user.hourlySalary * (endTimeInHours - startTimeInHours); 
        
                for (Supplement s : user.supplements) {
                    if (s.supplementType.equals(workdayType)) {
                        
                        if (endTimeInHours > s.startTimeInHours && startTimeInHours < s.endTimeInHours) {
                            Double supplementTime = s.endTimeInHours - startTimeInHours;
                            
                            if (s.endTimeInHours > endTimeInHours)
                                supplementTime -= s.endTimeInHours - endTimeInHours;

                            if (startTimeInHours < s.startTimeInHours)
                                supplementTime -= s.startTimeInHours - startTimeInHours;

                            salary += s.supplementPay * supplementTime;
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