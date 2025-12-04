package com.ruoyi.recruit.service;

import java.util.List;
import com.ruoyi.recruit.domain.Applications;

/**
 * 投递记录Service接口
 * 
 * @author Yihan
 * @date 2025-12-04
 */
public interface IApplicationsService 
{
    /**
     * 查询投递记录
     * 
     * @param id 投递记录主键
     * @return 投递记录
     */
    public Applications selectApplicationsById(Long id);

    /**
     * 查询投递记录列表
     * 
     * @param applications 投递记录
     * @return 投递记录集合
     */
    public List<Applications> selectApplicationsList(Applications applications);

    /**
     * 新增投递记录
     * 
     * @param applications 投递记录
     * @return 结果
     */
    public int insertApplications(Applications applications);

    /**
     * 修改投递记录
     * 
     * @param applications 投递记录
     * @return 结果
     */
    public int updateApplications(Applications applications);

    /**
     * 批量删除投递记录
     * 
     * @param ids 需要删除的投递记录主键集合
     * @return 结果
     */
    public int deleteApplicationsByIds(Long[] ids);

    /**
     * 删除投递记录信息
     * 
     * @param id 投递记录主键
     * @return 结果
     */
    public int deleteApplicationsById(Long id);
}
