package TestGrupp.Model;

public class Score {
    private int score;
    private long startTime;

    public Score() {
        score = 0;
        startTime = System.currentTimeMillis();
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
        long elapsedTime = currentTime - startTime;
        int additionalScore = (int) (elapsedTime / 1000) / 30 * 30;
        score += additionalScore;
        startTime = currentTime - (elapsedTime % 30000); // Reset start time to the last 30-second mark
    }

    public void enemyEliminated() {
        addScore(30);
    }
    

}