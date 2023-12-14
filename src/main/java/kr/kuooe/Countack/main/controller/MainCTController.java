package kr.kuooe.Countack.main.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.annotations.Api;
import kr.kuooe.Countack.main.service.dto.CtClickCount;
import kr.kuooe.Countack.main.service.repository.ClickCountRepository;

@Api(value = "MainCTController")
@Controller
public class MainCTController {
	public Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private ClickCountRepository clickCountRepository;

    @RequestMapping("/Countack/")
	public String countackMain(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) {
		log.debug("countackMain() =====> Start");
		log.debug("countackMain() =====> End");
		return "Countack/Main";
	}
    
    @GetMapping("/clickFrog")
    public String clickFrog() {
        // 현재 사용자의 국가를 가져옴 (더미 데이터 사용)
        String country = "US";

        // 국가별 클릭 정보 가져오기
        Optional<CtClickCount> clickCountOptional = clickCountRepository.findById(country);

        if (clickCountOptional.isPresent()) {
            // 기존 정보가 있으면 업데이트
            CtClickCount clickCount = clickCountOptional.get();
            clickCount.setCountryClicks(clickCount.getCountryClicks() + 1);
            clickCountRepository.save(clickCount);
        } else {
            // 기존 정보가 없으면 새로 생성
            CtClickCount clickCount = new CtClickCount();
            clickCount.setCountryCode(country);
            clickCount.setCountryClicks(1);
            clickCountRepository.save(clickCount);
        }

        return "Countack/Main";
    }
}