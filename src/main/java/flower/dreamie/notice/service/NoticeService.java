package flower.dreamie.notice.service;

import flower.dreamie.notice.entity.NoticeList;
import flower.dreamie.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {this.noticeRepository = noticeRepository; }

    //  공지사항 조회
    public List<NoticeList> getAllNoticeList() {
        return noticeRepository.findAll();
    }

    // 공지사항 저장
    public void saveNotice(NoticeList noticeList) { noticeRepository.save(noticeList);}

    // 공지사항 상세조회
    public NoticeList getNoticeById(Long notice_id) {
        Optional<NoticeList> notice = noticeRepository.findById(notice_id);
        return  notice.orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다."));
    }

    // 공지사항 수정
    public NoticeList updateNotice(Long notice_id, String content) {
        NoticeList notice = getNoticeById(notice_id);
        if (notice != null) {
            notice.setContent(content);
            saveNotice(notice);
        }
        return notice;
    }

    // 공지사항 삭제
    public void deleteNotice(Long notice_id) throws Exception {
        try {
            noticeRepository.deleteById(notice_id);
        } catch (Exception e) {
            throw new Exception("삭제 중 오류 발생", e);
        }
    }
}