package io.github.UniSim;

public class TimerClass {
    private long startTime;
    private long currRealTime;
    private int minutes;

    public TimerClass(int minutes) {
        this.minutes = minutes;
        startTime = System.currentTimeMillis();
    }

    public String updateRealTime() {
        currRealTime = (minutes * 60) + (startTime - System.currentTimeMillis()) / 1000;
        String output = (int) (currRealTime / 60) + ":" + (int) (currRealTime % 60);
        return output;
    }

    public int getGameYear() {
        int timePassed = (int) ((minutes * 60) - currRealTime);
        int currGameYear = timePassed / 60;
        return (int) currGameYear;
    }

    public int getGameMonth() {
        int timePassed = (int) ((minutes * 60) - currRealTime);
        int currGameMonth = (timePassed / 5) % 12;
        return (int) currGameMonth;
    }
}
