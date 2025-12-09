package com.ruoyi.recruit.service;

import java.util.Map;

/**
 * 首页概览Service接口
 * 
 * @author Yihan
 * @date 2025-12-12
 */
public interface IHomeService 
{
    /**
     * 获取首页概览数据
     * 
     * @return 概览数据
     */
    public Map<String, Object> getHomeOverview();
}

