package flower.dreamie.qna.controller;

import flower.dreamie.qna.service.QnaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QnaController {

    private final QnaService qnaService;

    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @RequestMapping("/qna")
    public String list(Model model) {
        model.addAttribute("qnaList", qnaService.getAllQnaList());
        return "qna/qnaList";
    }

    @RequestMapping("/qnaForm")
    public String qnaForm(Model model) {
        return "qna/qnaForm";
    }
}
