package bowling.domain;

import java.util.Objects;

public abstract class Frame {

    public static final int SCORE_STRIKE = 10;
    protected final Scores scores;
    protected final Chance chance;

    public Frame() {
        this.scores = new Scores();
        this.chance = new Chance(this.TotalChance());
    }

    protected abstract int TotalChance();

    protected abstract void validateScore(Frame frame);

    public void addScore(Score score) {
        if (!this.chance.isRemainChance()) {
            throw new IllegalArgumentException("남은 기회가 없습니다.");
        }
        this.scores.add(score);
        validateScore(this);
        this.minusChance();
    }

    public boolean isRemainChance() {
        return this.chance.isRemainChance();
    }

    public abstract void minusChance();

    public Scores scores() {
        return this.scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frame frame = (Frame) o;
        return Objects.equals(this.scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}
