package flower.dreamie.qna.service;

import flower.dreamie.qna.entity.QnaList;
import flower.dreamie.qna.repository.QnaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
