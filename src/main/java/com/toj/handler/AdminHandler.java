package com.toj.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toj.jr.WorkRecordDataSource;
import com.toj.pojo.*;
import com.toj.service.*;
import com.toj.utils.ExcelUtil;
import com.toj.utils.IsHoliday;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/AdminHandler")
public class AdminHandler {


    @Autowired
    EmployeeService employeeService;
    @Autowired
    RecordService recordService;
    @Autowired
    HolidayService holidayService;
    @Autowired
	BelongService belongService;
    @Autowired
    ProgramService programService;
    @Autowired
    MissionService missionService;


    // 跳转页面
    @RequestMapping(value = "/adminpage", method = RequestMethod.GET)
    public String adminPage(HttpServletRequest request) {
        return "admin/adminFace";
    }

    // 添加员工
    @RequestMapping("/addEmployee")
    public String addStudent(Employee employee, Model model) {
        if (employeeService.insertEmployee(employee) != 0) {
            model.addAttribute("employee", employee);
            model.addAttribute("msg", "操作に成功しました!");
            return "admin/adminFace";
        } else {
            model.addAttribute("msg", "操作に失敗しました!");
            return "admin/adminFace";
        }

    }

    // 添加考勤记录
    @RequestMapping("/addRecord")
    public String addRecord(Record record, Model model) {
        if (recordService.insertRecord(record) != 0) {
            model.addAttribute("record", record);
            model.addAttribute("msg", "操作に成功しました!");
            return "admin/adminFace";
        } else {
            model.addAttribute("msg", "操作に失敗しました!");
            return "admin/adminFace";
        }

    }

    //组装数据
    public List getList(List<Record> queryRecord) {
        IsHoliday isHoliday = new IsHoliday();
        List hList = holidayService.selectAll();
        List list = new ArrayList();
        Mission mission = null;
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
                map.put("isRest", "休");
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
    @RequestMapping(value = "/queryRec/{pn}", method = RequestMethod.GET)
    public String redirectRec(@RequestParam("name") String name, @RequestParam("s_date") String s_date, @RequestParam("e_date") String e_date, HttpServletRequest request,
                              @PathVariable(value = "pn") String pn, Model model) {

        int no = Integer.parseInt(pn);
        List<Record> recordList = new ArrayList<Record>();
        PageHelper.startPage(no, 10);
        request.setAttribute("name", name);
        request.setAttribute("s_date", s_date);
        request.setAttribute("e_date", e_date);
        //查询全部
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(s_date) && StringUtils.isEmpty(e_date)) {
            System.out.println("------------------------------------------------------------------------------------------------");
            recordList = recordService.selectRecordBySql(1, 10);
            pageIn(model, recordList);
            List list = getList(recordList);
            request.setAttribute("rlist", list);
            return "admin/queryRecord";

            //根据条件查询
        } else {
            recordList = recordService.getRecordBy(1, 10, name, s_date, e_date);
            pageIn(model, recordList);
            List list = getList(recordList);
            request.setAttribute("rlist", list);
            return "admin/queryRecord";
        }
    }

    // 查询
    @RequestMapping(value = "/query/{pn}", method = RequestMethod.GET)
    public String redirect(@RequestParam("serc") String serc, @RequestParam("condition") String condition, HttpServletRequest request,
                           @PathVariable(value = "pn") String pn, Model model) {

        int no = Integer.parseInt(pn);
        List<Employee> employeeList = new ArrayList<Employee>();
        PageHelper.startPage(no, 10);
        request.setAttribute("serc", serc);
        request.setAttribute("condition", condition);
        //查询全部
        if (serc.equals("all")) {
            employeeList = employeeService.selectEmployeeBySql(1, 10);
            pageIn(model, employeeList);
            request.setAttribute("elist", employeeList);
            return "admin/queryEmployee";

            //根据学号查询
        } else {

            employeeList = employeeService.getByEmployeeUserName(1, 10, condition);
            pageIn(model, employeeList);
            request.setAttribute("elist", employeeList);
            System.out.println("userName");

            return "admin/queryEmployee";


        }

    }

    //删除员工
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteStudent(@RequestParam("id") int id, HttpServletResponse response) {
        response.setContentType("application/json; charset=UTF-8");
        Result result = new Result();
        if (employeeService.deleteEmployee(id) != 0) {
        	belongService.deleteByEid(id);
            result.setMsg("削除しました");
        } else {
            result.setMsg("削除に失敗しました");
        }
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

    // 修改定位
    @RequestMapping(value = "/modityEmp/{userName}", method = RequestMethod.GET)
    public String editPre(@PathVariable("userName") String userName, HttpServletRequest request) {

        List<Employee> queryEmployee = new ArrayList<Employee>();
        queryEmployee = employeeService.getByEmployeeUserName(1, 10, userName);

        request.setAttribute("employeeList", queryEmployee);
        return "admin/modiEmployee";
    }

    // 修改定位
    @RequestMapping(value = "/modityRec/{id}", method = RequestMethod.GET)
    public String editRec(@PathVariable("id") int id, HttpServletRequest request) {

        List<Record> queryRecord = new ArrayList<Record>();
        queryRecord = recordService.getByRecordId(1, 10, id);
        Mission mission = missionService.selectMission(queryRecord.get(0).getEid(), queryRecord.get(0).getDate());
        request.setAttribute("mission", mission);
        request.setAttribute("recordList", queryRecord);
        return "admin/modiRecord";
    }


    // 修改
    @RequestMapping(value = "/modityempl/{userName}", method = RequestMethod.GET)
    public String update(@PathVariable("userName") String userName, Employee employee, HttpServletRequest request) {
        if (employeeService.modifyEmployee(employee) != 0) {
            request.setAttribute("msg", "操作に成功しました!");
            return "admin/adminFace";
        } else {
            request.setAttribute("msg", "操作に失敗しました!");
            return "admin/adminFace";
        }
    }

    // 修改
    @RequestMapping(value = "/modityReco/{id}", method = RequestMethod.GET)
    public String updateRecord(@PathVariable("id") int id, Record record,Mission mission, HttpServletRequest request) {
        Employee employee = employeeService.getByName(record.getName());
        record.setEid(employee.getId());
        if (recordService.modifyRecord(record) != 0) {
            if (missionService.updateMission(mission) != 0) {
                request.setAttribute("msg", "操作に成功しました!");
                return "admin/adminFace";
            } else {
                request.setAttribute("msg", "操作に失敗しました!");
                return "admin/adminFace";
            }
        } else {
            request.setAttribute("msg", "操作に失敗しました!");
            return "admin/adminFace";
        }
    }


    // 跳转页面
    @RequestMapping("/manageemp/{pn}")
    public String manageEmployee(HttpServletRequest request,
                                 @PathVariable(value = "pn") String pn, Model model) {
        int no = Integer.parseInt(pn);

        PageHelper.startPage(no, 10);
        List<Employee> queryEmployee = new ArrayList<Employee>();
        queryEmployee = employeeService.selectEmployeeBySql(1, 100);
        pageIn(model, queryEmployee);
        request.setAttribute("elist", queryEmployee);
        return "admin/queryEmployee";
    }

    @RequestMapping("/managerec/{pn}")
    public String manageRecord(HttpServletRequest request,
                               @PathVariable(value = "pn") String pn, Model model) {
        int no = Integer.parseInt(pn);

        PageHelper.startPage(no, 10);
        List<Record> queryRecord = new ArrayList<Record>();
        queryRecord = recordService.selectRecordBySql(1, 100);
        pageIn(model, queryRecord);
        List list = getList(queryRecord);
        request.setAttribute("rlist", list);
        return "admin/queryRecord";
    }


    // 跳转页面
    @RequestMapping("/addemp")
    public String adEmployee() {
        return "admin/addEmployee";
    }

    //导出
    @RequestMapping("/export")
    public void export(@RequestParam("name") String name, @RequestParam("s_date") String s_date, @RequestParam("e_date") String e_date,
                       HttpServletRequest request, HttpServletResponse response) {
        List<Record> recordList = new ArrayList<Record>();
        request.setAttribute("name", name);
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

    //打印
    @RequestMapping("/print")
    public void print(@RequestParam("name") String name, @RequestParam("s_date") String s_date, @RequestParam("e_date") String e_date,
                      HttpServletRequest request, HttpServletResponse response) {
        List<Record> recordList = new ArrayList<Record>();
        request.setAttribute("s_date", s_date);
        request.setAttribute("e_date", e_date);
        List hList = holidayService.selectAll();
        Program program = new Program();
        program = programService.getByEid(employeeService.getByName(name).getId());
        if(program==null){
            program.setName("研修");
        }
        String fileName = System.currentTimeMillis() + "_table.pdf";
        String filePath = request.getSession().getServletContext().getRealPath("")+ File.separator+ "WEB-INF" + File.separator + "classes" + File.separator + "jasper" +File.separator+"tample.jrxml" ;
// jasper模版
        JasperPrint jasperPrint = null;
        List list = null;
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
            //查询全部
            if (StringUtils.isEmpty(s_date)) {
                recordList = recordService.selectRecordBySql(1, 10);
                list = getList(recordList);
                jasperPrint = JasperFillManager.fillReport(jasperReport, null, new WorkRecordDataSource(list, hList,program.getName()));
                //根据条件查询
            } else {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                calendar.setTime(sdf.parse(s_date));
                int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                String[] arr = s_date.split("-");
                arr[2] = String.valueOf(lastDay);
                String eDate = StringUtils.join(arr, "-");
                recordList = recordService.getRecordBy(1, 10, name, s_date, eDate);
                list = getList(recordList);
                jasperPrint = JasperFillManager.fillReport(jasperReport, null, new WorkRecordDataSource(list, hList, s_date,program.getName()));
            }
            OutputStream out = response.getOutputStream();
            response.setContentType("application/pdf");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "inline;filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //跳转页面
    @RequestMapping("/manageSta/{pn}")
    public String statistics(HttpServletRequest request, @PathVariable(value = "pn") String pn, Model model) {
        int no = Integer.parseInt(pn);
        PageHelper.startPage(no, 10);
        //获得当前月份
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.getActualMaximum(month - 1);
        String sMonth = year + "-" + month + "-01";
        String eMonth = year + "-" + month + "-" + day;
        List<Record> recordList = new ArrayList<>();
        List<Record> recordList1 = new ArrayList<>();
        List<Employee> queryEmployee = new ArrayList<>();
        List<Statistics> list = new ArrayList<>();
        queryEmployee = employeeService.selectEmployeeBySql(1, 10);
        //获取所有员工
        for (Employee e : queryEmployee) {
            //当前员工的数据
            recordList = recordService.getRecordByName(1, 10, e.getName());
            float wtime = 0.0f;
            float statime = 0.0f;
            //当前员工总工作时间
            for (Record r : recordList) {
                String worktime = r.getWorktime();
                if (StringUtils.isEmpty(worktime)) {
                    worktime = "0.0";
                }
                float f = Float.parseFloat(worktime);
                statime = statime + f;
            }
            //当月工作时间
            recordList1 = recordService.getRecordBy(1, 10, e.getName(), sMonth, eMonth);
            for (Record r : recordList1) {
                String worktime = r.getWorktime();
                if (StringUtils.isEmpty(worktime)) {
                    worktime = "0.0";
                }
                float f = Float.parseFloat(worktime);
                wtime = wtime + f;
            }
            list.add(new Statistics(e.getName(), wtime, statime));
        }
        pageIn(model, list);
        request.setAttribute("list", list);
        return "admin/statistics";
    }


    // 查询
    @RequestMapping(value = "/statistics/{pn}", method = RequestMethod.GET)
    public String empSta(@PathVariable(value = "pn") String pn, @RequestParam("name") String name, @RequestParam("s_date") String s_date, @RequestParam("e_date") String e_date, HttpServletRequest request,
                         Model model) {
        int no = Integer.parseInt(pn);
        PageHelper.startPage(no, 10);
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.getActualMaximum(month - 1);
        String sMonth = year + "-" + month + "-01";
        String eMonth = year + "-" + month + "-" + day;
        List<Record> recordList = new ArrayList<>();
        List<Record> recordList1 = new ArrayList<>();
        List<Employee> queryEmployee = new ArrayList<>();
        List<Statistics> list = new ArrayList<>();
        request.setAttribute("name", name);
        request.setAttribute("s_date", s_date);
        request.setAttribute("e_date", e_date);

        //查询全部
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(s_date) && StringUtils.isEmpty(e_date)) {
            queryEmployee = employeeService.selectEmployeeBySql(1, 10);
            //获取所有员工
            for (Employee e : queryEmployee) {
                //当前员工的数据
                recordList = recordService.getRecordByName(1, 10, e.getName());
                float wtime = 0.0f;
                float statime = 0.0f;
                //当前员工总工作时间
                for (Record r : recordList) {
                    String worktime = r.getWorktime();
                    if (StringUtils.isEmpty(worktime)) {
                        worktime = "0.0";
                    }
                    float f = Float.parseFloat(worktime);
                    statime = statime + f;
                }
                //当月工作时间
                recordList1 = recordService.getRecordBy(1, 10, e.getName(), sMonth, eMonth);
                for (Record r : recordList1) {
                    String worktime = r.getWorktime();
                    if (StringUtils.isEmpty(worktime)) {
                        worktime = "0.0";
                    }
                    float f = Float.parseFloat(worktime);
                    wtime = wtime + f;
                }
                list.add(new Statistics(e.getName(), wtime, statime));
            }
            pageIn(model, list);
            request.setAttribute("list", list);
            return "admin/statistics";

            //根据条件查询
            //名字非空
        } else if (!StringUtils.isEmpty(name)) {
            recordList = recordService.getRecordBy(1, 10, name, s_date, e_date);
            float wtime = 0.0f;
            float statime = 0.0f;
            for (Record r : recordList) {
                String worktime = r.getWorktime();
                if (StringUtils.isEmpty(worktime)) {
                    worktime = "0.0";
                }
                float f = Float.parseFloat(worktime);
                wtime = wtime + f;
            }
            recordList1 = recordService.getRecordByName(1, 10, name);
            for (Record r : recordList1) {
                String worktime = r.getWorktime();
                if (StringUtils.isEmpty(worktime)) {
                    worktime = "0.0";
                }
                float f = Float.parseFloat(worktime);
                statime = statime + f;
            }
            list.add(new Statistics(name, wtime, statime));
            pageIn(model, recordList);
            request.setAttribute("list", list);
            return "admin/statistics";
            //名字为空
        } else {
            queryEmployee = employeeService.selectEmployeeBySql(1, 10);
            //获取所有员工
            for (Employee e : queryEmployee) {
                //当前员工的数据
                recordList = recordService.getRecordByName(1, 10, e.getName());
                float wtime = 0.0f;
                float statime = 0.0f;
                //当前员工总工作时间
                for (Record r : recordList) {
                    String worktime = r.getWorktime();
                    if (StringUtils.isEmpty(worktime)) {
                        worktime = "0.0";
                    }
                    float f = Float.parseFloat(worktime);
                    statime = statime + f;
                }
                //当月工作时间
                recordList1 = recordService.getRecordBy(1, 10, e.getName(), s_date, e_date);
                for (Record r : recordList1) {
                    String worktime = r.getWorktime();
                    if (StringUtils.isEmpty(worktime)) {
                        worktime = "0.0";
                    }
                    float f = Float.parseFloat(worktime);
                    wtime = wtime + f;
                }
                list.add(new Statistics(e.getName(), wtime, statime));
            }
            pageIn(model, list);
            request.setAttribute("list", list);
            return "admin/statistics";
        }

    }
}
