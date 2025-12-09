package com.ruoyi.recruit.mapper;

import java.util.List;
import com.ruoyi.recruit.domain.JobAuditLogs;

/**
 * 岗位审核历史记录Mapper接口
 * * @author ruoyi
 * @date 2025-12-09
 */
public interface JobAuditLogsMapper
{
    /**
     * 查询岗位审核历史记录
     * * @param id 岗位审核历史记录主键
     * @return 岗位审核历史记录
     */
    public JobAuditLogs selectJobAuditLogsById(Long id);

    /**
     * 查询岗位审核历史记录列表
     * * @param jobAuditLogs 岗位审核历史记录
     * @return 岗位审核历史记录集合
     */
    public List<JobAuditLogs> selectJobAuditLogsList(JobAuditLogs jobAuditLogs);

    /**
     * 新增岗位审核历史记录
     * * @param jobAuditLogs 岗位审核历史记录
     * @return 结果
     */
    public int insertJobAuditLogs(JobAuditLogs jobAuditLogs);

    /**
     * 修改岗位审核历史记录
     * * @param jobAuditLogs 岗位审核历史记录
     * @return 结果
     */
    public int updateJobAuditLogs(JobAuditLogs jobAuditLogs);

    /**
     * 删除岗位审核历史记录
     * * @param id 岗位审核历史记录主键
     * @return 结果
     */
    public int deleteJobAuditLogsById(Long id);

    /**
     * 批量删除岗位审核历史记录
     * * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJobAuditLogsByIds(Long[] ids);
}