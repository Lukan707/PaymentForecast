package Controller;

/*
 * This class represents a timeinterval.
 * A workday consists of one or more timeintervals.
 */
public class TimeInterval {
    public int startTimeInSeconds;
    public int endTimeInSeconds;
    public boolean isBreak;

    public TimeInterval(int startTimeInSeconds, int endTimeInSeconds, boolean isBreak) {
        this.startTimeInSeconds = startTimeInSeconds;
        this.endTimeInSeconds = endTimeInSeconds;
        this.isBreak = isBreak;
    }
}
