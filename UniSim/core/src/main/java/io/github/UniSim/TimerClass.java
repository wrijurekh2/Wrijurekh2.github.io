package io.github.UniSim;

public class TimerClass {
    private long startTime;
    private long pauseTime;
    private long currRealTime;
    private int minutes;
    private boolean ispaused;

    public TimerClass(int minutes) {
        this.minutes = minutes;
        ispaused = false;
        startTime = System.currentTimeMillis();
    }

    public String updateRealTime() {
        if (!ispaused) {
            currRealTime = (minutes * 60) + (startTime - System.currentTimeMillis()) / 1000;
        }
        String output = (int) (currRealTime / 60) + ":" + (int) (currRealTime % 60);
        return output;
    }

    public void pause() {
        if (!ispaused) {
            pauseTime = System.currentTimeMillis();
            ispaused = true;
        }
    }

    public void resume() {
        if (ispaused) {
            startTime += System.currentTimeMillis() - pauseTime;
            ispaused = false;
        }
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
