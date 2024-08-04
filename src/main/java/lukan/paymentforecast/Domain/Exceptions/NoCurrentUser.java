package lukan.paymentforecast.Domain.Exceptions;

public class NoCurrentUser extends Exception {
    public NoCurrentUser(String errorMessage) {
        super(errorMessage);
    }
}
