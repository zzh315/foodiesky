package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "employee related API")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @ApiOperation("employee login api")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @ApiOperation("employee logout api")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }


    /**
     * create new employee
     * @param employeeDTO
     * @return
     */
    @ApiOperation("create new employee API")
    @PostMapping
    public Result save(@RequestBody EmployeeDTO employeeDTO) {

        employeeService.save(employeeDTO);


        return Result.success();
    }

    /**
     * return employee name search result in PageResult
     * @param employeeDTO
     * @return
     */
    @ApiOperation("query employee page info API")
    @GetMapping("page")
    public Result<PageResult> pageQuery(EmployeePageQueryDTO employeeDTO) {

        PageResult pageResult =  employeeService.queryPage(employeeDTO);

        return Result.success(pageResult);
    }

    @ApiOperation("toggle employee status for login functionality")
    @PostMapping("/status/{status}")
    public Result toggleStatus(@PathVariable Integer status,  Long id) {

        employeeService.toggleStatus(status,id);

        return Result.success();
    }
}
