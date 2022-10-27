package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingTest {

    @DisplayName("투구하면 쓰러진 핀 수가 더해진 투구를 반환한다")
    @Test
    void bowl() {
        Bowling result = bowling().bowl(Pins.of(10));

        assertThat(result).isEqualTo(new Bowling(List.of(Pins.of(10))));
    }

    @DisplayName("투구가 스트라이크인지 여부를 반환한다")
    @ParameterizedTest
    @CsvSource(value = {
            "10,true",
            "9,false"
    })
    void isStrike(int fallenPins, boolean expected) {
        Bowling bowling = bowling().bowl(Pins.of(fallenPins));

        assertThat(bowling.isStrike()).isEqualTo(expected);
    }

    @DisplayName("투구가 스페어인지 여부를 반환한다")
    @ParameterizedTest
    @CsvSource(value = {
            "0,10,true",
            "8,2,true",
            "8,1,false"
    })
    void isSpare(int fallenPins1, int fallenPins2, boolean expected) {
        Bowling bowling = bowling()
                .bowl(Pins.of(fallenPins1))
                .bowl(Pins.of(fallenPins2));

        assertThat(bowling.isSpare()).isEqualTo(expected);
    }

    @DisplayName("스트라이크면 투구가 끝난다")
    @Test
    void finished_whenStrike() {
        Bowling bowling = bowling().bowl(Pins.of(10));

        assertThat(bowling.isFinished()).isTrue();
    }

    @DisplayName("모두 시도했을 때 투구가 끝난다")
    @Test
    void finished_whenTriesDone() {
        Bowling bowling = bowling()
                .bowl(Pins.of(1))
                .bowl(Pins.of(5));

        assertThat(bowling.isFinished()).isTrue();
    }

    @DisplayName("모두 시도하지 않았을 때 투구가 끝나지 않는다")
    @Test
    void notFinished_whenTriesNotDone() {
        Bowling bowling = bowling().bowl(Pins.of(1));

        assertThat(bowling.isFinished()).isFalse();
    }

    @DisplayName("투구 시도 횟수를 반환한다")
    @Test
    void tries() {
        Bowling bowling = bowling().bowl(Pins.of(1));

        assertThat(bowling.tries()).isEqualTo(1);
    }

    private Bowling bowling() {
        return Bowling.init();
    }
}