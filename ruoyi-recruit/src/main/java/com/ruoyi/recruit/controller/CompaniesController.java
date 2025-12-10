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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.recruit.domain.Companies;
import com.ruoyi.recruit.service.ICompaniesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.io.FilenameUtils;
import java.io.File;

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

    @Autowired
    private ServerConfig serverConfig;

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
     * 根据公司名称模糊查询公司列表（用于下拉选择）
     */
    @GetMapping("/search")
    public AjaxResult searchCompanies(@RequestParam(value = "companyName", required = false) String companyName)
    {
        Companies companies = new Companies();
        if (companyName != null && !companyName.isEmpty()) {
            companies.setCompanyName(companyName);
        }
        List<Companies> list = companiesService.selectCompaniesList(companies);
        // 限制返回数量，最多返回10条
        if (list != null && list.size() > 10) {
            list = list.subList(0, 10);
        }
        return success(list);
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

    /**
     * 公司Logo上传
     */
    @Log(title = "公司Logo", businessType = BusinessType.UPDATE)
    @PostMapping("/logo")
    public AjaxResult uploadLogo(@RequestParam("file") MultipartFile file) throws Exception
    {
        try
        {
            if (!file.isEmpty())
            {
                // 校验文件类型
                FileUploadUtils.assertAllowed(file, MimeTypeUtils.IMAGE_EXTENSION);
                
                // 上传文件路径
                String filePath = RuoYiConfig.getCompanyLogoPath();
                
                // 生成文件名（UUID + 原文件扩展名，不包含日期路径）
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                if (extension == null || extension.isEmpty())
                {
                    extension = MimeTypeUtils.getExtension(file.getContentType());
                }
                String fileName = IdUtils.fastSimpleUUID() + "." + extension;
                
                // 确保目录存在
                File uploadDir = new File(filePath);
                if (!uploadDir.exists())
                {
                    uploadDir.mkdirs();
                }
                
                // 保存文件
                File destFile = new File(uploadDir, fileName);
                file.transferTo(destFile);
                
                // 返回相对路径，格式：/files/company-logo/文件名
                String relativePath = "/files/company-logo/" + fileName;
                String url = serverConfig.getUrl() + relativePath;
                AjaxResult ajax = AjaxResult.success();
                ajax.put("url", url);
                ajax.put("fileName", relativePath);
                ajax.put("newFileName", fileName);
                ajax.put("originalFilename", file.getOriginalFilename());
                return ajax;
            }
            return error("上传图片异常，请联系管理员");
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
