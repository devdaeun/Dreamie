package flower.dreamie.qna.controller;

import flower.dreamie.answer.entity.Answer;
import flower.dreamie.answer.service.AnswerService;
import flower.dreamie.login.entity.User;
import flower.dreamie.qna.entity.QnaList;
import flower.dreamie.qna.service.QnaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class QnaController {

    private final QnaService qnaService;    //의존성 주입
    private final AnswerService answerService;

    public QnaController(QnaService qnaService, AnswerService answerService) {
        this.qnaService = qnaService;
        this.answerService = answerService;
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
        User user = (User) session.getAttribute("user");

        // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
        if (user == null) {
            return "redirect:/loginForm";
        }

        model.addAttribute("qna", new QnaList());
        return "qna/qnaForm";
    }

    // 문의사항 저장
    @RequestMapping("/qnaSave")
    public String saveQna(@ModelAttribute("qna") QnaList qnaList, HttpSession session) {
        // 세션에서 로그인된 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");

        // QnaList에 로그인된 사용자의 user_id 설정
        qnaList.setUser_id(user.getUser_id());

        // 기본적으로 show_type을 True로 설정 (필요시 form에서 값 받아 설정 가능)
        qnaList.setShow_type(QnaList.ShowType.valueOf(qnaList.getShow_type().name()));  // show_type을 기본적으로 'True'로 설정

        // 문의사항 저장
        qnaService.saveQna(qnaList);

        // 저장 후 문의사항 목록 페이지로 리다이렉트
        return "redirect:/qna";
    }

    //문의사항 상세조회
    @RequestMapping("/qna/{question_id}")
    public String detail(@PathVariable("question_id") Long question_id, Model model, HttpSession session) {
        QnaList qna = qnaService.getQnaById(question_id); // 해당 아이디로 문의사항 가져오기
        User user = (User) session.getAttribute("user");    //현재 로그인된 사용자 정보

        List<Answer> answer = answerService.getAnswerByQuestionId(question_id); // 해당 질문의 답변 리스트 가져오기

        model.addAttribute("qna", qna);  // 글 정보를 뷰로 전달
        model.addAttribute("answer", answer);   // 답변 모델에 추가
        if (user != null) {
            model.addAttribute("user", user);  // 사용자 정보를 뷰로 전달
        }

        return "qna/qnaDetail";  // 상세 페이지로 이동
    }

    //문의사항 수정 페이지로 이동
    @RequestMapping("/qna/{question_id}/edit")
    public String editForm(@PathVariable("question_id") Long question_id, Model model, HttpSession session) {
        QnaList qna = qnaService.getQnaById(question_id);   //해당 문의사항 가져오기
        User user = (User) session.getAttribute("user");    //현재 로그인된 사용자 정보

        model.addAttribute("qna", qna); //수정할 글 정보를 모델에 전달
        return "qna/qnaUpdate"; //수정 페이지로 이동
    }

    //문의사항 수정 처리
    @RequestMapping("/qna/{question_id}/update")
    public String updateQna(@PathVariable("question_id") Long question_id, @ModelAttribute("qna") QnaList qnaList, HttpSession session) {
        QnaList qna = qnaService.getQnaById(question_id);   //기존 문의사항 가져오기
        User user = (User) session.getAttribute("user");    // 현재 로그인된 사용자 정보

        //수정
        qna.setShow_type(QnaList.ShowType.valueOf(qnaList.getShow_type().name()));
        qna.setTitle(qnaList.getTitle());
        qna.setContent(qnaList.getContent());

        //문의사항 업데이트
        qnaService.saveQna((qna));

        return "redirect:/qna/" + question_id;  //수정 후 상세 페이지로 리다이렉트
    }

    //문의사항 삭제
    @RequestMapping("/qna/{question_id}/delete")
    public String deleteQna(@PathVariable("question_id") Long question_id, HttpSession session) {
        QnaList qna = qnaService.getQnaById(question_id);   // 삭제할 글 가져오기
        User user = (User) session.getAttribute("user");    // 현재 로그인된 사용자 정보

        // 글 작성자이거나 관리자일 경우에만 삭제 가능
        if (user == null || (!qna.getUser_id().equals(user.getUser_id()) && !user.getRole().equals("관리자"))) {
            return "redirect:/qna";  // 권한이 없으면 목록 페이지로 리다이렉트
        }

        qnaService.deleteQna(question_id);  // 서비스에서 삭제 처리
        return "redirect:/qna";  // 삭제 후 목록 페이지로 리다이렉트
    }
}
