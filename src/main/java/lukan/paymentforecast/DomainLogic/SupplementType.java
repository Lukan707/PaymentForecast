package lukan.paymentforecast.DomainLogic;

/* 
 * This enum represent the type of supplement
*/
public enum SupplementType {
    Weekday("Weekday"),
    Saturday("Saturday"),
    Sunday("Sunday"),
    Holiday("Holiday"),
    Seniority("Seniority"),
    Bonus("Bonus"),
    Extraordinary("Extraordinary");

    public final String label;

    private SupplementType(String label) {
        this.label = label;
    }
}
