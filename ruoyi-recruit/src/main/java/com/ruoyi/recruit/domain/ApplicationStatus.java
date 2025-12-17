package com.ruoyi.recruit.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class ApplicationStatus extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 状态码：10 / 20 / 30 / 40 / 50 */
    private String code;

    /** 状态名称：已投递 / 面试邀请 / 通过 / 拒绝 */
    private String name;

    /** 状态说明 */
    private String detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
