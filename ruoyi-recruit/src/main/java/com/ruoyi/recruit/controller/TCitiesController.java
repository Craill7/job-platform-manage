package com.ruoyi.recruit.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.recruit.domain.TCities;
import com.ruoyi.recruit.service.ITCitiesService;

/**
 * 城市管理Controller
 * 
 * @author Yihan
 * @date 2025-12-01
 */
@RestController
@RequestMapping("/recruit/cities")
public class TCitiesController extends BaseController
{
    @Autowired
    private ITCitiesService tCitiesService;

    /**
     * 查询城市管理列表
     */
    @GetMapping("/list")
    public AjaxResult list(TCities tCities)
    {
        List<TCities> list = tCitiesService.selectTCitiesList(tCities);
        return success(list);
    }

    /**
     * 根据省份ID查询城市列表
     */
    @GetMapping("/byProvince/{provinceId}")
    public AjaxResult listByProvince(@PathVariable("provinceId") Long provinceId)
    {
        List<TCities> list = tCitiesService.selectTCitiesByProvinceId(provinceId);
        return success(list);
    }

    /**
     * 获取城市管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tCitiesService.selectTCitiesById(id));
    }
}

