package lukan.paymentforecast.Domain;

import lukan.paymentforecast.Domain.Exceptions.InvalidTimeSlotException;

public class TimeFormatParser {
    public static int timeformatToSeconds(String time) throws InvalidTimeSlotException {
        if (time.isEmpty()) {
            throw new InvalidTimeSlotException("The given string is empty");
        }
        
        String[] timeArray = time.split(":");

        Integer hours;
        Integer minutes;

        try {
            hours = Integer.parseInt(timeArray[0]);
            minutes = Integer.parseInt(timeArray[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTimeSlotException("Timeslot contians illegal characters");
        }

        if (hours < 0 || hours > 23) {
            throw new InvalidTimeSlotException("The given hours is not within a valid range (0 - 23)");
        }

        if (minutes < 0 || minutes > 59) {
            throw new InvalidTimeSlotException("The given minutes is not within a valid range (0 - 59)");
        }

        return hours * 3600 + minutes * 60;
    }
}
