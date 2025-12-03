package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.TCompanyScalesMapper;
import com.ruoyi.recruit.domain.TCompanyScales;
import com.ruoyi.recruit.service.ITCompanyScalesService;

/**
 * 规模信息管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@Service
public class TCompanyScalesServiceImpl implements ITCompanyScalesService 
{
    @Autowired
    private TCompanyScalesMapper tCompanyScalesMapper;

    /**
     * 查询规模信息管理
     * 
     * @param id 规模信息管理主键
     * @return 规模信息管理
     */
    @Override
    public TCompanyScales selectTCompanyScalesById(Long id)
    {
        return tCompanyScalesMapper.selectTCompanyScalesById(id);
    }

    /**
     * 查询规模信息管理列表
     * 
     * @param tCompanyScales 规模信息管理
     * @return 规模信息管理
     */
    @Override
    public List<TCompanyScales> selectTCompanyScalesList(TCompanyScales tCompanyScales)
    {
        return tCompanyScalesMapper.selectTCompanyScalesList(tCompanyScales);
    }

    /**
     * 新增规模信息管理
     * 
     * @param tCompanyScales 规模信息管理
     * @return 结果
     */
    @Override
    public int insertTCompanyScales(TCompanyScales tCompanyScales)
    {
        return tCompanyScalesMapper.insertTCompanyScales(tCompanyScales);
    }

    /**
     * 修改规模信息管理
     * 
     * @param tCompanyScales 规模信息管理
     * @return 结果
     */
    @Override
    public int updateTCompanyScales(TCompanyScales tCompanyScales)
    {
        return tCompanyScalesMapper.updateTCompanyScales(tCompanyScales);
    }

    /**
     * 批量删除规模信息管理
     * 
     * @param ids 需要删除的规模信息管理主键
     * @return 结果
     */
    @Override
    public int deleteTCompanyScalesByIds(Long[] ids)
    {
        return tCompanyScalesMapper.deleteTCompanyScalesByIds(ids);
    }

    /**
     * 删除规模信息管理信息
     * 
     * @param id 规模信息管理主键
     * @return 结果
     */
    @Override
    public int deleteTCompanyScalesById(Long id)
    {
        return tCompanyScalesMapper.deleteTCompanyScalesById(id);
    }
}
