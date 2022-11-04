package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.UnAuthorizedException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question("question", "content").writeBy(UserTest.JAVAJIGI);
    }

    @DisplayName("사용자와 질문자가 아닌 경우 UnAuthorizedException 예외를 throw한다.")
    @Test
    void validate_writer() {
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI)).isInstanceOf(UnAuthorizedException.class);
    }

    @DisplayName("질문의 상태를 삭제 상태로 변경한다.")
    @Test
    void set_delete() {
        Assertions.assertAll(
                () -> assertThat(question.isDeleted()).isFalse(),
                () -> assertThat(question.setDeleted(true).isDeleted()).isTrue(),
                () -> assertThat(question.setDeleted(false).isDeleted()).isFalse()
        );
    }

    @DisplayName("모든 답변의 상태를 삭제로 변경한다.")
    @Test
    void set_deleteAllAnswers() {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, question, "answer1");
        question.addAnswer(answer1);
        Answer answer2 = new Answer(UserTest.JAVAJIGI, question, "answer2");
        question.addAnswer(answer2);

        question.deleteAllAnswers(UserTest.JAVAJIGI);

        Assertions.assertAll(
                () -> assertThat(answer1.isDeleted()).isTrue(),
                () -> assertThat(answer2.isDeleted()).isTrue()
        );
    }

    @DisplayName("답변 중 사용자가 답변자가 아닌 경우 UnAuthorizedException 예외를 throw한다.")
    @Test
    void set_deleteAllAnswers_contain_not_owner() {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, question, "answer1");
        question.addAnswer(answer1);
        Answer answer2 = new Answer(UserTest.SANJIGI, question, "answer2");
        question.addAnswer(answer2);

        assertThatThrownBy(() -> question.deleteAllAnswers(UserTest.JAVAJIGI))
                .isInstanceOf(UnAuthorizedException.class);
    }
}
