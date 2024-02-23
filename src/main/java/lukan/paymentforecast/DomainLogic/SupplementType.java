package lukan.paymentforecast.DomainLogic;

/* 
 * This enum represent the type of supplement
 * 
 * Some SupplementTypes is equal to WorkDayType enums.
*/
public enum SupplementType {
    Weekday("Weekday"),
    Saturday("Saturday"),
    Sunday("Sunday"),
    Holiday("Holiday"),
    Extraordinary("Extraordinary");

    public final String label;

    private SupplementType(String label) {
        this.label = label;
    }
}
