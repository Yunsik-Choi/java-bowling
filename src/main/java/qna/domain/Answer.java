package qna.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

@Entity
public class Answer extends AbstractEntity {
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    @Lob
    private String contents;

    private boolean deleted = false;

    public Answer() {
    }

    public Answer(User writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, User writer, Question question, String contents) {
        super(id);

        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public DeleteHistory delete(User user) {
        validateWriter(user);
        setDeleted(true);
        return createAnswerDeleteHistory();
    }

    private void validateWriter(User user) {
        if (!isOwner(user)) {
            throw new CannotDeleteException("답변은 답변자만 지울 수 있습니다.");
        }
    }

    public boolean isOwner(User writer) {
        return this.writer.equals(writer);
    }

    public Answer setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    private DeleteHistory createAnswerDeleteHistory() {
        return new DeleteHistory(ContentType.ANSWER, this.getId(), this.writer, LocalDateTime.now());
    }

    public boolean isDeleted() {
        return deleted;
    }

    public User getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
