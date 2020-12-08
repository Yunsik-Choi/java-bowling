package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;


public class FrameTest {
    @Test
    @DisplayName("첫번째 투구에서 10개의 핀을 모두 쓰러트리면 스트라이크")
    public void strikeTest() {
        Frame frame = new Frame();
        frame.setKnockDownPins(10);
        assertThat(frame.getStatus()).isEqualTo("X");
    }

    @Test
    @DisplayName("한 프레임의 모든 투구에서 10개의 핀을 모두 쓰러트리지 못한 경우 점수만 표기")
    public void scoreTest() {
        Frame frame = new Frame();
        frame.setKnockDownPins(3);
        frame.setKnockDownPins(5);
        assertThat(frame.getStatus()).isEqualTo("3|5");
    }
}
