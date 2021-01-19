package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

	// 필드
	// 생성자 - 스프링이 접근하므로 기본생성자를 활용

	// 메소드 getter/setter

	/** 메소드 일반**** 메소드 마다 기능 1개씩 -> 기능마다 url 부여 **/

	// 리스트
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("list");
		
		//dao를 통해 리스트를 가져옴
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList.toString());
		
		//model -> date를 보내는 방법 -> 담아 놓으면 된다.
		model.addAttribute("pList", personList);
				
		return "/WEB-INF/views/list.jsp";
	}

	// 등록폼
	@RequestMapping(value = "writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("writeForm");

		return "/WEB-INF/views/writeForm.jsp";
	}

	// 등록
	@RequestMapping(value ="write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp, @RequestParam("company") String company) {
		System.out.println("write");

		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo.toString());
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo);
		
		return "redirect:/phone/list";
	}
	// 수정폼 -> modifyForm

	// 수정 -> modify

	// 삭제 -> delete

}
