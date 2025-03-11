package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * create new employee
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);


    /**
     * return employee name search result in PageResult
     * @param employeeDTO
     * @return
     */
    PageResult queryPage(EmployeePageQueryDTO employeeDTO);



    void toggleStatus(Integer status, Long id);
}
