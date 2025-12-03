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
import com.ruoyi.recruit.domain.Companies;
import com.ruoyi.recruit.service.ICompaniesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业管理Controller
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@RestController
@RequestMapping("/recruit/companies")
public class CompaniesController extends BaseController
{
    @Autowired
    private ICompaniesService companiesService;

    /**
     * 查询企业管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:companies:list')")
    @GetMapping("/list")
    public TableDataInfo list(Companies companies)
    {
        startPage();
        List<Companies> list = companiesService.selectCompaniesList(companies);
        return getDataTable(list);
    }

    /**
     * 导出企业管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:companies:export')")
    @Log(title = "企业管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Companies companies)
    {
        List<Companies> list = companiesService.selectCompaniesList(companies);
        ExcelUtil<Companies> util = new ExcelUtil<Companies>(Companies.class);
        util.exportExcel(response, list, "企业管理数据");
    }

    /**
     * 获取企业管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('recruit:companies:query')")
    @GetMapping(value = "/{companyId}")
    public AjaxResult getInfo(@PathVariable("companyId") Long companyId)
    {
        return success(companiesService.selectCompaniesByCompanyId(companyId));
    }

    /**
     * 新增企业管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:companies:add')")
    @Log(title = "企业管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Companies companies)
    {
        return toAjax(companiesService.insertCompanies(companies));
    }

    /**
     * 修改企业管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:companies:edit')")
    @Log(title = "企业管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Companies companies)
    {
        return toAjax(companiesService.updateCompanies(companies));
    }

    /**
     * 删除企业管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:companies:remove')")
    @Log(title = "企业管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{companyIds}")
    public AjaxResult remove(@PathVariable Long[] companyIds)
    {
        return toAjax(companiesService.deleteCompaniesByCompanyIds(companyIds));
    }
}
