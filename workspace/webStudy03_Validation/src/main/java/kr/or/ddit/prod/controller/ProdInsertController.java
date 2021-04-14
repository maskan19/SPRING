package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdInsertController {
	private IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance();

	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(null);
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}

	@RequestMapping("/prod/prodInsert.do")
	public String form(HttpServletRequest req) {
		addAttribute(req);

		return "prod/prodForm";
	}

	@RequestMapping(value = "/prod/prodInsert.do", method = RequestMethod.POST)
	public String process(@ModelAttribute("prod") ProdVO prod, @RequestPart(value="prod_image", required=false) MultipartFile prod_image,
			HttpServletRequest req) throws IOException {
		addAttribute(req);

		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		//파일 저장
//		EventDrivenDevelopment/TestDrivenDevelopment 테스트에서 예상하는 결과대로 로직을 짜는 것
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(req.getServletContext().getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
		
		if (prod_image!=null &&!prod_image.isEmpty()) {
			prod_image.saveTo(saveFolder);
			prod.setProd_img(prod_image.getUniqueSaveName());
		}

		boolean valid = new CommonValidator<ProdVO>().validate(prod, errors, InsertGroup.class);

		String view = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.createProd(prod);
			if (ServiceResult.OK.equals(result)) {
				view = "redirect:/prod/prodView.do?what=" + prod.getProd_id();
			} else {
				message = "서버 오류";
				view = "prod/prodForm";
			}
		} else {
			view = "prod/prodForm";
		}

		req.setAttribute("message", message);

		return view;
	}

}
