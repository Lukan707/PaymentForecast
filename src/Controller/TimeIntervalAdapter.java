package Controller;

/*
 * This class provides functionality to adapt timeinterval as strings
 * to TimeIntervalObjects.
 */
public class TimeIntervalAdapter extends Adapter {
    public static TimeInterval AdaptTimeIntervalFromStringToObject(String startTime, String endTime, String intervalType) {
        boolean isBreak = false;
        if (intervalType.equals("b"))
            isBreak = true;
        return new TimeInterval(timeformatToSeconds(startTime), timeformatToSeconds(endTime), isBreak);
    }
}
