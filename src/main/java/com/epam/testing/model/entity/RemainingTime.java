package com.epam.testing.model.entity;

public class RemainingTime {
    private final long hours;
    private final long minutes;
    private final long seconds;

    public RemainingTime(long hours, long minutes, long seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public long getHours() {
        return hours;
    }

    public long getMinutes() {
        return minutes;
    }

    public long getSeconds() {
        return seconds;
    }
}
