package com.toj.handler;

import com.toj.service.EmployeeService;
import com.toj.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fuzui
 * @date 2019年4月13日 下午5:32:39
 * 
 */
@Controller
@RequestMapping("/AjaxHandler")
public class AjaxHandler {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	ProgramService programService;

	@RequestMapping(value="/existUserName",method = RequestMethod.POST)
	public void existSid(@RequestParam("userName") String userName,HttpServletResponse response,HttpServletRequest request) throws IOException{
		System.out.println("用户名="+userName);
 
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out=null;
		
		out=response.getWriter();
		if(employeeService.getByUserName(userName) != null){
			out.println("ユーザ名は既に存在します");
		}else {
			out.println("ユーザ名は使えます");
		}
		out.flush();
		out.close();
		
	}

	//项目名
	@RequestMapping(value="/existPname",method = RequestMethod.POST)
	public void existPname(@RequestParam("pname") String pname,HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out=null;
		out=response.getWriter();
		if(programService.getByPname(1,10,pname) == null || programService.getByPname(1,10,pname).size()==0){
			out.println("プロジェクト名は使えます");
		}else {
			out.println("プロジェクト名は既に存在します");
		}
		out.flush();
		out.close();

	}
	
}
