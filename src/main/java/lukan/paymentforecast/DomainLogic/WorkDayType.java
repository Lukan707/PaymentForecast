package lukan.paymentforecast.DomainLogic;

/*
 * This enum represent the type of day
 */
public enum WorkDayType {
    Weekday("Weekday"),
    Saturday("Saturday"),
    Sunday("Sunday"),
    Holiday("Holiday");

    public final String label;

    private WorkDayType(String label) {
        this.label = label;
    }
}

