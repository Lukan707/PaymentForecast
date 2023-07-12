package Application;

public class WorkDay {

    private String[] timeIntervals;
    private String workdayType;
    private User user;
    private Double salary;

    public WorkDay(String[] timeIntervals, String workdayType, User user) {
        this.timeIntervals = timeIntervals;
        this.workdayType = workdayType;
        this.user = user;
        this.salary = 0.0;
    }

    private Double timeformatToHours(String time) {
        String[] timeArray = time.split(":");
        return Integer.parseInt(timeArray[0]) + Integer.parseInt(timeArray[1]) / 60.0;
    }

    public void calculateSalary() {

        for (int i = 0; i < timeIntervals.length; i += 3) {
            if (!timeIntervals[i+2].equals("b")) {
                Double startTimeInHours = timeformatToHours(timeIntervals[i]);
                Double endTimeInHours = timeformatToHours(timeIntervals[i+1]);
                salary += user.hourlySalary * (endTimeInHours - startTimeInHours); 
        
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

                System.out.println(salary);
            }
            System.out.println(salary);
        }
    }

    public Double getSalary() { return salary; }
}