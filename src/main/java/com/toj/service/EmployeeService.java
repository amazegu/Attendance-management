package com.toj.service;


import com.toj.pojo.Employee;

import java.util.List;


public interface EmployeeService {



    /**
     *  添加员工
     * @param employee   员工信息
     * @return  插入结果 !=0则插入成功
     */
    public int insertEmployee(Employee employee);

    /**
     * @param id
     * @return  删除结果，!=0则删除成功
     */
    public int deleteEmployee(int id);

    /**
     *  修改员工信息
     * @param employee   员工信息
     * @return  修改结果 !=0则修改成功
     */
    public int modifyEmployee(Employee employee);

    /**
     *  修改员工密码
     * @param password     修改后的密码
     * @param id   查询条件
     * @return  修改结果 !=0则修改成功
     */
    public int modifyEmployeePwd(String password, int id);

    /**
     *  根据用户名查询出员工实体
     * @param name
     * @return
     */
    public Employee getByName(String name);

    /**
     *  根据用户名查询出员工实体
     * @param userName
     * @return
     */
    public Employee getByUserName(String userName);

    /**
     *  根据用户名查询出员工实体
     * @param id
     * @return
     */
    public Employee getById(int id);

    /**
     *  员工登录设置
     * @param userName   学号（唯一）
     * @param password   密码
     * @return
     */
    public String queryByNamePwd(String userName, String password);

    /**
     * sql后加limit实现分页
     *
     */
    public List<Employee> selectEmployeeBySql(int pageNo, int pageSize);

    /**
     * 根据学号查询员工信息
     * @param pageNo
     * @param pageSize
     * @return  查询结果
     */
    public List<Employee> getByEmployeeUserName(int pageNo, int pageSize, String userName);


    /**
     *  ajax验证学号是否存在
     * @param userName   学号
     * @return  结果
     */
    public String ajaxQueryByUserName(String userName);


}
