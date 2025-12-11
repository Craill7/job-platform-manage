package com.ruoyi.recruit.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 岗位详情视图对象
 */
public class JobDetailVo
{
    private static final long serialVersionUID = 1L;

    /** 岗位ID */
    private Long id;

    /** 企业名称 */
    private String companyName;

    /** 岗位名称 */
    private String title;

    /** 岗位类别名称 */
    private String typeName;

    /** 所属部门 */
    private String department;

    /** 最低薪资（k） */
    private Long minSalary;

    /** 最高薪资（k） */
    private Long maxSalary;

    /** 工作详细地点 */
    // 使用 @JsonProperty 确保返回的 JSON key 为 "address_detail"
    @JsonProperty("address_detail")
    private String addressDetail;

    /** 岗位性质（1=实习，2=全职） */
    private Long workNature;

    /** 学历要求（0=本科及以上...） */
    private Long requiredDegree;

    /** 招聘人数 */
    private Long headcount;

    /** 招聘截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    /** 岗位描述 */
    private String description;

    /** 岗位要求 */
    private String techRequirements;

    /** 岗位加分项 */
    private String bonusPoints;

    /** 提交人姓名 */
    private String postedByUserName;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /** 当前状态 */
    private Long status;

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Long getMinSalary() { return minSalary; }
    public void setMinSalary(Long minSalary) { this.minSalary = minSalary; }

    public Long getMaxSalary() { return maxSalary; }
    public void setMaxSalary(Long maxSalary) { this.maxSalary = maxSalary; }

    public String getAddressDetail() { return addressDetail; }
    public void setAddressDetail(String addressDetail) { this.addressDetail = addressDetail; }

    public Long getWorkNature() { return workNature; }
    public void setWorkNature(Long workNature) { this.workNature = workNature; }

    public Long getRequiredDegree() { return requiredDegree; }
    public void setRequiredDegree(Long requiredDegree) { this.requiredDegree = requiredDegree; }

    public Long getHeadcount() { return headcount; }
    public void setHeadcount(Long headcount) { this.headcount = headcount; }

    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) { this.deadline = deadline; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTechRequirements() { return techRequirements; }
    public void setTechRequirements(String techRequirements) { this.techRequirements = techRequirements; }

    public String getBonusPoints() { return bonusPoints; }
    public void setBonusPoints(String bonusPoints) { this.bonusPoints = bonusPoints; }

    public String getPostedByUserName() { return postedByUserName; }
    public void setPostedByUserName(String postedByUserName) { this.postedByUserName = postedByUserName; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Long getStatus() { return status; }
    public void setStatus(Long status) { this.status = status; }
}