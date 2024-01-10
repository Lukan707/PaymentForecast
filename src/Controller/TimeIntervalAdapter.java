package Controller;

/*
 * This class provides functionality to adapt timeinterval as strings
 * to TimeIntervalObjects.
 * This class follows the Adpater pattern.
 */
public class TimeIntervalAdapter {
    public static TimeInterval AdaptTimeIntervalFromStringToObject(String startTime, String endTime, boolean isBreak) {
        return new TimeInterval(timeformatToSeconds(startTime), timeformatToSeconds(endTime), isBreak);
    }

    public static int timeformatToSeconds(String time) {
        String[] timeArray = time.split(":");
        return Integer.parseInt(timeArray[0]) * 3600 + Integer.parseInt(timeArray[1]) * 60;
    }
}
