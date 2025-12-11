package com.ruoyi.recruit.service;

import java.util.List;

import com.ruoyi.recruit.domain.JobAuditVo;
import com.ruoyi.recruit.domain.JobDetailVo;
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

    /**
     * 查询岗位审核列表 VO
     */
    public List<JobAuditVo> selectJobAuditVoList(Jobs jobs);

    /**
     * 查询岗位详情 VO
     */
    public JobDetailVo selectJobDetailVoById(Long id);

    /**
     * 审核岗位
     * @param jobId 岗位ID
     * @param status 目标状态 (20=通过, 30=拒绝)
     * @param remark 审核备注
     * @param operatorId 操作人ID
     * @param operatorContact 操作人联系方式
     */
    public void auditJob(Long jobId, Integer status, String remark, Long operatorId, String operatorContact);
    /**
     * 批量审核岗位
     * * @param jobIds 岗位ID列表
     * @param status 目标状态
     * @param remark 备注
     * @param operatorId 操作人ID
     * @param operatorContact 操作人联系方式
     */
    public void batchAuditJobs(List<Long> jobIds, Integer status, String remark, Long operatorId, String operatorContact);
}
