package flower.dreamie.answer.controller;

import flower.dreamie.answer.entity.Answer;
import flower.dreamie.answer.service.AnswerService;
import flower.dreamie.login.entity.User;
import flower.dreamie.qna.entity.QnaList;
import flower.dreamie.qna.service.QnaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AnswerController {

    private final AnswerService answerService;
    private final QnaService qnaService;

    public AnswerController(AnswerService answerService, QnaService qnaService) {
        this.answerService = answerService;
        this.qnaService = qnaService;
    }

    // 답변 작성 - 관리자만 가능
    @PostMapping("/qna/{question_id}/answer")
    public String addAnswer(@PathVariable Long question_id,
                            @RequestParam String content, // 답변 내용만 받음
                            HttpSession session) {
        User user = (User) session.getAttribute("user");

        Answer answer = new Answer();
        answer.setQuestion_id(question_id); // 질문 ID 설정
        answer.setUser_id(user.getUser_id()); // 작성자 ID 설정
        answer.setContent(content); // 답변 내용 설정

        System.out.println("저장할 답변 내용: " + content); // 로그 확인

        answerService.saveAnswer(answer); // 답변 저장

        return "redirect:/qna/" + question_id; // 답변 작성 후 질문 상세 페이지로 리다이렉트
    }

    //답변 수정 페이지 이동
    @RequestMapping("/qna/{question_id}/answer/{answer_id}")
    public String editAnswer(@PathVariable Long question_id, @PathVariable("answer_id") Long answer_id, Model model, HttpSession session) {
        QnaList qna = qnaService.getQnaById(question_id);   //기존 문의사항 가져오기
        User user = (User) session.getAttribute("user");    // 현재 로그인된 사용자 정보

        Answer answer = answerService.getAnswerById(answer_id);

        model.addAttribute("answer", answer);
        model.addAttribute("qna", qna);
        model.addAttribute("user", user);

        return "qna/answerUpdate";
    }

    //답변 수정 처리
    @RequestMapping("/qna/{question_id}/answer/{answer_id}/update")
    public String updateAnswer(@PathVariable("question_id") Long question_id, @PathVariable("answer_id") Long answer_id, @ModelAttribute Answer answer, HttpSession session) {
        QnaList qna = qnaService.getQnaById(question_id);   //기존 문의사항 가져오기
        User user = (User) session.getAttribute("user");    // 현재 로그인된 사용자 정보

        Answer updateAnswer = answerService.getAnswerById(answer_id);

        //관리자만 답변 수정 가능
        updateAnswer.setContent(answer.getContent());

        answerService.saveAnswer(updateAnswer);

        return "redirect:/qna/" + question_id;
    }

    // 답변 삭제
    @RequestMapping("/qna/{question_id}/answer/{answer_id}/delete")
    public String deleteAnswer(@PathVariable("answer_id") Long answer_id) {
        Answer answer = answerService.getAnswerById(answer_id);
        answerService.deleteAnswer(answer_id);
        return "redirect:/qna/" + answer.getQuestion_id();
    }
}
