package lukan.paymentforecast.DomainLogic;

/*
 * This class represents a supplement.
 * A supplement contians an amount, the hours which within it is valid
 * and a type.
 */
public class Supplement {
    public Double supplementPay;
    public int startTimeInSeconds;
    public int endTimeInSeconds;
    public SupplementType supplementType;

    public Supplement(Double supplementPay, int startTimeInSeconds, int endTimeInSeconds, SupplementType supplementType) {
        this.supplementPay = supplementPay;
        this.startTimeInSeconds = startTimeInSeconds;
        this.endTimeInSeconds = endTimeInSeconds;
        this.supplementType = supplementType;
    }
}
