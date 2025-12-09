package com.ruoyi.recruit.service.impl;

import com.ruoyi.recruit.domain.JobAuditLogs;
import com.ruoyi.recruit.mapper.JobAuditLogsMapper;
import com.ruoyi.recruit.service.IJobAuditLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAuditLogsServiceImpl implements IJobAuditLogsService {
    @Autowired
    private JobAuditLogsMapper jobAuditLogsMapper;

    @Override
    public List<JobAuditLogs> selectJobAuditLogsList(JobAuditLogs jobAuditLogs) {
        return jobAuditLogsMapper.selectJobAuditLogsList(jobAuditLogs);
    }
}
