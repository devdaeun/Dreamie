package flower.dreamie.qna.controller;

import flower.dreamie.qna.entity.QnaList;
import flower.dreamie.qna.service.QnaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QnaController {

    private final QnaService qnaService;    //의존성 주입

    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    //문의사항 목록 조회
    @RequestMapping("/qna")
    public String list(Model model) {
        model.addAttribute("qnaList", qnaService.getAllQnaList());
        return "qna/qnaList";
    }

    //문의사항 작성하기
    @RequestMapping("/qnaForm")
    public String qnaForm(HttpSession session, Model model) {
        // 세션에서 로그인 정보 가져오기
        Long user_id = (Long) session.getAttribute("user_id");

        // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트
        if (user_id == null) {
            return "redirect:/loginForm";  // 로그인 페이지로 리다이렉트
        }

        model.addAttribute("qna", new QnaList());
        return "qna/qnaForm";
    }
}
