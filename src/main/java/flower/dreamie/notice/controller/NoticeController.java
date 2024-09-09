package flower.dreamie.notice.controller;

import flower.dreamie.notice.entity.Notice;
import flower.dreamie.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping
    public String getAllNotices(Model model) {
        List<Notice> notices = noticeService.getAllNotices();
        model.addAttribute("notices", notices);
        return "notice/notice";  // /WEB-INF/views/notice.jsp
    }

    @GetMapping("/form")
    public String showNoticeForm() {
        return "notice/noticeForm";  // /WEB-INF/views/noticeForm.jsp
    }

    @PostMapping
    public String createNotice(@ModelAttribute Notice notice) {
        noticeService.createNotice(notice);
        return "redirect:/notices";  // 공지사항 목록으로 리디렉션
    }
}
