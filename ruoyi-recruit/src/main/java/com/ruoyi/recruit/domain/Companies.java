package com.ruoyi.recruit.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业管理对象 companies
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public class Companies extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公司ID */
    @Excel(name = "公司ID")
    private Long companyId;

    /** 关联用户ID */
    private Long userId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 公司介绍 */
    private String description;

    /** 公司Logo */
    @Excel(name = "公司Logo")
    private String logoUrl;

    /** 行业领域 */
    @Excel(name = "行业领域")
    private Long industryId;

    /** 企业性质 */
    @Excel(name = "企业性质")
    private Long natureId;

    /** 公司规模 */
    @Excel(name = "公司规模")
    private Long companyScaleId;

    /** 行业领域名称 */
    private String industryName;

    /** 企业性质名称 */
    private String natureName;

    /** 公司规模名称 */
    private String companyScaleName;

    /** 公司地址 */
    private String companyAddress;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactPersonName;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String contactPersonPhone;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setLogoUrl(String logoUrl) 
    {
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() 
    {
        return logoUrl;
    }

    public void setIndustryId(Long industryId) 
    {
        this.industryId = industryId;
    }

    public Long getIndustryId() 
    {
        return industryId;
    }

    public void setNatureId(Long natureId) 
    {
        this.natureId = natureId;
    }

    public Long getNatureId() 
    {
        return natureId;
    }

    public void setCompanyScaleId(Long companyScaleId) 
    {
        this.companyScaleId = companyScaleId;
    }

    public Long getCompanyScaleId() 
    {
        return companyScaleId;
    }

    public void setIndustryName(String industryName) 
    {
        this.industryName = industryName;
    }

    public String getIndustryName() 
    {
        return industryName;
    }

    public void setNatureName(String natureName) 
    {
        this.natureName = natureName;
    }

    public String getNatureName() 
    {
        return natureName;
    }

    public void setCompanyScaleName(String companyScaleName) 
    {
        this.companyScaleName = companyScaleName;
    }

    public String getCompanyScaleName() 
    {
        return companyScaleName;
    }

    public void setCompanyAddress(String companyAddress) 
    {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress() 
    {
        return companyAddress;
    }

    public void setContactPersonName(String contactPersonName) 
    {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonName() 
    {
        return contactPersonName;
    }

    public void setContactPersonPhone(String contactPersonPhone) 
    {
        this.contactPersonPhone = contactPersonPhone;
    }

    public String getContactPersonPhone() 
    {
        return contactPersonPhone;
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
            .append("companyId", getCompanyId())
            .append("userId", getUserId())
            .append("companyName", getCompanyName())
            .append("description", getDescription())
            .append("logoUrl", getLogoUrl())
            .append("industryId", getIndustryId())
            .append("natureId", getNatureId())
            .append("companyScaleId", getCompanyScaleId())
            .append("industryName", getIndustryName())
            .append("natureName", getNatureName())
            .append("companyScaleName", getCompanyScaleName())
            .append("companyAddress", getCompanyAddress())
            .append("contactPersonName", getContactPersonName())
            .append("contactPersonPhone", getContactPersonPhone())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
