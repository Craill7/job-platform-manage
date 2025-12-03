package com.ruoyi.recruit.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 求职活动管理对象 events
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public class Events extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动ID */
    @Excel(name = "活动ID")
    private Long id;

    /** 发布管理员ID */
    private Long adminUserId;

    /** 活动标题 */
    @Excel(name = "活动标题")
    private String eventTitle;

    /** 活动详情 */
    private String eventSummary;

    /** 活动开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date eventStartTime;

    /** 活动结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date eventEndTime;

    /** 活动地点 */
    @Excel(name = "活动地点")
    private String eventLocation;

    /** 面向对象 */
    @Excel(name = "面向对象")
    private String targetAudience;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setAdminUserId(Long adminUserId) 
    {
        this.adminUserId = adminUserId;
    }

    public Long getAdminUserId() 
    {
        return adminUserId;
    }

    public void setEventTitle(String eventTitle) 
    {
        this.eventTitle = eventTitle;
    }

    public String getEventTitle() 
    {
        return eventTitle;
    }

    public void setEventSummary(String eventSummary) 
    {
        this.eventSummary = eventSummary;
    }

    public String getEventSummary() 
    {
        return eventSummary;
    }

    public void setEventStartTime(Date eventStartTime) 
    {
        this.eventStartTime = eventStartTime;
    }

    public Date getEventStartTime() 
    {
        return eventStartTime;
    }

    public void setEventEndTime(Date eventEndTime) 
    {
        this.eventEndTime = eventEndTime;
    }

    public Date getEventEndTime() 
    {
        return eventEndTime;
    }

    public void setEventLocation(String eventLocation) 
    {
        this.eventLocation = eventLocation;
    }

    public String getEventLocation() 
    {
        return eventLocation;
    }

    public void setTargetAudience(String targetAudience) 
    {
        this.targetAudience = targetAudience;
    }

    public String getTargetAudience() 
    {
        return targetAudience;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
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
            .append("adminUserId", getAdminUserId())
            .append("eventTitle", getEventTitle())
            .append("eventSummary", getEventSummary())
            .append("eventStartTime", getEventStartTime())
            .append("eventEndTime", getEventEndTime())
            .append("eventLocation", getEventLocation())
            .append("targetAudience", getTargetAudience())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
