package com.ruoyi.recruit.service;

import java.util.List;
import com.ruoyi.recruit.domain.TCompanyScales;

/**
 * 规模信息管理Service接口
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public interface ITCompanyScalesService 
{
    /**
     * 查询规模信息管理
     * 
     * @param id 规模信息管理主键
     * @return 规模信息管理
     */
    public TCompanyScales selectTCompanyScalesById(Long id);

    /**
     * 查询规模信息管理列表
     * 
     * @param tCompanyScales 规模信息管理
     * @return 规模信息管理集合
     */
    public List<TCompanyScales> selectTCompanyScalesList(TCompanyScales tCompanyScales);

    /**
     * 新增规模信息管理
     * 
     * @param tCompanyScales 规模信息管理
     * @return 结果
     */
    public int insertTCompanyScales(TCompanyScales tCompanyScales);

    /**
     * 修改规模信息管理
     * 
     * @param tCompanyScales 规模信息管理
     * @return 结果
     */
    public int updateTCompanyScales(TCompanyScales tCompanyScales);

    /**
     * 批量删除规模信息管理
     * 
     * @param ids 需要删除的规模信息管理主键集合
     * @return 结果
     */
    public int deleteTCompanyScalesByIds(Long[] ids);

    /**
     * 删除规模信息管理信息
     * 
     * @param id 规模信息管理主键
     * @return 结果
     */
    public int deleteTCompanyScalesById(Long id);
}
