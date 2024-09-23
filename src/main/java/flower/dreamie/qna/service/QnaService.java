package flower.dreamie.qna.service;

import flower.dreamie.qna.entity.QnaList;
import flower.dreamie.qna.repository.QnaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QnaService {

    private final QnaRepository qnaRepository;

    public QnaService(QnaRepository qnaRepository) {
        this.qnaRepository = qnaRepository;
    }

    //모든 문의사항 조회
    public List<QnaList> getAllQnaList() {
        return qnaRepository.findAll();
    }

    //새로운 문의사항 저장
    public void saveQna(QnaList qnaList) {
        qnaRepository.save(qnaList);  // DB에 문의사항 저장
    }

    //id로 문의사항 조회
    public QnaList getQnaById(Long qusetion_id) {
        Optional<QnaList> qna = qnaRepository.findById(qusetion_id);  // DB에서 ID로 문의사항을 찾음
        return qna.orElseThrow(() -> new IllegalArgumentException("해당 문의사항이 존재하지 않습니다."));
    }

    //문의사항 삭제
    public void deleteQna(Long question_id) {
        qnaRepository.deleteById(question_id);  //해당 id의 문의사항 삭제
    }
}
