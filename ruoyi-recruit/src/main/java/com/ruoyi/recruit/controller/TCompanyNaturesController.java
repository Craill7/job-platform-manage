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
import com.ruoyi.recruit.domain.TCompanyNatures;
import com.ruoyi.recruit.service.ITCompanyNaturesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业性质管理Controller
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@RestController
@RequestMapping("/recruit/natures")
public class TCompanyNaturesController extends BaseController
{
    @Autowired
    private ITCompanyNaturesService tCompanyNaturesService;

    /**
     * 查询企业性质管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:natures:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCompanyNatures tCompanyNatures)
    {
        startPage();
        List<TCompanyNatures> list = tCompanyNaturesService.selectTCompanyNaturesList(tCompanyNatures);
        return getDataTable(list);
    }

    /**
     * 导出企业性质管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:natures:export')")
    @Log(title = "企业性质管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TCompanyNatures tCompanyNatures)
    {
        List<TCompanyNatures> list = tCompanyNaturesService.selectTCompanyNaturesList(tCompanyNatures);
        ExcelUtil<TCompanyNatures> util = new ExcelUtil<TCompanyNatures>(TCompanyNatures.class);
        util.exportExcel(response, list, "企业性质管理数据");
    }

    /**
     * 获取企业性质管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('recruit:natures:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tCompanyNaturesService.selectTCompanyNaturesById(id));
    }

    /**
     * 新增企业性质管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:natures:add')")
    @Log(title = "企业性质管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCompanyNatures tCompanyNatures)
    {
        return toAjax(tCompanyNaturesService.insertTCompanyNatures(tCompanyNatures));
    }

    /**
     * 修改企业性质管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:natures:edit')")
    @Log(title = "企业性质管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCompanyNatures tCompanyNatures)
    {
        return toAjax(tCompanyNaturesService.updateTCompanyNatures(tCompanyNatures));
    }

    /**
     * 删除企业性质管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:natures:remove')")
    @Log(title = "企业性质管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tCompanyNaturesService.deleteTCompanyNaturesByIds(ids));
    }
}
