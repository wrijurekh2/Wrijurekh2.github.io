package io.github.UniSim;

public class TimerClass {
    private long startTime;
    private long currTime;
    private int minutes;

    public TimerClass(int minutes) {
        this.minutes = minutes;
        startTime = System.currentTimeMillis();
    }

    public int update() {
        currTime = (minutes * 60) + (startTime - System.currentTimeMillis()) / 1000;
        return (int) currTime;
    }
}
