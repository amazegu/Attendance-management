package com.toj.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toj.jr.WorkRecordDataSource;
import com.toj.pojo.Mission;
import com.toj.pojo.Program;
import com.toj.pojo.Record;
import com.toj.pojo.Result;
import com.toj.service.*;
import com.toj.utils.ExcelImport;
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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
@RequestMapping("/ReportHandler")
public class ReportHandler {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RecordService recordService;
    @Autowired
    HolidayService holidayService;
    @Autowired
    ReportService reportService;
    @Autowired
    ProgramService programService;
    @Autowired
    MissionService missionService;

    //分页
    public void pageIn(Model model, List list) {
        PageInfo page = new PageInfo(list, 5);
        model.addAttribute("pageInfo", page);
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
                map.put("isRest", "休️");
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

    //跳转页面
    @RequestMapping("/managerep/{name}/{pn}")
    public String manageReport(HttpServletRequest request, @PathVariable(value = "pn") String pn,
                               @PathVariable(value = "name") String name, Model model) {
        List<Record> queryRecord = getRecordLists(pn, name, "thisMonth", model);
        List list = getList(queryRecord);
        request.setAttribute("rlist", list);
        request.setAttribute("serc", "thisMonth");
        return "employee/report";
    }

    //跳转页面
    @RequestMapping("/queryRep/{name}/{pn}")
    public String queryReport(HttpServletRequest request, @PathVariable(value = "pn") String pn,
                              @PathVariable(value = "name") String name, @RequestParam("serc") String serc, Model model) {
        List<Record> queryRecord = getRecordLists(pn, name, serc, model);
        List list = getList(queryRecord);
        request.setAttribute("serc", serc);
        request.setAttribute("rlist", list);
        return "employee/report";
    }

    public List<Record> getRecordLists(@PathVariable("pn") String pn, @PathVariable("name") String name, @RequestParam("serc") String serc, Model model) {
        int no = Integer.parseInt(pn);
        PageHelper.startPage(no, 10);
        List<Record> queryRecord = new ArrayList<Record>();
        String s_date = null;
        String e_date = null;
        if ("thisMonth".equals(serc)) {
            s_date = getMonth() + "-01";
            e_date = getLastDay();
        } else {
            s_date = getLastMonth() + "-01";
            e_date = getLastMonthDay();
        }
        queryRecord = reportService.selectReportList(1, 10, name, s_date, e_date);
        pageIn(model, queryRecord);
        return queryRecord;
    }


    @RequestMapping(value = "/upload/{id}", method = RequestMethod.POST)
    private void importExcel(@PathVariable("id") int eid, @RequestParam(value = "serc") String serc, HttpServletRequest request, HttpServletResponse response) {
        try {
            Result result = new Result();
            response.setContentType("application/json; charset=UTF-8");
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding(request.getCharacterEncoding());
            ExcelImport excelImport = new ExcelImport();
            List<FileItem> list = upload.parseRequest(request);
            for (int i = 0; i < list.size(); i++) {
                FileItem item = list.get(i);
                if (item.getName().endsWith(".xls") || item.getName().endsWith(".xlsx")) {
                    List<List<String>> datas = excelImport.importXlsx(item.getInputStream());
                    if (datas != null && datas.size() > 0) {
                        long index = importFromExcel(datas, eid, serc);
                        System.out.println(index);
                        if (index > 0) {
                            /*String name = employeeService.getById(eid).getName();
                            request.setAttribute("msg", "データの更新に成功しました");
                            return "employee/success";*/
                            result.setSuccess(true);
                            result.setMsg("データの更新に成功しました");
                        } else {
                            /*request.setAttribute("msg", "データが更新されていません");
                            return "employee/fail";*/
                            result.setMsg("データが更新されていません");
                            result.setSuccess(false);
                        }
                    }
                }
            }
            getResult(result, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*request.setAttribute("msg", "アップロードに失敗しました");
        return "employee/fail";*/
    }

    // 修改定位
    @RequestMapping(value = "/modityRep/{id}", method = RequestMethod.GET)
    public String editRec(@PathVariable("id") int id, HttpServletRequest request) {
        Record record = reportService.getById(id);
        Mission mission = missionService.selectMission(record.getEid(), record.getDate());
        request.setAttribute("record", record);
        request.setAttribute("mission", mission);
        return "employee/modiReport";
    }

    // 修改
    @RequestMapping(value = "/modityRepo/{id}", method = RequestMethod.GET)
    public String updateRecord(@PathVariable("id") int id, Record record, Mission mission, HttpServletRequest request) {
        record.setEid(employeeService.getByName(record.getName()).getId());
        if (reportService.updateReport(record) != 0) {
            if (missionService.updateMission(mission) != 0) {
                request.setAttribute("msg", "操作に成功しました!");
                return "employee/employeeFace";
            } else {
                request.setAttribute("msg", "操作に失敗しました!");
                return "employee/employeeFace";
            }
        } else {
            request.setAttribute("msg", "修正に失敗しました!");
            return "employee/fail";
        }
    }

    //追加跳转页面
    @RequestMapping("/add")
    public String add(@RequestParam("id") int id, HttpServletRequest request) {
        Program program = new Program();
        program = programService.getByEid(id);
        if (program == null) {
            program.setEndtime("18:00:00");
            program.setStartime("09:00:00");
            program.setRest("1.0");
        }
        Mission mission = null;
        request.setAttribute("program", program);
        request.setAttribute("mission", mission);
        return "employee/addReport";
    }

    //追加
    @RequestMapping("/addReport")
    public String addReport(Record record,Mission mission, HttpServletRequest request) {
        record.setEid(employeeService.getByName(record.getName()).getId());
        if (reportService.selectReport(record.getName(), record.getDate()) != null) {
            request.setAttribute("msg", "既存のデータは追加できません!");
            return "employee/fail";
        } else {
            if (reportService.insertReport(record) != 0) {
                if (missionService.updateMission(mission) != 0) {
                    request.setAttribute("msg", "追加に成功しました!");
                    return "employee/success";
                } else {
                    request.setAttribute("msg", "追加に失敗しました!");
                    return "employee/fail";
                }
            } else {
                request.setAttribute("msg", "追加に失敗しました!");
                return "employee/fail";
            }
        }

    }

    //删除
    @RequestMapping(value = "/deleteRep", method = RequestMethod.POST)
    public void deletePro(@RequestParam("id") int id, HttpServletResponse response) {
        Result result = new Result();
        Record report = reportService.getById(id);
        response.setContentType("application/json; charset=UTF-8");
        if (reportService.deleteReport(id) != 0) {
            if(missionService.deleteMission(report.getEid(),report.getDate())!= 0){
                result.setMsg("削除しました");
            }else{
                result.setMsg("削除に失敗しました");
            }
        } else {
            result.setMsg("削除に失敗しました");
        }
        getResult(result, response);
    }

    //追加跳转页面
    @RequestMapping(value = "/existRecord", method = RequestMethod.POST)
    public void existRecord(@RequestParam("name") String name, @RequestParam("date") String date, HttpServletResponse response) {
        Result result = new Result();
        response.setContentType("UTF-8");
        if (reportService.selectReport(name, date) != null) {
            result.setMsg("既存のデータは追加できません");
            result.setSuccess(false);
        } else {
            result.setMsg("追加できます");
            result.setSuccess(true);
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

    //打印
    @RequestMapping("/print/{name}")
    public void print(@PathVariable("name") String name, @RequestParam("serc") String serc, HttpServletRequest request, HttpServletResponse response, Model model) {
        List hList = holidayService.selectAll();
        String fileName = System.currentTimeMillis() + "_table.pdf";
        String filePath = request.getSession().getServletContext().getRealPath("") + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "jasper" + File.separator + "tample.jrxml";
        JasperPrint jasperPrint = null;
        List<Record> queryRecord = getRecordLists("1", name, serc, model);
        List list = getList(queryRecord);
        String s_date = null;
        if ("thisMonth".equals(serc)) {
            s_date = getMonth() + "-01";
        } else {
            s_date = getLastMonth() + "-01";
        }
        Program program = new Program();
        program = programService.getByEid(queryRecord.get(0).getEid());
        if (program == null) {
            program.setName("研修");
        }
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
            //查询全部
            jasperPrint = JasperFillManager.fillReport(jasperReport, null, new WorkRecordDataSource(list, hList, s_date, program.getName()));
            //根据条件查询
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

    //导出
    @RequestMapping("/export/{name}")
    public void export(@PathVariable("name") String name, @RequestParam("serc") String serc, HttpServletRequest request, HttpServletResponse response) {
        IsHoliday isHoliday = new IsHoliday();
        List hList = holidayService.selectAll();
        List<Record> queryRecord = new ArrayList<Record>();
        String s_date = null;
        String e_date = null;
        if ("thisMonth".equals(serc)) {
            s_date = getMonth() + "-01";
            e_date = getLastDay();
        } else {
            s_date = getLastMonth() + "-01";
            e_date = getLastMonthDay();
        }
        queryRecord = recordService.getRecordBy(1, 10, name, s_date, e_date);
        Collections.reverse(queryRecord);
        Map<Integer, Map<Integer, Object>> rowMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        Mission mission = null;
        int rowNum = 1;
        for (Record a : queryRecord) {
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


    public long importFromExcel(List<List<String>> datas, int eid, String serc) {
        String name = employeeService.getById(eid).getName();
        String nowMonth = null;
        if ("lastMonth".equals(serc)) {
            nowMonth = getLastMonth();

        } else {
            nowMonth = getMonth();
        }
        long index = 0L;
        for (List<String> list1 : datas) {
            if (!nowMonth.equals(list1.get(2).substring(0, 7)) || StringUtils.isEmpty(list1.get(2))) {
                continue;
            }
            Record record = new Record(list1.get(14), list1.get(1), eid, list1.get(15), list1.get(2), list1.get(5), list1.get(6), list1.get(13), list1.get(7));
            Mission mission = new Mission(eid, list1.get(2), list1.get(8), list1.get(10), list1.get(11), list1.get(12), list1.get(9));
            if (reportService.selectReport(name, list1.get(2)) != null) {
                if (missionService.selectMission(eid, list1.get(2)) != null) {
                    if (reportService.updateByNameDate(record) != 0 && missionService.insertMission(mission) != 0) {
                        index++;
                    }
                } else {
                    if (reportService.updateByNameDate(record) != 0 && missionService.updateMission(mission) != 0) {
                        index++;
                    }
                }
                if (reportService.updateByNameDate(record) != 0) {
                    index++;
                }
            } else {
                if (reportService.insertReport(record) != 0) {
                    index++;
                }
            }
        }
        return index;
    }

    public String getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String nowMonth = sdf.format(new Date());
        return nowMonth;
    }

    public String getLastDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        String lastday = getMonth() + "-" + days;
        return lastday;
    }

    public String getLastMonthDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        String lastMonthday = getLastMonth() + "-" + days;
        return lastMonthday;
    }

    public String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        String lastMonth = null;
        if (month < 10) {
            lastMonth = year + "-0" + month;
        } else {
            lastMonth = year + "-" + month;
        }
        return lastMonth;
    }

    public void getResult(Result result, HttpServletResponse response) {
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
