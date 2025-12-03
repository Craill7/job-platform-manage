package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.TIndustriesMapper;
import com.ruoyi.recruit.domain.TIndustries;
import com.ruoyi.recruit.service.ITIndustriesService;

/**
 * 行业信息管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@Service
public class TIndustriesServiceImpl implements ITIndustriesService 
{
    @Autowired
    private TIndustriesMapper tIndustriesMapper;

    /**
     * 查询行业信息管理
     * 
     * @param id 行业信息管理主键
     * @return 行业信息管理
     */
    @Override
    public TIndustries selectTIndustriesById(Long id)
    {
        return tIndustriesMapper.selectTIndustriesById(id);
    }

    /**
     * 查询行业信息管理列表
     * 
     * @param tIndustries 行业信息管理
     * @return 行业信息管理
     */
    @Override
    public List<TIndustries> selectTIndustriesList(TIndustries tIndustries)
    {
        return tIndustriesMapper.selectTIndustriesList(tIndustries);
    }

    /**
     * 新增行业信息管理
     * 
     * @param tIndustries 行业信息管理
     * @return 结果
     */
    @Override
    public int insertTIndustries(TIndustries tIndustries)
    {
        return tIndustriesMapper.insertTIndustries(tIndustries);
    }

    /**
     * 修改行业信息管理
     * 
     * @param tIndustries 行业信息管理
     * @return 结果
     */
    @Override
    public int updateTIndustries(TIndustries tIndustries)
    {
        return tIndustriesMapper.updateTIndustries(tIndustries);
    }

    /**
     * 批量删除行业信息管理
     * 
     * @param ids 需要删除的行业信息管理主键
     * @return 结果
     */
    @Override
    public int deleteTIndustriesByIds(Long[] ids)
    {
        return tIndustriesMapper.deleteTIndustriesByIds(ids);
    }

    /**
     * 删除行业信息管理信息
     * 
     * @param id 行业信息管理主键
     * @return 结果
     */
    @Override
    public int deleteTIndustriesById(Long id)
    {
        return tIndustriesMapper.deleteTIndustriesById(id);
    }
}
