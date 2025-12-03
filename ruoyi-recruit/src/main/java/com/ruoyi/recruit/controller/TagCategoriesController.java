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
import com.ruoyi.recruit.domain.TagCategories;
import com.ruoyi.recruit.service.ITagCategoriesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标签分类管理Controller
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@RestController
@RequestMapping("/recruit/tagCategories")
public class TagCategoriesController extends BaseController
{
    @Autowired
    private ITagCategoriesService tagCategoriesService;

    /**
     * 查询标签分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:tagCategories:list')")
    @GetMapping("/list")
    public TableDataInfo list(TagCategories tagCategories)
    {
        startPage();
        List<TagCategories> list = tagCategoriesService.selectTagCategoriesList(tagCategories);
        return getDataTable(list);
    }

    /**
     * 导出标签分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:tagCategories:export')")
    @Log(title = "标签分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TagCategories tagCategories)
    {
        List<TagCategories> list = tagCategoriesService.selectTagCategoriesList(tagCategories);
        ExcelUtil<TagCategories> util = new ExcelUtil<TagCategories>(TagCategories.class);
        util.exportExcel(response, list, "标签分类管理数据");
    }

    /**
     * 获取标签分类管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('recruit:tagCategories:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tagCategoriesService.selectTagCategoriesById(id));
    }

    /**
     * 新增标签分类管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:tagCategories:add')")
    @Log(title = "标签分类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TagCategories tagCategories)
    {
        return toAjax(tagCategoriesService.insertTagCategories(tagCategories));
    }

    /**
     * 修改标签分类管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:tagCategories:edit')")
    @Log(title = "标签分类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TagCategories tagCategories)
    {
        return toAjax(tagCategoriesService.updateTagCategories(tagCategories));
    }

    /**
     * 删除标签分类管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:tagCategories:remove')")
    @Log(title = "标签分类管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tagCategoriesService.deleteTagCategoriesByIds(ids));
    }
}
