package com.ruoyi.recruit.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 岗位管理对象 jobs
 * 
 * @author Yihan
 * @date 2025-12-01
 */
public class Jobs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 岗位ID */
    @Excel(name = "岗位ID")
    private Long id;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private Long companyId;

    /** 所属部门 */
    @Excel(name = "所属部门")
    private String department;

    /** 岗位名称 */
    @Excel(name = "岗位名称")
    private String title;

    /** 岗位类别 */
    @Excel(name = "岗位类别")
    private Long type;

    /** 岗位描述 */
    private String description;

    /** 岗位要求 */
    private String techRequirements;

    /** 岗位加分项 */
    private String bonusPoints;

    /** 最少薪资(k) */
    @Excel(name = "最少薪资(k)")
    private Long minSalary;

    /** 最多薪资(k) */
    @Excel(name = "最多薪资(k)")
    private Long maxSalary;

    /** 省份 */
    private Long provinceId;

    /** 城市 */
    private Long cityId;

    /** 详细地址 */
    private String addressDetail;

    /** 岗位性质 */
    @Excel(name = "岗位性质")
    private Long workNature;

    /** 学历要求 */
    @Excel(name = "学历要求")
    private Long requiredDegree;

    /** 招聘人数 */
    @Excel(name = "招聘人数")
    private Long headcount;

    /** 要求入职时间 */
    private Date requiredStartDate;

    /** 招聘截止日期 */
    private Date deadline;

    /** 创建时间 */
    private Date createdAt;

    /** 更新时间 */
    private Date updatedAt;

    /** 岗位状态 */
    @Excel(name = "岗位状态")
    private Long status;

    /** 岗位浏览次数 */
    @Excel(name = "岗位浏览次数")
    private Long viewCount;

    /** 关联users.id */
    private Long postedByUserId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }

    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setTechRequirements(String techRequirements) 
    {
        this.techRequirements = techRequirements;
    }

    public String getTechRequirements() 
    {
        return techRequirements;
    }

    public void setBonusPoints(String bonusPoints) 
    {
        this.bonusPoints = bonusPoints;
    }

    public String getBonusPoints() 
    {
        return bonusPoints;
    }

    public void setMinSalary(Long minSalary) 
    {
        this.minSalary = minSalary;
    }

    public Long getMinSalary() 
    {
        return minSalary;
    }

    public void setMaxSalary(Long maxSalary) 
    {
        this.maxSalary = maxSalary;
    }

    public Long getMaxSalary() 
    {
        return maxSalary;
    }

    public void setProvinceId(Long provinceId) 
    {
        this.provinceId = provinceId;
    }

    public Long getProvinceId() 
    {
        return provinceId;
    }

    public void setCityId(Long cityId) 
    {
        this.cityId = cityId;
    }

    public Long getCityId() 
    {
        return cityId;
    }

    public void setAddressDetail(String addressDetail) 
    {
        this.addressDetail = addressDetail;
    }

    public String getAddressDetail() 
    {
        return addressDetail;
    }

    public void setWorkNature(Long workNature) 
    {
        this.workNature = workNature;
    }

    public Long getWorkNature() 
    {
        return workNature;
    }

    public void setRequiredDegree(Long requiredDegree) 
    {
        this.requiredDegree = requiredDegree;
    }

    public Long getRequiredDegree() 
    {
        return requiredDegree;
    }

    public void setHeadcount(Long headcount) 
    {
        this.headcount = headcount;
    }

    public Long getHeadcount() 
    {
        return headcount;
    }

    public void setRequiredStartDate(Date requiredStartDate) 
    {
        this.requiredStartDate = requiredStartDate;
    }

    public Date getRequiredStartDate() 
    {
        return requiredStartDate;
    }

    public void setDeadline(Date deadline) 
    {
        this.deadline = deadline;
    }

    public Date getDeadline() 
    {
        return deadline;
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

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setViewCount(Long viewCount) 
    {
        this.viewCount = viewCount;
    }

    public Long getViewCount() 
    {
        return viewCount;
    }

    public void setPostedByUserId(Long postedByUserId) 
    {
        this.postedByUserId = postedByUserId;
    }

    public Long getPostedByUserId() 
    {
        return postedByUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("department", getDepartment())
            .append("title", getTitle())
            .append("type", getType())
            .append("description", getDescription())
            .append("techRequirements", getTechRequirements())
            .append("bonusPoints", getBonusPoints())
            .append("minSalary", getMinSalary())
            .append("maxSalary", getMaxSalary())
            .append("provinceId", getProvinceId())
            .append("cityId", getCityId())
            .append("addressDetail", getAddressDetail())
            .append("workNature", getWorkNature())
            .append("requiredDegree", getRequiredDegree())
            .append("headcount", getHeadcount())
            .append("requiredStartDate", getRequiredStartDate())
            .append("deadline", getDeadline())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("status", getStatus())
            .append("viewCount", getViewCount())
            .append("postedByUserId", getPostedByUserId())
            .toString();
    }
}
