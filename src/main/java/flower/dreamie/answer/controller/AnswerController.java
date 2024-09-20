package flower.dreamie.answer.controller;

import flower.dreamie.answer.entity.Answer;
import flower.dreamie.answer.service.AnswerService;
import flower.dreamie.login.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    // 답변 작성 - 관리자만 가능
    @PostMapping("/qna/{question_id}/answer")
    public String addAnswer(@PathVariable Long question_id,
                            @RequestParam String content, // 답변 내용만 받음
                            HttpSession session) {
        User user = (User) session.getAttribute("user");

        // 관리자인 경우에만 답변 작성 가능
        if (user != null && user.getRole().equals("관리자")) {
            Answer answer = new Answer();
            answer.setQuestion_id(question_id); // 질문 ID 설정
            answer.setUser_id(user.getUser_id()); // 작성자 ID 설정
            answer.setContent(content); // 답변 내용 설정

            answerService.saveAnswer(answer); // 답변 저장
        }

        return "redirect:/qna/" + question_id; // 답변 작성 후 질문 상세 페이지로 리다이렉트
    }
}
