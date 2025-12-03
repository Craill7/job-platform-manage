package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.TJobCategoriesMapper;
import com.ruoyi.recruit.domain.TJobCategories;
import com.ruoyi.recruit.service.ITJobCategoriesService;

/**
 * 岗位类别管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@Service
public class TJobCategoriesServiceImpl implements ITJobCategoriesService 
{
    @Autowired
    private TJobCategoriesMapper tJobCategoriesMapper;

    /**
     * 查询岗位类别管理
     * 
     * @param id 岗位类别管理主键
     * @return 岗位类别管理
     */
    @Override
    public TJobCategories selectTJobCategoriesById(Long id)
    {
        return tJobCategoriesMapper.selectTJobCategoriesById(id);
    }

    /**
     * 查询岗位类别管理列表
     * 
     * @param tJobCategories 岗位类别管理
     * @return 岗位类别管理
     */
    @Override
    public List<TJobCategories> selectTJobCategoriesList(TJobCategories tJobCategories)
    {
        return tJobCategoriesMapper.selectTJobCategoriesList(tJobCategories);
    }

    /**
     * 新增岗位类别管理
     * 
     * @param tJobCategories 岗位类别管理
     * @return 结果
     */
    @Override
    public int insertTJobCategories(TJobCategories tJobCategories)
    {
        return tJobCategoriesMapper.insertTJobCategories(tJobCategories);
    }

    /**
     * 修改岗位类别管理
     * 
     * @param tJobCategories 岗位类别管理
     * @return 结果
     */
    @Override
    public int updateTJobCategories(TJobCategories tJobCategories)
    {
        return tJobCategoriesMapper.updateTJobCategories(tJobCategories);
    }

    /**
     * 批量删除岗位类别管理
     * 
     * @param ids 需要删除的岗位类别管理主键
     * @return 结果
     */
    @Override
    public int deleteTJobCategoriesByIds(Long[] ids)
    {
        return tJobCategoriesMapper.deleteTJobCategoriesByIds(ids);
    }

    /**
     * 删除岗位类别管理信息
     * 
     * @param id 岗位类别管理主键
     * @return 结果
     */
    @Override
    public int deleteTJobCategoriesById(Long id)
    {
        return tJobCategoriesMapper.deleteTJobCategoriesById(id);
    }
}
