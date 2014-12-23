package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.JsonView;
import core.mvc.ModelAndView;

public class ListAPIController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();
	private List<Question> questions;
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		questions = questionDao.findAll();
		
		ModelAndView mav = new ModelAndView(new JsonView());
		mav.addObject("questions", questions);
		return mav;
	}
}
