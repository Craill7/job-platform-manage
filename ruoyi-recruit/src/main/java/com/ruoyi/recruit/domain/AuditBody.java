package com.ruoyi.recruit.domain;

import java.util.List;

/**
 * 审核操作请求参数
 */
public class AuditBody {
    /** 岗位ID列表 (批量时用) */
    private List<Long> jobIds;

    /** 审核人联系方式 */
    private String operatorContact;

    /** 审核备注 */
    private String remark;

    // Getters & Setters
    public List<Long> getJobIds() { return jobIds; }
    public void setJobIds(List<Long> jobIds) { this.jobIds = jobIds; }
    public String getOperatorContact() { return operatorContact; }
    public void setOperatorContact(String operatorContact) { this.operatorContact = operatorContact; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}