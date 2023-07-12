package Application;

public class Supplement {
    public Double supplementPay;
    public Double startTimeInHours;
    public Double endTimeInHours;
    public String supplementType;

    public Supplement(Double supplementPay, Double startTimeInHours, Double endTimeInHours, String supplementType) {
        this.supplementPay = supplementPay;
        this.startTimeInHours = startTimeInHours;
        this.endTimeInHours = endTimeInHours;
        this.supplementType = supplementType;
    }
}
