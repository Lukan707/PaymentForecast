package Controller;

public class WorkDay {

    private Double startTimeInHours;
    private Double endTimeInHours;
    private Double breakTimeInHours;
    private String workdayType;
    private User user;
    private Double salary;

    public WorkDay(String startTime, String endTime, String breakTime, String workdayType, User user) {
        startTimeInHours = timeformatToHours(startTime);
        endTimeInHours = timeformatToHours(endTime);
        breakTimeInHours = timeformatToHours(breakTime);
        this.workdayType = workdayType;
        this.user = user;
    }

    private Double timeformatToHours(String time) {
        String[] timeArray = time.split(":");
        return Integer.parseInt(timeArray[0]) + Integer.parseInt(timeArray[1]) / 60.0;
    }

    public void calculateSalary() {
        salary = 0.0;

        // TODO: Change all implementations to follow first implementation in first if-statement

        salary = user.hourlySalary * (endTimeInHours - startTimeInHours - breakTimeInHours);
        
        for (Supplement s : user.supplements) {
            if (s.supplementType.equals(workdayType)) {
                Double supplementTime;
                if (endTimeInHours > s.startTimeInHours && startTimeInHours < s.endTimeInHours) {
                    System.out.println("Current used supplement is: " + s.supplementType + " " + s.supplementPay);
                    System.out.println("vSlut: " + endTimeInHours + ", tStart: " + s.startTimeInHours);
                    supplementTime = s.endTimeInHours - startTimeInHours;
                    if (s.endTimeInHours > endTimeInHours)
                        supplementTime -= s.endTimeInHours - endTimeInHours;
                    System.out.println("SupplementTime 1: " + supplementTime);
                    if (startTimeInHours < s.startTimeInHours)
                        supplementTime -= s.startTimeInHours - startTimeInHours;
                    System.out.println("SupplementTime 2: " + supplementTime);
                    salary += s.supplementPay * supplementTime;
                }
            }
        }
    }

    public Double getSalary() {
        return salary;
    }
}