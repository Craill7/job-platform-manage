package com.ruoyi.recruit.service;

import java.util.List;
import com.ruoyi.recruit.domain.TIndustries;

/**
 * 行业信息管理Service接口
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public interface ITIndustriesService 
{
    /**
     * 查询行业信息管理
     * 
     * @param id 行业信息管理主键
     * @return 行业信息管理
     */
    public TIndustries selectTIndustriesById(Long id);

    /**
     * 查询行业信息管理列表
     * 
     * @param tIndustries 行业信息管理
     * @return 行业信息管理集合
     */
    public List<TIndustries> selectTIndustriesList(TIndustries tIndustries);

    /**
     * 新增行业信息管理
     * 
     * @param tIndustries 行业信息管理
     * @return 结果
     */
    public int insertTIndustries(TIndustries tIndustries);

    /**
     * 修改行业信息管理
     * 
     * @param tIndustries 行业信息管理
     * @return 结果
     */
    public int updateTIndustries(TIndustries tIndustries);

    /**
     * 批量删除行业信息管理
     * 
     * @param ids 需要删除的行业信息管理主键集合
     * @return 结果
     */
    public int deleteTIndustriesByIds(Long[] ids);

    /**
     * 删除行业信息管理信息
     * 
     * @param id 行业信息管理主键
     * @return 结果
     */
    public int deleteTIndustriesById(Long id);
}
