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

    //질문에 대한 답변 가져오기
    public List<Answer> getAnswerByQuestionId(Long question_id) {
        return answerRepository.findByQuestionId(question_id);
    }

    //답변 저장
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public Answer getAnswerById(Long answer_id) {
        return answerRepository.findById(answer_id).orElseThrow(() -> new IllegalArgumentException("해당 답변이 존재하지 않습니다."));
    }

    public void deleteAnswer(Long answer_id) {
        answerRepository.deleteById(answer_id);
    }
}
