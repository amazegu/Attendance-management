package com.toj.handler;

import com.toj.pojo.Employee;
import com.toj.service.AdminService;
import com.toj.service.EmployeeService;
import com.toj.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/LoginHandler")
public class LoginHandler {
    @Autowired
	AdminService adminServiceImpl;
    @Autowired
	EmployeeService employeeService;
    @Autowired
    RecordService recordService;
    
  //管理员登录

    @RequestMapping("/adminlogin")
    public String loginStudent(@RequestParam("aname") String aname, @RequestParam("apassword") String apassword,HttpSession httpSession) {
        String n = null;
        n = adminServiceImpl.queryByNamePwd(aname,apassword);

        if (n != null && !"".equals(n)) {
            httpSession.setAttribute("aname", aname);
            return "admin/adminFace";
        } else {
            return "login";
        }

    }

    // 管理员退出登录
    @RequestMapping("/adminlogout")
    public ModelAndView adminLogout(HttpSession httpSession) {
        httpSession.removeAttribute("aname");
        return new ModelAndView(new RedirectView("/employee/index.jsp"));
    }
    
    
 // 学生登录
 	@RequestMapping("/employeelogin")
 	public ModelAndView loginEmployee(@RequestParam("userName") String userName, @RequestParam("password") String password,
 			Model model, HttpSession httpSession, HttpServletRequest httpRequest) {

 		Employee employee = new Employee();
 		employee = employeeService.getByUserName(userName);
 		if (employeeService.queryByNamePwd(userName, password) != null) {
            httpSession.setAttribute("id", employee.getId());
 			httpSession.setAttribute("userName", userName);
 			httpSession.setAttribute("name", employee.getName());
 			return new ModelAndView(new RedirectView("../employee/employeeFace.jsp"));
 		} else {
 			httpRequest.setAttribute("msg","アカウントまたはパスワードが違います。ログインに失敗しました。");
 			return new ModelAndView(new RedirectView("../fail.jsp"));
 		}

 	}
 	
 // 学生退出登录
 	@RequestMapping("/employeelogout")
 	public ModelAndView employeeLogout(HttpSession httpSession) {

        httpSession.removeAttribute("id");
 		httpSession.removeAttribute("userName");
 		httpSession.removeAttribute("name");
 		return new ModelAndView(new RedirectView("/employee/index.jsp"));
 	}



}
