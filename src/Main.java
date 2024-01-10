import java.util.ArrayList;

import Controller.Supplement;
import Controller.User;
import Controller.WorkDay;
import Controller.SupplementType;

public class Main {

    public static void main(String[] args) {
        
        ArrayList<Supplement> supplements = new ArrayList<>() {{
            add(new Supplement(29.0, 18.0, 23.0, SupplementType.Weekday));
            add(new Supplement(38.95, 23.0, 23.98, SupplementType.Weekday));
            add(new Supplement(38.95, 0.0, 6.0, SupplementType.Weekday));
            add(new Supplement(51.0, 15.0, 24.0, SupplementType.Saturday));
            add(new Supplement(58.15, 0.0, 24.0, SupplementType.Sunday));
        }};
        User user = new User(128.83, supplements);
        
        WorkDay workDay = new WorkDay(new String[]{"17:45", "23:59", ""}, SupplementType.Weekday, user);
        workDay.calculateSalary();
        System.out.println(workDay.getSalary());
    }
}