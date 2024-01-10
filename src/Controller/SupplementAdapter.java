package Controller;

public class SupplementAdapter extends Adapter {
    public static Supplement AdaptSupplementFromStringToObject(Double supplementPay, String startTime, String endTime, String type) {
        SupplementType supplementType = null;
        switch (type) {
            case "weekday":
                supplementType = SupplementType.Weekday;
                break;

            case "saturday":
                supplementType = SupplementType.Saturday;

            case "sunday":
                supplementType = SupplementType.Sunday;
                break;

            case "holiday":
                supplementType = SupplementType.Holiday;
                break;

            case "seniority":
                supplementType = SupplementType.Seniority;
                break;

            case "bonus":
                supplementType = SupplementType.Bonus;
                break;

            default:
                supplementType = SupplementType.Extraordinary;
                break;
        }
        return new Supplement(supplementPay, timeformatToSeconds(startTime), timeformatToSeconds(endTime), supplementType);
    }
}
