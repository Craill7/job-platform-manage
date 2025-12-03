package com.ruoyi.recruit.mapper;

import java.util.List;
import com.ruoyi.recruit.domain.TagCategories;

/**
 * 标签分类管理Mapper接口
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public interface TagCategoriesMapper 
{
    /**
     * 查询标签分类管理
     * 
     * @param id 标签分类管理主键
     * @return 标签分类管理
     */
    public TagCategories selectTagCategoriesById(Long id);

    /**
     * 查询标签分类管理列表
     * 
     * @param tagCategories 标签分类管理
     * @return 标签分类管理集合
     */
    public List<TagCategories> selectTagCategoriesList(TagCategories tagCategories);

    /**
     * 新增标签分类管理
     * 
     * @param tagCategories 标签分类管理
     * @return 结果
     */
    public int insertTagCategories(TagCategories tagCategories);

    /**
     * 修改标签分类管理
     * 
     * @param tagCategories 标签分类管理
     * @return 结果
     */
    public int updateTagCategories(TagCategories tagCategories);

    /**
     * 删除标签分类管理
     * 
     * @param id 标签分类管理主键
     * @return 结果
     */
    public int deleteTagCategoriesById(Long id);

    /**
     * 批量删除标签分类管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTagCategoriesByIds(Long[] ids);
}
