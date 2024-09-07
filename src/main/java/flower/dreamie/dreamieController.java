package flower.dreamie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class dreamieController {

    @RequestMapping(value = "/")
    public String main() {
        return "main";
    }
}
