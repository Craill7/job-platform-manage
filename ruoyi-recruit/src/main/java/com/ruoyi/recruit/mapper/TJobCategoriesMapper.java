package com.ruoyi.recruit.mapper;

import java.util.List;
import com.ruoyi.recruit.domain.TJobCategories;

/**
 * 岗位类别管理Mapper接口
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public interface TJobCategoriesMapper 
{
    /**
     * 查询岗位类别管理
     * 
     * @param id 岗位类别管理主键
     * @return 岗位类别管理
     */
    public TJobCategories selectTJobCategoriesById(Long id);

    /**
     * 查询岗位类别管理列表
     * 
     * @param tJobCategories 岗位类别管理
     * @return 岗位类别管理集合
     */
    public List<TJobCategories> selectTJobCategoriesList(TJobCategories tJobCategories);

    /**
     * 新增岗位类别管理
     * 
     * @param tJobCategories 岗位类别管理
     * @return 结果
     */
    public int insertTJobCategories(TJobCategories tJobCategories);

    /**
     * 修改岗位类别管理
     * 
     * @param tJobCategories 岗位类别管理
     * @return 结果
     */
    public int updateTJobCategories(TJobCategories tJobCategories);

    /**
     * 删除岗位类别管理
     * 
     * @param id 岗位类别管理主键
     * @return 结果
     */
    public int deleteTJobCategoriesById(Long id);

    /**
     * 批量删除岗位类别管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTJobCategoriesByIds(Long[] ids);
}
