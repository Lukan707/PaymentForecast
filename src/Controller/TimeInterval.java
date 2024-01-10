package Controller;

public class TimeInterval {
    public String startTime;
    public String endTime;
    public IntervalType type;

    public TimeInterval(String startTime, String endTime, IntervalType type) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }
}
