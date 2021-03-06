package cn.jhc.myexam.server.domain;

import cn.jhc.myexam.server.repository.QuizRepository;
import cn.jhc.myexam.server.service.QuizService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Configurable
@Component
@RooDataOnDemand(entity = Quiz.class)
public class QuizDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Quiz> data;

	@Autowired
	private CategoryDataOnDemand categoryDataOnDemand;

	@Autowired
	QuizService quizService;

	@Autowired
	QuizRepository quizRepository;

	public Quiz getNewTransientQuiz(int index) {
		Quiz obj = new Quiz();
		setCategory(obj, index);
		setContent(obj, index);
		setInfo(obj, index);
		setName(obj, index);
		setTimeClose(obj, index);
		setTimeOpen(obj, index);
		return obj;
	}

	public void setCategory(Quiz obj, int index) {
		Category category = categoryDataOnDemand.getRandomCategory();
		obj.setCategory(category);
	}

	public void setContent(Quiz obj, int index) {
		String content = "content_" + index;
		if (content.length() > 1024) {
			content = content.substring(0, 1024);
		}
		obj.setContent(content);
	}

	public void setInfo(Quiz obj, int index) {
		String info = "info_" + index;
		if (info.length() > 2048) {
			info = info.substring(0, 2048);
		}
		obj.setInfo(info);
	}

	public void setName(Quiz obj, int index) {
		String name = "name_" + index;
		if (name.length() > 1024) {
			name = name.substring(0, 1024);
		}
		obj.setName(name);
	}

	public void setTimeClose(Quiz obj, int index) {
		Date timeClose = new GregorianCalendar(Calendar.getInstance().get(
				Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar
						.getInstance().get(Calendar.HOUR_OF_DAY), Calendar
						.getInstance().get(Calendar.MINUTE), Calendar
						.getInstance().get(Calendar.SECOND)
						+ new Double(Math.random() * 1000).intValue())
				.getTime();
		obj.setTimeClose(timeClose);
	}

	public void setTimeOpen(Quiz obj, int index) {
		Date timeOpen = new GregorianCalendar(Calendar.getInstance().get(
				Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar
						.getInstance().get(Calendar.HOUR_OF_DAY), Calendar
						.getInstance().get(Calendar.MINUTE), Calendar
						.getInstance().get(Calendar.SECOND)
						- new Double(Math.random() * 1000).intValue())
				.getTime();
		obj.setTimeOpen(timeOpen);
	}

	public Quiz getSpecificQuiz(int index) {
		init();
		if (index < 0) {
			index = 0;
		}
		if (index > (data.size() - 1)) {
			index = data.size() - 1;
		}
		Quiz obj = data.get(index);
		Long id = obj.getId();
		return quizService.findQuiz(id);
	}

	public Quiz getRandomQuiz() {
		init();
		Quiz obj = data.get(rnd.nextInt(data.size()));
		Long id = obj.getId();
		return quizService.findQuiz(id);
	}

	public boolean modifyQuiz(Quiz obj) {
		return false;
	}

	public void init() {
		int from = 0;
		int to = 10;
		data = quizService.findQuizEntries(from, to);
		if (data == null) {
			throw new IllegalStateException(
					"Find entries implementation for 'Quiz' illegally returned null");
		}
		if (!data.isEmpty()) {
			return;
		}

		data = new ArrayList<Quiz>();
		for (int i = 0; i < 10; i++) {
			Quiz obj = getNewTransientQuiz(i);
			try {
				quizService.saveQuiz(obj);
			} catch (final ConstraintViolationException e) {
				final StringBuilder msg = new StringBuilder();
				for (Iterator<ConstraintViolation<?>> iter = e
						.getConstraintViolations().iterator(); iter.hasNext();) {
					final ConstraintViolation<?> cv = iter.next();
					msg.append("[")
							.append(cv.getRootBean().getClass().getName())
							.append(".").append(cv.getPropertyPath())
							.append(": ").append(cv.getMessage())
							.append(" (invalid value = ")
							.append(cv.getInvalidValue()).append(")")
							.append("]");
				}
				throw new IllegalStateException(msg.toString(), e);
			}
			quizRepository.flush();
			data.add(obj);
		}
	}
}
