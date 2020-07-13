package com.toj.handler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toj.pojo.Employee;
import com.toj.pojo.Mission;
import com.toj.pojo.Program;
import com.toj.pojo.Record;
import com.toj.service.*;
import com.toj.utils.ExcelUtil;
import com.toj.utils.GetTime;
import com.toj.utils.IsHoliday;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/EmployeeHandler")
public class EmployeeHandler {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    RecordService recordService;
    @Autowired
    HolidayService holidayService;
    @Autowired
    ProgramService programService;
    @Autowired
    MissionService missionService;
    private Employee employee;

    // 跳转页面
    @RequestMapping(value = "/employeepage", method = RequestMethod.GET)
    public String employeePage() {
        return "employee/employeeFace";
    }

	/*// 跳转页面
	@RequestMapping("/employeeSign/{id}")
	public ModelAndView employeeSign(@PathVariable(value = "id") int id, Model model) {
		employee = employeeService.getById(id);
		model.addAttribute("userName", employee.getUserName());
		model.addAttribute("name", employee.getName());
		return new ModelAndView(new RedirectView("/employee/employee/signIn.jsp"));
	}

	// 打卡
	@RequestMapping("/signIn")
	public String signIn(Record record, Model model) {
		if (recordService.insertRecord(record) != 0) {
			model.addAttribute("record", record);
			return "employee/success";
		} else {
			return "employee/fail";
		}
	}*/

    //跳转页面
    @RequestMapping("/managerec/{name}/{pn}")
    public String manageRecord(HttpServletRequest request, @PathVariable(value = "pn") String pn,
                               @PathVariable(value = "name") String name, Model model) {
        int no = Integer.parseInt(pn);

        PageHelper.startPage(no, 10);
        List<Record> queryRecord = new ArrayList<Record>();
        queryRecord = recordService.getRecordByName(1, 100, name);
        pageIn(model, queryRecord);
        List list = getList(queryRecord);
        request.setAttribute("rlist", list);
        return "employee/queryRecord";
    }

    //组装数据
    public List getList(List<Record> queryRecord) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = format.format(new Date());
        String nowMonth = nowDate.substring(0, 7);
        IsHoliday isHoliday = new IsHoliday();
        List hList = holidayService.selectAll();
        Mission mission = null;
        List list = new ArrayList();
        for (Record r : queryRecord) {
            mission = missionService.selectMission(r.getEid(), r.getDate());
            Map map = new HashMap();
            map.put("id", r.getId());
            map.put("name", r.getName());
            map.put("date", r.getDate());
            map.put("startime", r.getStartime());
            map.put("endtime", r.getEndtime());
            map.put("worktime", r.getWorktime());
            map.put("rest", r.getRest());
            map.put("situation", r.getSituation());
            map.put("remarks", r.getRemarks());
            map.put("weekday", isHoliday.getWeek(r.getDate()));
            if (isHoliday.isHoliday(hList, r.getDate())) {
                map.put("isRest", "休️");
            }
            if (nowMonth.equals(r.getDate().substring(0, 7))) {
                map.put("inMonth", "1");
            } else {
                map.put("inMonth", "0");
            }
            if (mission == null) {
                map.put("design", "0.0");
                map.put("code", "0.0");
                map.put("study", "0.0");
                map.put("meeting", "0.0");
                map.put("test", "0.0");
            } else {
                map.put("design", mission.getDesign());
                map.put("code", mission.getCode());
                map.put("study", mission.getStudy());
                map.put("meeting", mission.getMeeting());
                map.put("test", mission.getTest());
            }
            list.add(map);
        }
        return list;
    }


    public void pageIn(Model model, List list) {
        PageInfo page = new PageInfo(list, 5);
        model.addAttribute("pageInfo", page);
    }

    // 查询
    @RequestMapping(value = "/queryRec/{name}/{pn}", method = RequestMethod.GET)
    public String redirectRec(@PathVariable(value = "name") String name, @RequestParam("s_date") String s_date, @RequestParam("e_date") String e_date, HttpServletRequest request,
                              @PathVariable(value = "pn") String pn, Model model) {

        int no = Integer.parseInt(pn);
        List<Record> recordList = new ArrayList<Record>();
        PageHelper.startPage(no, 10);
        request.setAttribute("s_date", s_date);
        request.setAttribute("e_date", e_date);
        //查询全部
        if (name == null && s_date == null && e_date == null) {
            System.out.println("------------------------------------------------------------------------------------------------");
            recordList = recordService.selectRecordBySql(1, 10);
            pageIn(model, recordList);
            List list = getList(recordList);
            request.setAttribute("rlist", list);
            return "employee/queryRecord";

            //根据条件查询
        } else {
            recordList = recordService.getRecordBy(1, 10, name, s_date, e_date);
            pageIn(model, recordList);
            List list = getList(recordList);
            request.setAttribute("rlist", list);
            return "employee/queryRecord";
        }
    }


    // 跳转页面
    @RequestMapping("/querySelf/{id}")
    public String modify(@PathVariable(value = "id") int id, Model model) {
        employee = employeeService.getById(id);
        Program program = new Program();
        program = programService.getByEid(id);
        if (program == null) {
            program.setName("研修");
        }
        model.addAttribute("employee", employee);
        model.addAttribute("program", program.getName());
        return "employee/selfInfo";
    }

    // 跳转页面
    @RequestMapping("/modifypassword/{id}")
    public ModelAndView teacherModi(@PathVariable(value = "id") int id, Model model) {

        return new ModelAndView(new RedirectView("/employee/employee/modityPw.jsp"));
    }

    // 修改
    @RequestMapping("/modifypw/{id}")
    public ModelAndView teacherModiPw(@PathVariable(value = "id") int id,
                                      @RequestParam("password") String password, Model model) {
        if (employeeService.modifyEmployeePwd(password, id) != 0) {
            return new ModelAndView(new RedirectView("../querySelf/{id}"));
        } else {
            model.addAttribute("msg", "操作に失敗しました!");
            return new ModelAndView(new RedirectView("employeeFace.jsp"));
        }

    }

    // 修改记录跳转
    @RequestMapping(value = "/modityRec/{id}", method = RequestMethod.GET)
    public String editRec(@PathVariable("id") int id, HttpServletRequest request) {

        List<Record> queryRecord = new ArrayList<Record>();
        queryRecord = recordService.getByRecordId(1, 10, id);
        Mission mission = missionService.selectMission(queryRecord.get(0).getEid(), queryRecord.get(0).getDate());
        request.setAttribute("recordList", queryRecord);
        request.setAttribute("mission", mission);
        return "employee/modiRecord";
    }

    // 修改记录
    @RequestMapping(value = "/modityReco/{id}", method = RequestMethod.GET)
    public String updateRecord(@PathVariable("id") int id, Record record, Mission mission, HttpServletRequest request) {
        Employee employee = employeeService.getByName(record.getName());
        record.setEid(employee.getId());
        if (recordService.modifyRecord(record) != 0) {
            if (missionService.updateMission(mission) != 0) {
                request.setAttribute("msg", "操作に成功しました!");
                return "employee/employeeFace";
            } else {
                request.setAttribute("msg", "操作に失敗しました!");
                return "employee/employeeFace";
            }
        } else {
            request.setAttribute("msg", "操作に失敗しました!");
            return "employee/employeeFace";
        }
    }

    //导出
    @RequestMapping("/export/{name}")
    public void export(@PathVariable("name") String name, @RequestParam("s_date") String s_date, @RequestParam("e_date") String e_date,
                       HttpServletRequest request, HttpServletResponse response) {
        List<Record> recordList = new ArrayList<Record>();
        request.setAttribute("s_date", s_date);
        request.setAttribute("e_date", e_date);
        IsHoliday isHoliday = new IsHoliday();
        List hList = holidayService.selectAll();

        //查询全部
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(s_date) && StringUtils.isEmpty(e_date)) {
            recordList = recordService.selectRecordBySql(1, 10);
            //根据条件查询
        } else {
            recordList = recordService.getRecordBy(1, 10, name, s_date, e_date);
        }
        Collections.reverse(recordList);
        Map<Integer, Map<Integer, Object>> rowMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        Mission mission = null;
        int rowNum = 1;
        for (Record a : recordList) {
            ++rowNum;
            mission = missionService.selectMission(a.getEid(),a.getDate());
            Map<Integer, Object> m = new HashMap<>();
            map.put("name", a.getName());
            map.put("date", a.getDate());
            map.put("weekday", isHoliday.getWeek(a.getDate()));
            if (isHoliday.isHoliday(hList, a.getDate())) {
                map.put("isRest", "休");
            } else {
                map.put("isRest", " ");
            }
            map.put("startime", a.getStartime());
            map.put("endtime", a.getEndtime());
            map.put("rest", a.getRest());
            map.put("situation", a.getSituation());
            map.put("worktime", a.getWorktime());
            map.put("remarks", a.getRemarks());
            if (mission == null) {
                map.put("design", "0.0");
                map.put("code", "0.0");
                map.put("study", "0.0");
                map.put("meeting", "0.0");
                map.put("test", "0.0");
            } else {
                map.put("design", mission.getDesign());
                map.put("code", mission.getCode());
                map.put("study", mission.getStudy());
                map.put("meeting", mission.getMeeting());
                map.put("test", mission.getTest());
            }
            m.put(0, rowNum - 1);
            m.put(1, map.get("name"));
            m.put(2, map.get("date"));
            m.put(3, map.get("weekday"));
            m.put(4, map.get("isRest"));
            m.put(5, map.get("startime"));
            m.put(6, map.get("endtime"));
            m.put(7, map.get("rest"));
            m.put(8, map.get("design"));
            m.put(9, map.get("code"));
            m.put(10, map.get("test"));
            m.put(11, map.get("meeting"));
            m.put(12, map.get("study"));
            m.put(13, map.get("situation"));
            m.put(14, map.get("worktime"));
            m.put(15, map.get("remarks"));
            rowMap.put(rowNum, m);
        }
        try {
            String fileName = System.currentTimeMillis() + "_table.xlsx";
            response.setContentType("application/x-download");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "inline;filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
            String templatePath = request.getSession().getServletContext().getRealPath("") + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "Excel" + File.separator + "record.xlsx";
            ExcelUtil.exportExcelByTemplate(templatePath, "出勤記録表", rowMap, 2, response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // 签到
    @RequestMapping("/employeeSignIn/{name}")
    public String employeeSignIn(@PathVariable("name") String name, HttpServletRequest request) {
        employee = employeeService.getByName(name);
        int eid = employee.getId();
        GetTime gt = new GetTime();
        String date = gt.getNow()[0];
        String time = gt.getNow()[1];
        if (recordService.isBeing(name, date) == null) {
            Record record = new Record();
            record.setDate(date);
            record.setName(name);
            record.setStartime(time);
            record.setEid(eid);
            request.setAttribute("url", "employee.jsp");
            if (recordService.insertRecord(record) != 0) {
                request.setAttribute("msg", "成功にサインインしました！");
                return "employee/employeeFace";
            } else {
                request.setAttribute("msg", "失敗にサインインしました！");
                return "employee/employeeFace";
            }
        } else if (StringUtils.isEmpty(recordService.isBeing(name, date).getStartime())) {
            if (recordService.updateStartime(eid, date, time) != 0) {
                request.setAttribute("msg", "成功にサインインしました！");
                return "employee/employeeFace";
            } else {
                request.setAttribute("msg", "失敗にサインインしました！");
                return "employee/employeeFace";
            }
        } else {
            request.setAttribute("msg", "当日はすでに記録にサインしてある！");
            return "employee/employeeFace";
        }
    }

    //签退跳转页面
    @RequestMapping("/employeeSignOut/{id}")
    public String employeeSignOut(@PathVariable("id") int id, HttpServletRequest request) {
        employee = employeeService.getById(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        Record record = recordService.isBeing(employee.getName(), date);
        if (record == null || StringUtils.isEmpty(record.getStartime())) {
            request.setAttribute("msg", "今日はまだサインインしていません!");
            return "employee/employeeFace";
        } else if (!StringUtils.isEmpty(record.getEndtime())) {
            request.setAttribute("msg", "当日すでにサインアウトが完了しました！");
            return "employee/employeeFace";
        } else {
            request.setAttribute("date", date);
            request.setAttribute("record", record);
            request.setAttribute("mission", new Mission());
            return "employee/remarks";
        }
    }


    //工作内容
    @RequestMapping(value = "/remarks", method = RequestMethod.POST)
    public String remarks(Record record, Mission mission, HttpServletRequest request) {
        employee = employeeService.getById(record.getEid());
        if (recordService.modifyRecord(record) != 0 && missionService.insertMission(mission) != 0) {
            if (missionService.selectMission(mission.getEid(), mission.getDate()) == null) {
                if (missionService.insertMission(mission) != 0) {
                    request.setAttribute("msg", "成功にサインアウトしました！");
                    return "employee/employeeFace";
                } else {
                    request.setAttribute("msg", "失敗にサインアウトしました！");
                    return "employee/employeeFace";
                }
            } else if (missionService.updateMission(mission) != 0) {
                request.setAttribute("msg", "成功にサインアウトしました！");
                return "employee/employeeFace";
            } else {
                request.setAttribute("msg", "失敗にサインアウトしました！");
                return "employee/employeeFace";
            }
        } else {
            request.setAttribute("msg", "失敗にサインアウトしました！");
            return "employee/employeeFace";
        }

    }

    //特殊情况
    @RequestMapping("/situation/{id}")
    public ModelAndView situation(@PathVariable("id") int id, Model model) {
        employee = employeeService.getById(id);
        model.addAttribute("userName", employee.getUserName());
        model.addAttribute("name", employee.getName());
        return new ModelAndView(new RedirectView("/employee/employee/situation.jsp"));
    }

    @RequestMapping("/employeeSit")
    public String employeeSit(Record record, HttpServletRequest request) {
        String date = record.getDate();
        String name = record.getName();
        String situation = record.getSituation();
        employee = employeeService.getByName(name);
        int eid = employee.getId();
        request.setAttribute("url", "employee.jsp");
        if (recordService.isBeing(name, date) == null) {
            if (recordService.insertRecord(record) != 0) {
                request.setAttribute("msg", "成功に追加しました！");
                return "employee/employeeFace";
            } else {
                request.setAttribute("msg", "特別な場合の追加に失敗しました！");
                return "employee/employeeFace";
            }
        } else {
            if (recordService.updateSituation(eid, date, situation) != 0) {
                request.setAttribute("msg", "成功に追加しました！");
                return "employee/employeeFace";
            } else {
                request.setAttribute("msg", "特別な場合の追加に失敗しました！");
                return "employee/employeeFace";
            }
        }
    }

    //跳转页面
    @RequestMapping("/manageSta/{name}")
    public String statistics(HttpServletRequest request, @PathVariable(value = "name") String name, Model model) {
        GetTime gt = new GetTime();
        String date = gt.getNow()[0];
        Record record = recordService.isBeing(name, date);
        String wtime = null;
        float statime = 0.0f;
        if (record == null || StringUtils.isEmpty(record.getWorktime())) {
            wtime = "0.0";
        } else {
            wtime = record.getWorktime();
        }
        List<Record> recordList = new ArrayList<>();
        recordList = recordService.getRecordByName(1, 10, name);
        for (Record r : recordList) {
            String worktime = r.getWorktime();
            if (StringUtils.isEmpty(worktime)) {
                worktime = "0.0";
            }
            float f = Float.parseFloat(worktime);
            statime = statime + f;
        }
        request.setAttribute("name", name);
        request.setAttribute("statime", statime);
        request.setAttribute("wtime", wtime);
        return "employee/statistics";
    }


    // 查询
    @RequestMapping(value = "/statistics/{name}/{statime:.+}", method = RequestMethod.GET)
    public String empSta(@PathVariable(value = "name") String name, @PathVariable(value = "statime") String statime, @RequestParam("serc") String serc, @RequestParam("condition") String condition, HttpServletRequest request,
                         Model model) {

        List<Record> recordList = new ArrayList<Record>();
        request.setAttribute("serc", serc);
        request.setAttribute("condition", condition);
        request.setAttribute("statime", statime);
        request.setAttribute("name", name);
        GetTime gt = new GetTime();
        String date = gt.getNow()[0];
        String time = gt.getNow()[1];
        String wtime = null;
        Record record = recordService.isBeing(name, date);
        float stime = 0.0f;
        if (StringUtils.isEmpty(record.getWorktime())) {
            if (StringUtils.isEmpty(record.getStartime())) {
                wtime = "0.0";
            } else {
                record.setEndtime(time);
                gt.getWorktime(record);
                wtime = record.getWorktime();
            }
        } else {
            wtime = record.getWorktime();
        }
        //查询全部
        if (serc.equals("all")) {
            request.setAttribute("wtime", wtime);
        } else if (serc.equals("day")) {
            if (!date.equals(condition.trim())) {
                wtime = recordService.getByDay(name, condition);
                if (StringUtils.isEmpty(wtime)) {
                    wtime = "0.0";
                }
            }
            request.setAttribute("wtime", wtime);
        } else if (serc.equals("month")) {
            String[] arr = condition.split("-");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(arr[0]));
            cal.set(Calendar.MONTH, Integer.parseInt(arr[1]) - 1);
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            String sMonth = condition.trim() + "-01";
            String eMonth = condition.trim() + "-" + lastDay;
            recordList = recordService.getRecordBy(1, 10, name, sMonth, eMonth);
            for (Record r : recordList) {
                String worktime = r.getWorktime();
                if (StringUtils.isEmpty(worktime)) {
                    worktime = "0.0";
                }
                float f = Float.parseFloat(worktime);
                stime = stime + f;
            }
            request.setAttribute("wtime", stime);
        } else {
            String sMonth = condition.trim() + "-01-01";
            String eMonth = condition.trim() + "-12-31";
            recordList = recordService.getRecordBy(1, 10, name, sMonth, eMonth);
            for (Record r : recordList) {
                String worktime = r.getWorktime();
                if (StringUtils.isEmpty(worktime)) {
                    worktime = "0.0";
                }
                float f = Float.parseFloat(worktime);
                stime = stime + f;
            }
            request.setAttribute("wtime", stime);
        }
        return "employee/statistics";
    }


}
