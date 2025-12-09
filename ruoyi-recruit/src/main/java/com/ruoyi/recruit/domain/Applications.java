package com.ruoyi.recruit.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投递记录对象 applications
 * 
 * @author Yihan
 * @date 2025-12-04
 */
public class Applications extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 投递记录ID */
    @Excel(name = "投递记录ID")
    private Long id;

    /** 投递职位 */
    @Excel(name = "投递职位")
    private Long jobId;

    /** 职位名称（用于显示，通过jobId关联查询获得） */
    private String jobTitle;

    /** 投递人 */
    @Excel(name = "投递人")
    private Long studentUserId;

    /** 投递人姓名（用于显示，通过studentUserId关联查询获得） */
    private String studentName;

    /** 简历 */
    private Long resumeId;

    /** 投递状态 */
    @Excel(name = "投递状态")
    private Long status;

    /** 投递时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投递时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittedAt;

    /** 状态更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "状态更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setJobId(Long jobId) 
    {
        this.jobId = jobId;
    }

    public Long getJobId() 
    {
        return jobId;
    }

    public void setJobTitle(String jobTitle) 
    {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() 
    {
        return jobTitle;
    }

    public void setStudentUserId(Long studentUserId) 
    {
        this.studentUserId = studentUserId;
    }

    public Long getStudentUserId() 
    {
        return studentUserId;
    }

    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }

    public void setResumeId(Long resumeId) 
    {
        this.resumeId = resumeId;
    }

    public Long getResumeId() 
    {
        return resumeId;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setSubmittedAt(Date submittedAt) 
    {
        this.submittedAt = submittedAt;
    }

    public Date getSubmittedAt() 
    {
        return submittedAt;
    }

    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("jobId", getJobId())
            .append("jobTitle", getJobTitle())
            .append("studentUserId", getStudentUserId())
            .append("studentName", getStudentName())
            .append("resumeId", getResumeId())
            .append("status", getStatus())
            .append("submittedAt", getSubmittedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
