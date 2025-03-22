package com.sky.controller.admin;


import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Api("controller for dish")
@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;


    @PostMapping
    @ApiOperation("submit dish and save")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("dishDTO:{}", dishDTO);
        dishService.save(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("display dish pages")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("dishPageQueryDTO:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);

        return Result.success(pageResult);
    }


    @DeleteMapping
    @ApiOperation("delete dishes by ids in request query param")
    public Result deleteDishes(@RequestParam List<Long> ids) {
        log.info("ids:{}", ids);

        dishService.deleteDishesByIds(ids);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishVO> getDishById(@PathVariable Long id) {
        log.info("id:{}", id);
        DishVO dish = dishService.getDishVOById(id);

        return Result.success(dish);
    }

    @PutMapping
    @ApiOperation("modify dish")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("dishDTO:{}", dishDTO);

        dishService.updateWithFlavor(dishDTO);

        return Result.success();
    }
}
