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
import com.ruoyi.recruit.domain.Applications;
import com.ruoyi.recruit.service.IApplicationsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 投递记录Controller
 * 
 * @author Yihan
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/recruit/applications")
public class ApplicationsController extends BaseController
{
    @Autowired
    private IApplicationsService applicationsService;

    /**
     * 查询投递记录列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:applications:list')")
    @GetMapping("/list")
    public TableDataInfo list(Applications applications)
    {
        startPage();
        List<Applications> list = applicationsService.selectApplicationsList(applications);
        return getDataTable(list);
    }

    /**
     * 导出投递记录列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:applications:export')")
    @Log(title = "投递记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Applications applications)
    {
        List<Applications> list = applicationsService.selectApplicationsList(applications);
        ExcelUtil<Applications> util = new ExcelUtil<Applications>(Applications.class);
        util.exportExcel(response, list, "投递记录数据");
    }

    /**
     * 获取投递记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('recruit:applications:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(applicationsService.selectApplicationsById(id));
    }

    /**
     * 新增投递记录
     */
    @PreAuthorize("@ss.hasPermi('recruit:applications:add')")
    @Log(title = "投递记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Applications applications)
    {
        return toAjax(applicationsService.insertApplications(applications));
    }

    /**
     * 修改投递记录
     */
    @PreAuthorize("@ss.hasPermi('recruit:applications:edit')")
    @Log(title = "投递记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Applications applications)
    {
        return toAjax(applicationsService.updateApplications(applications));
    }

    /**
     * 删除投递记录
     */
    @PreAuthorize("@ss.hasPermi('recruit:applications:remove')")
    @Log(title = "投递记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(applicationsService.deleteApplicationsByIds(ids));
    }
}
