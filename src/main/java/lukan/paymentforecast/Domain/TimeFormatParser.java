package lukan.paymentforecast.Domain;

import lukan.paymentforecast.Domain.Exceptions.InvalidTimeSlotException;

public class TimeFormatParser {
    public static int timeformatToSeconds(String time) throws InvalidTimeSlotException {
        String[] timeArray = time.split(":");

        Integer hours = Integer.parseInt(timeArray[0]);
        Integer minutes = Integer.parseInt(timeArray[1]);
        
        if (hours < 0 || hours > 23) {
            throw new InvalidTimeSlotException("The given hours is not within a valid range (0 - 23)");
        }

        if (minutes < 0 || minutes > 59) {
            throw new InvalidTimeSlotException("The given minutes is not within a valid range (0 - 59)");
        }

        return hours * 3600 + minutes * 60;
    }
}
