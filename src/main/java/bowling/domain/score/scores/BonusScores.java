package bowling.domain.score.scores;

import bowling.domain.score.Score;

public class BonusScores extends Scores {
    private static final int SPARE_BONUS_SIZE = 1;
    private static final int STRIKE_BONUS_SIZE = 2;

    @Override
    public void add(Score score) {
        this.scores.add(score);
    }

    @Override
    public boolean isNotEndScore(Scores scores) {
        if (scores.isStrike()) {
            return !this.isSizeEqual(STRIKE_BONUS_SIZE);
        }
        if (scores.isSpare()) {
            return !this.isSizeEqual(SPARE_BONUS_SIZE);
        }
        return false;
    }

    @Override
    public boolean isChanceMinusTwo() {
        throw new IllegalArgumentException();
    }
}
