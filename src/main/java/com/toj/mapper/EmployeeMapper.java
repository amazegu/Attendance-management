package com.toj.mapper;

import java.util.List;
import java.util.Map;

import com.toj.pojo.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;



public interface EmployeeMapper {


    /**
     *  添加员工
     * @param Employee   员工信息
     * @return  插入结果 !=0则插入成功
     */
    public int insertEmployee(Employee Employee);

    /**
     *  删除员工信息
     * @param id
     * @return  删除结果，!=0则删除成功
     */
    public int deleteEmployee(int id);

    /**
     *  修改员工信息
     * @param Employee   员工信息
     * @return  修改结果 !=0则修改成功
     */
    public int modifyEmployee(Employee Employee);

    /**
     *  修改员工密码
     * @param password     修改后的密码
     * @param id   查询条件学号
     * @return  修改结果 !=0则修改成功
     */
    public int modifyEmployeePwd(@Param("password") String password, @Param("id") int id);

    /**
     *  根据学号查询出员工实体
     * @param name
     * @return
     */
    public Employee getByName(String name);

    /**
     *  根据学号查询出员工实体
     * @param userName
     * @return
     */
    public Employee getByUserName(String userName);

    /**
     *  根据学号查询出员工实体
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
    public String queryByNamePwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 查询全部员工，接住sql语句进行分页
     * @param data
     * @return      查询结果
     */
    public List<Employee> selectEmployeeBySql(Map<String, Object> data);


    /**
     * 根据学号查询员工信息
     * @param data
     * @return  查询结果
     */
    public List<Employee> getByEmployeeUserName(Map<String, Object> data);




    /**
     *  ajax验证学号是否存在
     * @param userName   学号
     * @return  结果
     */
    public String ajaxQueryByUserName(String userName);



}
