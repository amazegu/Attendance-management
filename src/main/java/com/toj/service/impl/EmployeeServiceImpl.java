package com.toj.service.impl;

import com.toj.mapper.EmployeeMapper;
import com.toj.pojo.Employee;
import com.toj.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EmployeeServiceImpl implements EmployeeService, Serializable {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     *  添加员工
     * @param employee   员工信息
     * @return  插入结果 !=0则插入成功
     */
    @Override
    public int insertEmployee(Employee employee) {
        return employeeMapper.insertEmployee(employee);
    }

    /**
     *  删除员工信息
     * @param id
     * @return  删除结果，!=0则删除成功
     */
    @Override
    public int deleteEmployee(int id) {
        return employeeMapper.deleteEmployee(id);
    }

    /**
     *  修改员工信息
     * @param Employee   员工信息
     * @return  修改结果 !=0则修改成功
     */
    @Override
    public int modifyEmployee(Employee Employee) {
        return employeeMapper.modifyEmployee(Employee);
    }

    /**
     *  修改员工密码
     * @param password     修改后的密码
     * @param id   查询条件学号
     * @return  修改结果 !=0则修改成功
     */
    @Override
    public int modifyEmployeePwd(String password, int id) {
        return employeeMapper.modifyEmployeePwd(password,id);
    }

    /**
     *  根据学号查询出员工实体
     * @param name
     * @return
     */
    @Override
    public Employee getByName(String name) {
        return employeeMapper.getByName(name);
    }

    /**
     *  根据学号查询出员工实体
     * @param userName
     * @return
     */
    @Override
    public Employee getByUserName(String userName) {
        return employeeMapper.getByUserName(userName);
    }

    /**
     *  根据学号查询出员工实体
     * @param id
     * @return
     */
    @Override
    public Employee getById(int id) { return  employeeMapper.getById(id); }

    /**
     *  员工登录设置
     * @param userName   （唯一）
     * @param password   密码
     * @return
     */
    @Override
    public String queryByNamePwd(String userName, String password) {
        return employeeMapper.queryByNamePwd(userName,password);
    }

    /**
     *  查询全部员工
     * @param pageNo
     * @param pageSize
     * @return  查询结果
     */
    @Override
    public List<Employee> selectEmployeeBySql(int pageNo, int pageSize) {
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("pageNo",(pageNo-1) * pageSize);
        data.put("pageSize",pageSize);
        return employeeMapper.selectEmployeeBySql(data);
    }

    /**
     * 根据学号查询员工信息
     * @param pageNo
     * @param pageSize
     * @return  查询结果
     */
    @Override
    public List<Employee> getByEmployeeUserName(int pageNo, int pageSize,String userName) {
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("pageNo",(pageNo-1) * pageSize);
        data.put("pageSize",pageSize);
        data.put("userName",userName);
        return employeeMapper.getByEmployeeUserName(data);
    }


    /**
     *  ajax验证学号是否存在
     * @param userName   学号
     * @return  结果
     */
    @Override
    public String ajaxQueryByUserName(String userName) {
        return employeeMapper.ajaxQueryByUserName(userName);
    }

}
