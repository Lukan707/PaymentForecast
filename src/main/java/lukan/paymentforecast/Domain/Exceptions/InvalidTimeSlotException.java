package lukan.paymentforecast.Domain.Exceptions;

public class InvalidTimeSlotException extends Exception {
    public InvalidTimeSlotException(String errorMessage) {
        super(errorMessage);
    }
}
