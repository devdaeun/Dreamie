package flower.dreamie.notice.controller;

import flower.dreamie.login.entity.User;
import flower.dreamie.notice.entity.NoticeList;
import flower.dreamie.notice.service.NoticeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // 공지사항 조회
    @RequestMapping("/notice")
    public String list(Model model) {
        model.addAttribute("noticeList", noticeService.getAllNoticeList());
        return "notice/noticeList";
    }

    // 공지사항 작성하기
    @RequestMapping("/noticeForm")
    public String noticeForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/loginForm";
        }

        // 관리자 체크
        if (!user.getRole().equals("admin")) {
            return "redirect:/notice";
        }

        model.addAttribute("notice", new NoticeList());
        return "notice/noticeForm";
    }

    // 공지사항 저장
    @RequestMapping("/noticeSave")
    public String saveNotice(@ModelAttribute("notice") NoticeList noticeList, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || !"admin".equals(user.getRole())) {
            return "redirect:/notice";
        }

        // NoticeList에 User 객체를 설정
        noticeList.setUser(user);
        noticeList.setShow_type(NoticeList.ShowType.True);

        noticeService.saveNotice(noticeList);

        return "redirect:/notice";
    }

    // 공지사항 상세 조회
    @RequestMapping("/notice/{notice_id}")
    public String detail(@PathVariable("notice_id") Long notice_id, Model model, HttpSession session) {
        NoticeList notice = noticeService.getNoticeById(notice_id);
        User user = (User) session.getAttribute("user");

        model.addAttribute("notice", notice);
        if (user != null) {
            model.addAttribute("user", user);
        }

        return "notice/noticeDetail";
    }

    // 공지사항 수정하기
    @PostMapping("/notice/update")
    @ResponseBody
    public String updateNotice(@RequestParam("notice_id") Long noticeId,
                               @RequestParam("content") String content) {
        NoticeList updatedNotice = noticeService.updateNotice(noticeId, content);
        if (updatedNotice != null) {
            return "OK";
        }
        return "FAIL";
    }


}
