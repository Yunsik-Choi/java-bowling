package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;


public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();


    public void addAnswer(Answer answer) {
        answers.add(answer);
    }


    public DeleteHistories deleteAnswers(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for(Answer answer : answers){
            deleteHistories.add(answer.delete(loginUser));
        }

        return DeleteHistories.of(deleteHistories);
    }

    public boolean hasOtherUsersAnswer(User questionWriter) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(questionWriter));
    }
}
