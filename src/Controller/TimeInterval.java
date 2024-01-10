package Controller;

/*
 * This class represents a timeinterval.
 * A workday consists of one or more timeintervals.
 */
public class TimeInterval {
    public String startTime;
    public String endTime;
    public boolean isBreak;

    public TimeInterval(String startTime, String endTime, boolean isBreak) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBreak = isBreak;
    }
}
