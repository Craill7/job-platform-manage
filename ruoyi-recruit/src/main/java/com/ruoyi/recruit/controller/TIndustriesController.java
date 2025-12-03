package com.ruoyi.recruit.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.recruit.domain.TIndustries;
import com.ruoyi.recruit.service.ITIndustriesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 行业信息管理Controller
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@RestController
@RequestMapping("/recruit/industries")
public class TIndustriesController extends BaseController
{
    @Autowired
    private ITIndustriesService tIndustriesService;

    /**
     * 查询行业信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:industries:list')")
    @GetMapping("/list")
    public TableDataInfo list(TIndustries tIndustries)
    {
        startPage();
        List<TIndustries> list = tIndustriesService.selectTIndustriesList(tIndustries);
        return getDataTable(list);
    }

    /**
     * 导出行业信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:industries:export')")
    @Log(title = "行业信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TIndustries tIndustries)
    {
        List<TIndustries> list = tIndustriesService.selectTIndustriesList(tIndustries);
        ExcelUtil<TIndustries> util = new ExcelUtil<TIndustries>(TIndustries.class);
        util.exportExcel(response, list, "行业信息管理数据");
    }

    /**
     * 获取行业信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('recruit:industries:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tIndustriesService.selectTIndustriesById(id));
    }

    /**
     * 新增行业信息管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:industries:add')")
    @Log(title = "行业信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TIndustries tIndustries)
    {
        return toAjax(tIndustriesService.insertTIndustries(tIndustries));
    }

    /**
     * 修改行业信息管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:industries:edit')")
    @Log(title = "行业信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TIndustries tIndustries)
    {
        return toAjax(tIndustriesService.updateTIndustries(tIndustries));
    }

    /**
     * 删除行业信息管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:industries:remove')")
    @Log(title = "行业信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tIndustriesService.deleteTIndustriesByIds(ids));
    }
}
