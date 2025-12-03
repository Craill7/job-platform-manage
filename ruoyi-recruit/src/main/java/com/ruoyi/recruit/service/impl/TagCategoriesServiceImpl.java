package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.TagCategoriesMapper;
import com.ruoyi.recruit.domain.TagCategories;
import com.ruoyi.recruit.service.ITagCategoriesService;

/**
 * 标签分类管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@Service
public class TagCategoriesServiceImpl implements ITagCategoriesService 
{
    @Autowired
    private TagCategoriesMapper tagCategoriesMapper;

    /**
     * 查询标签分类管理
     * 
     * @param id 标签分类管理主键
     * @return 标签分类管理
     */
    @Override
    public TagCategories selectTagCategoriesById(Long id)
    {
        return tagCategoriesMapper.selectTagCategoriesById(id);
    }

    /**
     * 查询标签分类管理列表
     * 
     * @param tagCategories 标签分类管理
     * @return 标签分类管理
     */
    @Override
    public List<TagCategories> selectTagCategoriesList(TagCategories tagCategories)
    {
        return tagCategoriesMapper.selectTagCategoriesList(tagCategories);
    }

    /**
     * 新增标签分类管理
     * 
     * @param tagCategories 标签分类管理
     * @return 结果
     */
    @Override
    public int insertTagCategories(TagCategories tagCategories)
    {
        return tagCategoriesMapper.insertTagCategories(tagCategories);
    }

    /**
     * 修改标签分类管理
     * 
     * @param tagCategories 标签分类管理
     * @return 结果
     */
    @Override
    public int updateTagCategories(TagCategories tagCategories)
    {
        return tagCategoriesMapper.updateTagCategories(tagCategories);
    }

    /**
     * 批量删除标签分类管理
     * 
     * @param ids 需要删除的标签分类管理主键
     * @return 结果
     */
    @Override
    public int deleteTagCategoriesByIds(Long[] ids)
    {
        return tagCategoriesMapper.deleteTagCategoriesByIds(ids);
    }

    /**
     * 删除标签分类管理信息
     * 
     * @param id 标签分类管理主键
     * @return 结果
     */
    @Override
    public int deleteTagCategoriesById(Long id)
    {
        return tagCategoriesMapper.deleteTagCategoriesById(id);
    }
}
