package flower.dreamie.answer.repository;

import flower.dreamie.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
//    List<Answer> findByQuestion_id(Long question_id);
    @Query("SELECT a FROM Answer a WHERE a.question_id = :questionId")
    List<Answer> findByQuestionId(@Param("questionId") Long questionId);
}
