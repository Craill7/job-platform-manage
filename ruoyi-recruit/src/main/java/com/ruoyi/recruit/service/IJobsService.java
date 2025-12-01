package com.ruoyi.recruit.service;

import java.util.List;
import com.ruoyi.recruit.domain.Jobs;

/**
 * 岗位管理Service接口
 * 
 * @author Yihan
 * @date 2025-12-01
 */
public interface IJobsService 
{
    /**
     * 查询岗位管理
     * 
     * @param id 岗位管理主键
     * @return 岗位管理
     */
    public Jobs selectJobsById(Long id);

    /**
     * 查询岗位管理列表
     * 
     * @param jobs 岗位管理
     * @return 岗位管理集合
     */
    public List<Jobs> selectJobsList(Jobs jobs);

    /**
     * 新增岗位管理
     * 
     * @param jobs 岗位管理
     * @return 结果
     */
    public int insertJobs(Jobs jobs);

    /**
     * 修改岗位管理
     * 
     * @param jobs 岗位管理
     * @return 结果
     */
    public int updateJobs(Jobs jobs);

    /**
     * 批量删除岗位管理
     * 
     * @param ids 需要删除的岗位管理主键集合
     * @return 结果
     */
    public int deleteJobsByIds(Long[] ids);

    /**
     * 删除岗位管理信息
     * 
     * @param id 岗位管理主键
     * @return 结果
     */
    public int deleteJobsById(Long id);
}
