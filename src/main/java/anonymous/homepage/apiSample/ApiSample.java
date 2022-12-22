package anonymous.homepage.apiSample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apiSample")
public class ApiSample {

    @GetMapping("/postcode.do")
    public String postcode() {
        return "apiSample/postcode";
    }

    @GetMapping("/map.do")
    public String map() {
        return "apiSample/map";
    }

}
