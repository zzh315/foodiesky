package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * insert new employee into DB
     * @param employee
     */
    @Insert("insert into employee " +
            "values" +
            "(null,#{name},#{username},#{password},#{phone},#{sex},#{idNumber}," +
            "#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Employee employee);


    /**
     * return name search result list of Employees
     * @param employeePageDTO
     * @return
     */
    Page<Employee> queryPage(EmployeePageQueryDTO employeePageDTO);



    void update(Employee employee);
}
