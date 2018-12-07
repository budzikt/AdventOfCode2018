package days.four;

import java.time.LocalDate;

public class GuardLog {
    public LocalDate date;
    public int minutesSleep;
    public int minutesAwake;

    GuardLog(LocalDate date, int seep) {
        this.date = date;
        this.minutesSleep = seep;
    }
}
