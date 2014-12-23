package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import core.mvc.AbstractController;
import core.mvc.JsonView;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class AddAnswerController extends AbstractController {
	private AnswerDao answerDao = new AnswerDao();
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		String contents = ServletRequestUtils.getStringParameter(request, "contents");
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		
		Answer answer = new Answer(writer, contents, questionId);
		answerDao.insert(answer);
		questionDao.updateCountOfComment(questionId);
		
		ModelAndView mav = new ModelAndView(new JsonView());
		return mav;
	}
}
