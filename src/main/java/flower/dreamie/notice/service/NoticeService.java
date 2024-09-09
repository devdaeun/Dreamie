package flower.dreamie.notice.service;

import flower.dreamie.notice.entity.Notice;
import flower.dreamie.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

}
