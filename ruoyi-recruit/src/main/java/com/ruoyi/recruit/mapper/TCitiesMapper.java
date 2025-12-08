package com.ruoyi.recruit.mapper;

import java.util.List;
import com.ruoyi.recruit.domain.TCities;

/**
 * 城市管理Mapper接口
 * 
 * @author Yihan
 * @date 2025-12-01
 */
public interface TCitiesMapper 
{
    /**
     * 查询城市管理
     * 
     * @param id 城市管理主键
     * @return 城市管理
     */
    public TCities selectTCitiesById(Long id);

    /**
     * 根据省份ID查询城市列表
     * 
     * @param provinceId 省份ID
     * @return 城市管理集合
     */
    public List<TCities> selectTCitiesByProvinceId(Long provinceId);

    /**
     * 查询城市管理列表
     * 
     * @param tCities 城市管理
     * @return 城市管理集合
     */
    public List<TCities> selectTCitiesList(TCities tCities);
}

