package com.ruoyi.recruit.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生信息管理对象 students
 * 
 * @author Yihan
 * @date 2025-12-04
 */
public class Students extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 头像 */
    @Excel(name = "头像")
    private String avatarUrl;

    /** 姓名 */
    @Excel(name = "姓名")
    private String fullName;

    /** 性别 */
    @Excel(name = "性别")
    private Long gender;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 电话 */
    @Excel(name = "电话")
    private String phoneNumber;

    /** 生日 */
    private Date dateOfBirth;

    /** 求职状态 */
    @Excel(name = "求职状态")
    private Long jobSeekingStatus;

    /** 期望职位 */
    @Excel(name = "期望职位")
    private String expectedPosition;

    /** 期望最少薪资 */
    private Long expectedMinSalary;

    /** 期望最多薪资 */
    private Long expectedMaxSalary;

    /** 技能掌握 */
    private String skillsSummary;

    /** 当前选用的模板ID */
    private Long currentTemplateId;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setAvatarUrl(String avatarUrl) 
    {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() 
    {
        return avatarUrl;
    }

    public void setFullName(String fullName) 
    {
        this.fullName = fullName;
    }

    public String getFullName() 
    {
        return fullName;
    }

    public void setGender(Long gender) 
    {
        this.gender = gender;
    }

    public Long getGender() 
    {
        return gender;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public void setDateOfBirth(Date dateOfBirth) 
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() 
    {
        return dateOfBirth;
    }

    public void setJobSeekingStatus(Long jobSeekingStatus) 
    {
        this.jobSeekingStatus = jobSeekingStatus;
    }

    public Long getJobSeekingStatus() 
    {
        return jobSeekingStatus;
    }

    public void setExpectedPosition(String expectedPosition) 
    {
        this.expectedPosition = expectedPosition;
    }

    public String getExpectedPosition() 
    {
        return expectedPosition;
    }

    public void setExpectedMinSalary(Long expectedMinSalary) 
    {
        this.expectedMinSalary = expectedMinSalary;
    }

    public Long getExpectedMinSalary() 
    {
        return expectedMinSalary;
    }

    public void setExpectedMaxSalary(Long expectedMaxSalary) 
    {
        this.expectedMaxSalary = expectedMaxSalary;
    }

    public Long getExpectedMaxSalary() 
    {
        return expectedMaxSalary;
    }

    public void setSkillsSummary(String skillsSummary) 
    {
        this.skillsSummary = skillsSummary;
    }

    public String getSkillsSummary() 
    {
        return skillsSummary;
    }

    public void setCurrentTemplateId(Long currentTemplateId) 
    {
        this.currentTemplateId = currentTemplateId;
    }

    public Long getCurrentTemplateId() 
    {
        return currentTemplateId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("avatarUrl", getAvatarUrl())
            .append("fullName", getFullName())
            .append("gender", getGender())
            .append("studentId", getStudentId())
            .append("phoneNumber", getPhoneNumber())
            .append("dateOfBirth", getDateOfBirth())
            .append("jobSeekingStatus", getJobSeekingStatus())
            .append("expectedPosition", getExpectedPosition())
            .append("expectedMinSalary", getExpectedMinSalary())
            .append("expectedMaxSalary", getExpectedMaxSalary())
            .append("skillsSummary", getSkillsSummary())
            .append("currentTemplateId", getCurrentTemplateId())
            .toString();
    }
}
