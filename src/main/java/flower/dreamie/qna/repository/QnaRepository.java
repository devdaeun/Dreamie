package flower.dreamie.qna.repository;

import flower.dreamie.qna.entity.QnaList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnaList, Long> {
}
