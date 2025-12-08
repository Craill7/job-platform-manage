package com.ruoyi.recruit.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.recruit.domain.TProvinces;
import com.ruoyi.recruit.service.ITProvincesService;

/**
 * 省份管理Controller
 * 
 * @author Yihan
 * @date 2025-12-01
 */
@RestController
@RequestMapping("/recruit/provinces")
public class TProvincesController extends BaseController
{
    @Autowired
    private ITProvincesService tProvincesService;

    /**
     * 查询省份管理列表
     */
    @GetMapping("/list")
    public AjaxResult list(TProvinces tProvinces)
    {
        List<TProvinces> list = tProvincesService.selectTProvincesList(tProvinces);
        return success(list);
    }

    /**
     * 获取省份管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tProvincesService.selectTProvincesById(id));
    }
}

