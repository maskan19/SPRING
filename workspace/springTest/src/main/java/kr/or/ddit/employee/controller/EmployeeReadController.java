package kr.or.ddit.employee.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.employee.service.IEmployeeService;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class EmployeeReadController {
	@Inject
	IEmployeeService service;
	@Inject
	private WebApplicationContext container;
	private ServletContext application;

	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}

	
	
	
	@RequestMapping("/employee/employeeList.do")
	public String listForHTML(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			,@ModelAttribute("searchVO") SearchVO searchVO
			, Model model) {
		PagingVO<EmployeeVO> pagingVO = listForAjax(currentPage, searchVO);

		model.addAttribute("pagingVO", pagingVO);

		return "employee/employeeList";
	}
	

	
	
	@RequestMapping(value = "/employee/employeeList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<EmployeeVO> listForAjax(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO) {
		PagingVO<EmployeeVO> pagingVO = new PagingVO<>(10, 10);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleSearch(searchVO);

		int totalRecord = service.retrieveEmpCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<EmployeeVO> boardList = service.retrieveEmpList(pagingVO);

		pagingVO.setDataList(boardList);

		return pagingVO;
	}

}
