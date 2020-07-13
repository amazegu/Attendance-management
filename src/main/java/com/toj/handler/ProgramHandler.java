package com.toj.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toj.pojo.Belong;
import com.toj.pojo.Employee;
import com.toj.pojo.Program;
import com.toj.pojo.Result;
import com.toj.service.BelongService;
import com.toj.service.EmployeeService;
import com.toj.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/ProgramHandler")
public class ProgramHandler {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProgramService programService;
    @Autowired
    BelongService belongService;

    //分页
    public void pageIn(Model model, List list) {
        PageInfo page = new PageInfo(list, 5);
        model.addAttribute("pageInfo", page);
    }

    // 跳转页面
    @RequestMapping("/manageProgram/{pn}")
    public String manageProgram(HttpServletRequest request,
                                @PathVariable(value = "pn") String pn, Model model) {
        int no = Integer.parseInt(pn);
        PageHelper.startPage(no, 10);
        List<Program> queryProgram = new ArrayList<Program>();
        queryProgram = programService.selectProgram(1, 10, null,null,null);
        pageIn(model, queryProgram);
        request.setAttribute("plist", queryProgram);
        List<Employee> employees = new ArrayList<>();
        employees = employeeService.selectEmployeeBySql(1, 100);
        request.setAttribute("employees", employees);
        return "admin/queryProgram";
    }

    // 查询
    @RequestMapping(value = "/query/{pn}", method = RequestMethod.GET)
    public String redirect(@RequestParam("pname") String pname, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, HttpServletRequest request,
                           @PathVariable(value = "pn") String pn, Model model) {

        int no = Integer.parseInt(pn);
        List<Program> queryProgram = new ArrayList<Program>();
        PageHelper.startPage(no, 10);
        request.setAttribute("pname", pname);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        queryProgram = programService.selectProgram(1,10,pname, startDate, endDate);
        pageIn(model, queryProgram);
        request.setAttribute("plist", queryProgram);
        return "admin/queryProgram";

    }

    // 新增跳转页面
    @RequestMapping("/addpro")
    public String adProgram() {
        return "admin/addProgram";
    }

    //新增
    @RequestMapping("/addProgram")
    public String addProgram(Program program, Model model) {
        if (programService.insertProgram(program) != 0) {
            model.addAttribute("program", program);
            model.addAttribute("msg", "操作に成功しました!");
            return "admin/adminFace";
        } else {
            model.addAttribute("msg", "操作に失敗しました!");
            return "admin/adminFace";
        }

    }

    // 修改跳转页面
    @RequestMapping(value = "/modityPro/{id}", method = RequestMethod.GET)
    public String modityPro(@PathVariable("id") int id, HttpServletRequest request) {
        List<Program> queryProgram = new ArrayList<Program>();
        queryProgram = programService.getById(1,10,id);
        request.setAttribute("queryProgram", queryProgram);
        return "admin/modityProgram";
    }

    //修改提交
    @RequestMapping("/modityProgram/{id}")
    public String modityProgram(@PathVariable("id") int id, Program program, HttpServletRequest request) {
        if (programService.updateProgram(program) != 0) {
            request.setAttribute("msg", "操作に成功しました!");
            return "admin/adminFace";
        } else {
            request.setAttribute("msg", "操作に失敗しました!");
            return "admin/adminFace";
        }
    }

    //获取项目人员
    @RequestMapping(value = "getProPerson", method = RequestMethod.POST)
    public void getProPerson(@RequestParam("pid") int pid, HttpServletResponse response) {
        Result result = new Result();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        List<Belong> belongs = new ArrayList<>();
        belongs = belongService.selectDecidePid(1,10,pid);
        List<Belong> belongList = new ArrayList<>();
        belongList = belongService.selectByPid(1,10,pid);
        Map<String, List<Belong>> map = new HashMap<>();
        map.put("inList",belongList);
        map.put("outList",belongs);
        result.setData(map);
        getResult(result,response);

    }

    //项目人员管理
    @RequestMapping(value = "personManage", method = RequestMethod.POST)
    public void personManage(@RequestParam(value = "employeelist", required = false) int[] ids, @RequestParam("pid") int pid, HttpServletResponse response) {
        response.setContentType("application/json; charset=UTF-8");
        Result result = new Result();
        List<Belong> belongList = belongService.selectByPid(1,10,pid);
        List<Integer> list = new ArrayList<>();
        int index = 0;
        //增加
        if ((ids == null || ids.length == 0) && belongList.size() != 0) {
            index = belongService.deleteByPid(pid);
        } else if ((ids == null || ids.length == 0) && belongList.size() == 0) {
            index = 0;
        } else {
            for (int eid : ids) {
                list.add(eid);
                if (belongService.selectByIds(eid, pid) == null) {
                    belongService.insert(eid, pid);
                    index++;
                } else {
                    continue;
                }
            }
            for (Belong belong : belongList) {
                if (!list.contains(belong.getEid())) {
                    belongService.deleteById(belong.getId());
                    index++;
                } else {
                    continue;
                }
            }
        }
        if (index > 0) {
            result.setMsg("変更に成功しました！");
        } else {
            result.setMsg("変更または変更に失敗しました！");
        }
        getResult(result,response);
    }

    //删除项目
    @RequestMapping(value = "/deletePro",method = RequestMethod.POST)
    public void deletePro(@RequestParam("pid") int pid,HttpServletResponse response){
        Result result = new Result();
        response.setContentType("application/json; charset=UTF-8");
        if(programService.deleteProgram(pid)!=0){
            belongService.deleteByPid(pid);
            result.setMsg("削除しました");
        }else{
            result.setMsg("削除に失敗しました");
        }
        getResult(result,response);
    }

    public void getResult(Result result,HttpServletResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().write(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
