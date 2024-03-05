package rsm.third_task;

import java.time.Duration;
import java.time.LocalDateTime;

public class Report {
    private String name;
    private LocalDateTime timeToUpload;
    private boolean status;
    private static Duration totalTime;
    private static int counterSuccesses;
    private static int counterFailures;

    public static Duration getTotalTime() {
        return totalTime;
    }

    public static void setTotalTime(Duration totalTime) {
        Report.totalTime = totalTime;
    }

    public static int getCounterSuccesses() {
        return counterSuccesses;
    }

    public static void setCounterSuccesses(int counterSuccesses) {
        Report.counterSuccesses = counterSuccesses;
    }

    public static int getCounterFailures() {
        return counterFailures;
    }

    public static void setCounterFailures(int counterFailures) {
        Report.counterFailures = counterFailures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimeToUpload() {
        return timeToUpload;
    }

    public void setTimeToUpload(LocalDateTime timeToUpload) {
        this.timeToUpload = timeToUpload;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
