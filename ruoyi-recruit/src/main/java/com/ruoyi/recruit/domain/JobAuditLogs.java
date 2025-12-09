package com.ruoyi.recruit.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 岗位审核历史记录对象 job_audit_logs
 * * @author ruoyi
 * @date 2025-12-09
 */
public class JobAuditLogs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long id;

    /** 关联 jobs.id */
    @Excel(name = "岗位ID")
    private Integer jobId;

    /** 审核人ID (关联 sys_user.user_id) */
    @Excel(name = "审核人ID")
    private Long operatorId;

    /** 审核人联系方式 */
    @Excel(name = "审核人联系方式")
    private String operatorContact;

    /** 审核结果状态 (20=通过, 30=拒绝) */
    @Excel(name = "审核结果状态", readConverterExp = "20=通过,30=拒绝")
    private Integer auditStatus;

    /** 审核备注/拒绝原因 */
    @Excel(name = "审核备注")
    private String remark;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setJobId(Integer jobId)
    {
        this.jobId = jobId;
    }

    public Integer getJobId()
    {
        return jobId;
    }
    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId()
    {
        return operatorId;
    }
    public void setOperatorContact(String operatorContact)
    {
        this.operatorContact = operatorContact;
    }

    public String getOperatorContact()
    {
        return operatorContact;
    }
    public void setAuditStatus(Integer auditStatus)
    {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus()
    {
        return auditStatus;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRemark()
    {
        return remark;
    }
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("jobId", getJobId())
                .append("operatorId", getOperatorId())
                .append("operatorContact", getOperatorContact())
                .append("auditStatus", getAuditStatus())
                .append("remark", getRemark())
                .append("createdAt", getCreatedAt())
                .toString();
    }
}