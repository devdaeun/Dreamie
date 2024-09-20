package flower.dreamie.answer.service;

import flower.dreamie.answer.entity.Answer;
import flower.dreamie.answer.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private  final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAnswerByQuestionId(Long question_id) {
        return answerRepository.findByQuestionId(question_id);
    }

    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }
}
