package com.ruoyi.recruit.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import java.util.Date;

public class CompanyAudit {
/**
 * 企业账号审核列表视图对象
 */
    private static final long serialVersionUID = 1L;

    /** 用户ID (直接定义为 userId，无需 @JsonProperty 改名) */
    @Excel(name = "用户ID", cellType = Excel.ColumnType.NUMERIC)
    private Long userId;

    @Excel(name = "注册邮箱")
    private String email;

    /** 角色: 1=学生, 2=HR */
    @Excel(name = "用户角色", readConverterExp = "1=学生,2=HR")
    private Integer role;

    /** 状态: 0=待审核, 1=已通过, 2=已拒绝 */
    @Excel(name = "审核状态", readConverterExp = "0=待审核,1=已通过,2=已拒绝")
    private Integer status;

    /** 公司名称 (关联查询得出) */
    @Excel(name = "公司名称")
    private String companyName;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /** 审核时间 (根据逻辑计算得出) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date auditedAt;

    // ================= Getters & Setters =================
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getRole() { return role; }
    public void setRole(Integer role) { this.role = role; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getAuditedAt() { return auditedAt; }
    public void setAuditedAt(Date auditedAt) { this.auditedAt = auditedAt; }
}