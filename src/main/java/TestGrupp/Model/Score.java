package TestGrupp.Model;

public class Score {
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
    }

    public void removeScore(int scoreRemoved) {
        score -= scoreRemoved;
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
        }
    }
}