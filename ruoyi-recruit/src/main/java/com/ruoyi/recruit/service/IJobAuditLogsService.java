package com.ruoyi.recruit.service;

import com.ruoyi.recruit.domain.JobAuditLogs;

import java.util.List;

public interface IJobAuditLogsService {
    List<JobAuditLogs> selectJobAuditLogsList(JobAuditLogs jobAuditLogs);
}