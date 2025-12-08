package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.TCitiesMapper;
import com.ruoyi.recruit.domain.TCities;
import com.ruoyi.recruit.service.ITCitiesService;

/**
 * 城市管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-01
 */
@Service
public class TCitiesServiceImpl implements ITCitiesService 
{
    @Autowired
    private TCitiesMapper tCitiesMapper;

    /**
     * 查询城市管理
     * 
     * @param id 城市管理主键
     * @return 城市管理
     */
    @Override
    public TCities selectTCitiesById(Long id)
    {
        return tCitiesMapper.selectTCitiesById(id);
    }

    /**
     * 根据省份ID查询城市列表
     * 
     * @param provinceId 省份ID
     * @return 城市管理集合
     */
    @Override
    public List<TCities> selectTCitiesByProvinceId(Long provinceId)
    {
        return tCitiesMapper.selectTCitiesByProvinceId(provinceId);
    }

    /**
     * 查询城市管理列表
     * 
     * @param tCities 城市管理
     * @return 城市管理
     */
    @Override
    public List<TCities> selectTCitiesList(TCities tCities)
    {
        return tCitiesMapper.selectTCitiesList(tCities);
    }
}

