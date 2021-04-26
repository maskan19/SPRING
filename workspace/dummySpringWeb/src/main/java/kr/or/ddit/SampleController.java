package kr.or.ddit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class SampleController {
	@RequestMapping(value="sample1.do", method = RequestMethod.GET)
	public String sample1(Model model) {
		model.addAttribute("data", "전달한 모델 ");
		return "sample/view";
	}
	
	@RequestMapping("sample2.do")
	public ModelAndView sample2() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", "전달한 모델");
		mav.setViewName("sample/view");
		return mav;
	}
}






