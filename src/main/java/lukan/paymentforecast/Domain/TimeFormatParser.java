package lukan.paymentforecast.Domain;

public class TimeFormatParser {
    public static int timeformatToSeconds(String time) {
        String[] timeArray = time.split(":");
        return Integer.parseInt(timeArray[0]) * 3600 + Integer.parseInt(timeArray[1]) * 60;
    }
}
