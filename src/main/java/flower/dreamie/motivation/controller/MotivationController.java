package flower.dreamie.motivation.controller;

import flower.dreamie.motivation.service.MotivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MotivationController {

    @Autowired
    private MotivationService motivationService;

    @GetMapping(value = "/") // URL 경로에 따라 수정
    public String about(Model model) {
        String[] randomMotivation = motivationService.getRandomMotivation();
        model.addAttribute("randomMotivation", randomMotivation[0]); // 모델에 랜덤 한국어 명언 추가
        model.addAttribute("randomMotivationEn", randomMotivation[1]); // 모델에 랜덤 영어 명언 추가
        return "main"; // JSP 파일 이름
    }
}

