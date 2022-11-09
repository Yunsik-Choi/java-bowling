package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScoresTest {

    @Test
    void create() {
        Scores scores = new Scores(Score.of(1));

        assertThat(scores).isEqualTo(new Scores(Score.of(1)));
    }

    @Test
    void add() {
        Scores scores = new Scores();
        scores.add(Score.of(4));

        assertThat(scores).isEqualTo(new Scores(Score.of(4)));
    }

    @Test
    void isEmpty() {
        Scores emptyScores = new Scores();
        Scores notEmptyScores = new Scores();
        notEmptyScores.add(Score.of(4));

        Assertions.assertAll(() -> assertThat(emptyScores.isEmpty()).isTrue(),
                () -> assertThat(notEmptyScores.isEmpty()).isFalse());
    }

    @Test
    void first() {
        Scores scores = new Scores(1, 2, 3);

        assertThat(scores.first()).isEqualTo(Score.of(1));
    }

    @Test
    void second() {
        Scores scores = new Scores(1, 2, 3);

        assertThat(scores.second()).isEqualTo(Score.of(2));
    }

    @Test
    void third() {
        Scores scores = new Scores(1, 2, 3);

        assertThat(scores.third()).isEqualTo(Score.of(3));
    }

    @Test
    void sum() {
        Scores scores = new Scores(1, 2, 3);

        assertThat(scores.sum()).isEqualTo(6);
    }
}