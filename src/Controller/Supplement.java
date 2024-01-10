package Controller;

/*
 * This class represents a supplement.
 * A supplement contians an amount, the hours which within it is valid
 * and a type.
 */
public class Supplement {
    public Double supplementPay;
    public Double startTimeInHours;
    public Double endTimeInHours;
    public SupplementType supplementType;

    public Supplement(Double supplementPay, Double startTimeInHours, Double endTimeInHours, SupplementType supplementType) {
        this.supplementPay = supplementPay;
        this.startTimeInHours = startTimeInHours;
        this.endTimeInHours = endTimeInHours;
        this.supplementType = supplementType;
    }
}
