package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.TProvincesMapper;
import com.ruoyi.recruit.domain.TProvinces;
import com.ruoyi.recruit.service.ITProvincesService;

/**
 * 省份管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-01
 */
@Service
public class TProvincesServiceImpl implements ITProvincesService 
{
    @Autowired
    private TProvincesMapper tProvincesMapper;

    /**
     * 查询省份管理
     * 
     * @param id 省份管理主键
     * @return 省份管理
     */
    @Override
    public TProvinces selectTProvincesById(Long id)
    {
        return tProvincesMapper.selectTProvincesById(id);
    }

    /**
     * 查询省份管理列表
     * 
     * @param tProvinces 省份管理
     * @return 省份管理
     */
    @Override
    public List<TProvinces> selectTProvincesList(TProvinces tProvinces)
    {
        return tProvincesMapper.selectTProvincesList(tProvinces);
    }
}

