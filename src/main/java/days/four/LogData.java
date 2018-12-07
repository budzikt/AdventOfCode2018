package days.four;


import java.time.LocalDateTime;

public class LogData {
    public Integer id;
    public LogAction action;
    public LocalDateTime date;

    public LogData(Integer id, LogAction action, LocalDateTime time){
        this.id = id;
        this.action = action;
        this.date = time;
    }
}
