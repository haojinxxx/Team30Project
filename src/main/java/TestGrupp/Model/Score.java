package TestGrupp.Model;

import java.util.Observable;

public class Score extends Observable {
    private int score;
    private long startTime;
    private long lastUpdateTime;

    public Score() {
        score = 0;
        startTime = System.currentTimeMillis();
        lastUpdateTime = startTime;
    }

    public void addScore(int scoreAdded) {
        score += scoreAdded;
        setChanged();
        notifyObservers();
    }

    public void removeScore(int scoreRemoved) {
        score -= scoreRemoved;
        setChanged();
        notifyObservers();
    }

    public int getScore() {
        return score;
    }

    public void updateScoreBasedOnTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastUpdateTime;
        if (elapsedTime >= 1000) {
            int secondsPassed = (int) (elapsedTime / 1000);
            score += secondsPassed * 10;
            lastUpdateTime += secondsPassed * 1000;
            setChanged();
            notifyObservers();
        }
    }
}