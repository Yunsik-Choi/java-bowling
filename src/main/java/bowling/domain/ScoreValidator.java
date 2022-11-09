package bowling.domain;

public class ScoreValidator {

    public static final int SCORE_STRIKE = 10;

    private ScoreValidator() throws InstantiationException {
        throw new InstantiationException("ScoreValidator는 생성할 수 없습니다.");
    }

    public static void validate(Frame frame) {
        if (isNotNeedValidate(frame)) {
            return;
        }
        if (frame instanceof DefaultFrame) {
            validateDefaultFrame(frame.scores());
        }
        if (frame instanceof LastFrame) {
            validateLastFrame(frame.scores());
        }
    }

    private static boolean isNotNeedValidate(Frame frame) {
        return frame.scores().size() < 2;
    }

    private static void validateDefaultFrame(Scores scores) {
        if (scores.sum() > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateLastFrame(Scores scores) {
        if (scores.size() == 2) {
            validateLastFrameSecondTimeScore(scores);
            return;
        }
        if (scores.size() == 3) {
            validateLastFrameThirdTimeScore(scores);
            return;
        }
        throw new IllegalArgumentException();
    }

    private static void validateLastFrameSecondTimeScore(Scores scores) {
        if (scores.first().isStrike()) {
            return;
        }
        if (scores.sum() <= SCORE_STRIKE) {
            return;
        }
        throw new IllegalArgumentException();
    }

    private static void validateLastFrameThirdTimeScore(Scores scores) {
        if (scores.first().isStrike() && scores.second().isStrike()) {
            return;
        }
        if (Scores.sumScores(scores.first(), scores.second()) == SCORE_STRIKE) {
            return;
        }
        if (scores.first().isStrike()
                && Scores.sumScores(scores.second(), scores.third()) <= SCORE_STRIKE) {
            return;
        }
        throw new IllegalArgumentException();
    }
}
