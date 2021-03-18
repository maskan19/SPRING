package kr.or.ddit.servlet03.view;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

// functionInterface가 가능해짐
public abstract class AbstractView {
	public abstract void mergeModelAndView(Object target, HttpServletResponse resp) throws IOException;
}
