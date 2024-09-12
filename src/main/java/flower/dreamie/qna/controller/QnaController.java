package flower.dreamie.qna.controller;

import flower.dreamie.login.entity.User;
import flower.dreamie.qna.entity.QnaList;
import flower.dreamie.qna.service.QnaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

//        // 로그인된 사용자 정보가 없는 경우 로그인 페이지로 리다이렉트
//        if (user == null) {
//            return "redirect:/loginForm";
//        }

        // QnaList에 로그인된 사용자의 user_id 설정
        qnaList.setUser_id(user.getUser_id());

        // 기본적으로 show_type을 True로 설정 (필요시 form에서 값 받아 설정 가능)
        qnaList.setShow_type(QnaList.ShowType.True);  // show_type을 기본적으로 'True'로 설정

        // 문의사항 저장
        qnaService.saveQna(qnaList);

        // 저장 후 문의사항 목록 페이지로 리다이렉트
        return "redirect:/qna";
    }
}
