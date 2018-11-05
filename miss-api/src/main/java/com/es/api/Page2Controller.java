package com.es.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.es.controller.BaseController;
import com.es.model.WordBank;
import com.es.model.WordBankForShow;
import com.mysi.service.face.WordServiceFace;

@Controller
@RequestMapping(value = "/p2")
public class Page2Controller extends BaseController {

	@Inject
	private WordServiceFace wordService;
	
	@RequestMapping("/words/{user}/$")
	public @ResponseBody List<WordBankForShow> lists(HttpServletResponse response, @PathVariable String user) {
		doHead(response);
		List<WordBankForShow> result = new ArrayList<WordBankForShow>();
		List<WordBank> wbs = wordService.findByUser(user);
		Collections.sort(wbs, new Comparator<WordBank>() {
			@Override
			public int compare(WordBank o1, WordBank o2) {
				return o1.getSzm() - o2.getSzm();
			}
		});
		char szm = 0;
		for (WordBank wb : wbs) {
			if (wb.getSzm() != szm) {
				szm = wb.getSzm();
				result.add(new WordBankForShow(1, wb.getSzm() + "", wb.getSzm() + ""));
			}
			result.add(new WordBankForShow(2, wb.getDanCi(), wb.getDanCi() + " " + wb.getFanYi()));
		}
		return result;
	}

	@RequestMapping("/word/delete/{user}/{danci}/$")
	public @ResponseBody String delete(HttpServletResponse response, @PathVariable String user,
			@PathVariable String danci) {
		doHead(response);
		wordService.delete(user,danci);
		return "success";
	}

}
