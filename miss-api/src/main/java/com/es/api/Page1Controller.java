package com.es.api;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.es.controller.BaseController;
import com.es.entity.Legend;
import com.mysi.service.face.LegendServiceFace;

@Controller
@RequestMapping(value = "/p1")
public class Page1Controller extends BaseController {

	@Inject
	private LegendServiceFace legendService;

	@RequestMapping("/lists/{user}/$")
	public @ResponseBody List<Legend> lists(HttpServletResponse response, @PathVariable String user) {
		doHead(response);
		return WordBankUtils.addFanYi(legendService.lists(0, 0), user);
	}

	@RequestMapping("/legend/{id}/{user}/$")
	public @ResponseBody Legend getById(HttpServletResponse response, @PathVariable Long id,
			@PathVariable String user) {
		doHead(response);
		return WordBankUtils.addFanYi(legendService.getById(id), user);
	}

	@RequestMapping("/flush/wordbank/$")
	public void flushwordbank(HttpServletResponse response) {
		doHead(response);
		WordBankUtils.flushWordBank();
	}

}
