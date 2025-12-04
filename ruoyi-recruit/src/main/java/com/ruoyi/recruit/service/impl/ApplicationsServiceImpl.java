package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.ApplicationsMapper;
import com.ruoyi.recruit.domain.Applications;
import com.ruoyi.recruit.service.IApplicationsService;

/**
 * 投递记录Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-04
 */
@Service
public class ApplicationsServiceImpl implements IApplicationsService 
{
    @Autowired
    private ApplicationsMapper applicationsMapper;

    /**
     * 查询投递记录
     * 
     * @param id 投递记录主键
     * @return 投递记录
     */
    @Override
    public Applications selectApplicationsById(Long id)
    {
        return applicationsMapper.selectApplicationsById(id);
    }

    /**
     * 查询投递记录列表
     * 
     * @param applications 投递记录
     * @return 投递记录
     */
    @Override
    public List<Applications> selectApplicationsList(Applications applications)
    {
        return applicationsMapper.selectApplicationsList(applications);
    }

    /**
     * 新增投递记录
     * 
     * @param applications 投递记录
     * @return 结果
     */
    @Override
    public int insertApplications(Applications applications)
    {
        return applicationsMapper.insertApplications(applications);
    }

    /**
     * 修改投递记录
     * 
     * @param applications 投递记录
     * @return 结果
     */
    @Override
    public int updateApplications(Applications applications)
    {
        return applicationsMapper.updateApplications(applications);
    }

    /**
     * 批量删除投递记录
     * 
     * @param ids 需要删除的投递记录主键
     * @return 结果
     */
    @Override
    public int deleteApplicationsByIds(Long[] ids)
    {
        return applicationsMapper.deleteApplicationsByIds(ids);
    }

    /**
     * 删除投递记录信息
     * 
     * @param id 投递记录主键
     * @return 结果
     */
    @Override
    public int deleteApplicationsById(Long id)
    {
        return applicationsMapper.deleteApplicationsById(id);
    }
}
