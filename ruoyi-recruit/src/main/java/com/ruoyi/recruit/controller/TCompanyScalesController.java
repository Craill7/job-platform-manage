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
import com.ruoyi.recruit.domain.TCompanyScales;
import com.ruoyi.recruit.service.ITCompanyScalesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 规模信息管理Controller
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@RestController
@RequestMapping("/recruit/scales")
public class TCompanyScalesController extends BaseController
{
    @Autowired
    private ITCompanyScalesService tCompanyScalesService;

    /**
     * 查询规模信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:scales:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCompanyScales tCompanyScales)
    {
        startPage();
        List<TCompanyScales> list = tCompanyScalesService.selectTCompanyScalesList(tCompanyScales);
        return getDataTable(list);
    }

    /**
     * 导出规模信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:scales:export')")
    @Log(title = "规模信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TCompanyScales tCompanyScales)
    {
        List<TCompanyScales> list = tCompanyScalesService.selectTCompanyScalesList(tCompanyScales);
        ExcelUtil<TCompanyScales> util = new ExcelUtil<TCompanyScales>(TCompanyScales.class);
        util.exportExcel(response, list, "规模信息管理数据");
    }

    /**
     * 获取规模信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('recruit:scales:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tCompanyScalesService.selectTCompanyScalesById(id));
    }

    /**
     * 新增规模信息管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:scales:add')")
    @Log(title = "规模信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCompanyScales tCompanyScales)
    {
        return toAjax(tCompanyScalesService.insertTCompanyScales(tCompanyScales));
    }

    /**
     * 修改规模信息管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:scales:edit')")
    @Log(title = "规模信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCompanyScales tCompanyScales)
    {
        return toAjax(tCompanyScalesService.updateTCompanyScales(tCompanyScales));
    }

    /**
     * 删除规模信息管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:scales:remove')")
    @Log(title = "规模信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tCompanyScalesService.deleteTCompanyScalesByIds(ids));
    }
}
