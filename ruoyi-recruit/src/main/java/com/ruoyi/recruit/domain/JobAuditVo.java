package com.ruoyi.recruit.domain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
/**
 * 岗位审核列表视图对象
 */
public class JobAuditVo {

    private static final long serialVersionUID = 1L;

    /** 岗位ID */
    @Excel(name = "岗位ID", cellType = Excel.ColumnType.NUMERIC)
    private Long id;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 岗位名称 */
    @Excel(name = "岗位名称")
    private String title;

    /** 岗位类别名称 */
    private String typeName;

    /** 工作地点 (省份+城市) */
    @Excel(name = "工作地点")
    private String workLocation;

    /** 最少薪资(k) */
    private Long minSalary;

    /** 最多薪资(k) */
    private Long maxSalary;

    /** 岗位性质 */
    @Excel(name = "岗位性质", readConverterExp = "1=全职,2=实习,3=兼职")
    private Long workNature;

    /** 状态 */
    @Excel(name = "状态", readConverterExp = "10=待审核,20=审核通过,30=审核拒绝")
    private Long status;

    /** 发布人 (邮箱/用户名) */
    @Excel(name = "发布人")
    private String postedByUserName;

    /** 创建时间 (发布时间) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /** 最近审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date auditedAt;

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getWorkLocation() { return workLocation; }
    public void setWorkLocation(String workLocation) { this.workLocation = workLocation; }

    public Long getMinSalary() { return minSalary; }
    public void setMinSalary(Long minSalary) { this.minSalary = minSalary; }

    public Long getMaxSalary() { return maxSalary; }
    public void setMaxSalary(Long maxSalary) { this.maxSalary = maxSalary; }

    public Long getWorkNature() { return workNature; }
    public void setWorkNature(Long workNature) { this.workNature = workNature; }

    public Long getStatus() { return status; }
    public void setStatus(Long status) { this.status = status; }

    public String getPostedByUserName() { return postedByUserName; }
    public void setPostedByUserName(String postedByUserName) { this.postedByUserName = postedByUserName; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getAuditedAt() { return auditedAt; }
    public void setAuditedAt(Date auditedAt) { this.auditedAt = auditedAt; }
}