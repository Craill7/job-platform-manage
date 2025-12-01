package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.JobsMapper;
import com.ruoyi.recruit.domain.Jobs;
import com.ruoyi.recruit.service.IJobsService;

/**
 * 岗位管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-01
 */
@Service
public class JobsServiceImpl implements IJobsService 
{
    @Autowired
    private JobsMapper jobsMapper;

    /**
     * 查询岗位管理
     * 
     * @param id 岗位管理主键
     * @return 岗位管理
     */
    @Override
    public Jobs selectJobsById(Long id)
    {
        return jobsMapper.selectJobsById(id);
    }

    /**
     * 查询岗位管理列表
     * 
     * @param jobs 岗位管理
     * @return 岗位管理
     */
    @Override
    public List<Jobs> selectJobsList(Jobs jobs)
    {
        return jobsMapper.selectJobsList(jobs);
    }

    /**
     * 新增岗位管理
     * 
     * @param jobs 岗位管理
     * @return 结果
     */
    @Override
    public int insertJobs(Jobs jobs)
    {
        return jobsMapper.insertJobs(jobs);
    }

    /**
     * 修改岗位管理
     * 
     * @param jobs 岗位管理
     * @return 结果
     */
    @Override
    public int updateJobs(Jobs jobs)
    {
        return jobsMapper.updateJobs(jobs);
    }

    /**
     * 批量删除岗位管理
     * 
     * @param ids 需要删除的岗位管理主键
     * @return 结果
     */
    @Override
    public int deleteJobsByIds(Long[] ids)
    {
        return jobsMapper.deleteJobsByIds(ids);
    }

    /**
     * 删除岗位管理信息
     * 
     * @param id 岗位管理主键
     * @return 结果
     */
    @Override
    public int deleteJobsById(Long id)
    {
        return jobsMapper.deleteJobsById(id);
    }
}
