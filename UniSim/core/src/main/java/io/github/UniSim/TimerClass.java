package io.github.UniSim;

public class TimerClass {
    private long startTime;
    private long pauseTime;
    private long currRealTime;
    private int seconds;
    private boolean ispaused;

    //Initialises the timer
    public TimerClass(int minutes) {
        this.seconds = minutes * 60;
        ispaused = false;
        startTime = System.currentTimeMillis();
    }

    //Calculates the time passed since the start of the timer and adds onto the desired gameplay time creating a countdown effect
    public String updateRealTime() {
        if (!ispaused) {
            currRealTime = seconds + (startTime - System.currentTimeMillis()) / 1000;
        }
        String output = (int) (currRealTime / 60) + ":" + (int) (currRealTime % 60);
        return output;
    }

    //Pauses the game and saves the time of when it occured
    public void pause() {
        if (!ispaused) {
            pauseTime = System.currentTimeMillis();
            ispaused = true;
        }
    }

    //Resumes the timer by adding onto the current timer the time difference between pause and resume
    public void resume() {
        if (ispaused) {
            startTime += System.currentTimeMillis() - pauseTime;
            ispaused = false;
        }
    }

    //Getter method for the year in-game
    public int getGameYear() {
        int timePassed = (int) (seconds - currRealTime);
        int currGameYear = timePassed / 60;
        return (int) currGameYear;
    }

    //Getter method for the month in-game
    public int getGameMonth() {
        int timePassed = (int) (seconds - currRealTime);
        int currGameMonth = (timePassed / 5) % 12;
        return (int) currGameMonth;
    }
}
