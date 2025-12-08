package com.ruoyi.recruit.service;

import java.util.List;
import com.ruoyi.recruit.domain.TProvinces;

/**
 * 省份管理Service接口
 * 
 * @author Yihan
 * @date 2025-12-01
 */
public interface ITProvincesService 
{
    /**
     * 查询省份管理
     * 
     * @param id 省份管理主键
     * @return 省份管理
     */
    public TProvinces selectTProvincesById(Long id);

    /**
     * 查询省份管理列表
     * 
     * @param tProvinces 省份管理
     * @return 省份管理集合
     */
    public List<TProvinces> selectTProvincesList(TProvinces tProvinces);
}

