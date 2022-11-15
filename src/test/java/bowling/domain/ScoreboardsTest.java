package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.score.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoreboardsTest {

    private Name name;
    private Round round;
    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        this.name = new Name("cys");
        this.round = new Round(1);
        this.scoreboard = new Scoreboard(this.name);
    }

    @Test
    void isEndTurnTrue() {
        this.scoreboard.addScore(Score.of(7), this.round);
        this.scoreboard.addScore(Score.of(2), this.round);
        Scoreboards scoreboards = new Scoreboards();
        scoreboards.add(this.name, this.scoreboard);

        assertThat(scoreboards.isEndTurn(this.name, new Round(1))).isTrue();
    }

    @Test
    void isEndTurnStrikeTrue() {
        this.scoreboard.addScore(Score.of(10), this.round);
        Scoreboards scoreboards = new Scoreboards();
        scoreboards.add(this.name, this.scoreboard);

        assertThat(scoreboards.isEndTurn(this.name, new Round(1))).isTrue();
    }

    @Test
    void isNotEndFalse() {
        this.scoreboard.addScore(Score.of(8), this.round);
        Scoreboards scoreboards = new Scoreboards();
        scoreboards.add(this.name, this.scoreboard);

        assertThat(scoreboards.isEndTurn(this.name, new Round(1))).isFalse();
    }
}
