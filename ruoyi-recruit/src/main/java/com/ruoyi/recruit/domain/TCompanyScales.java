package com.ruoyi.recruit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 规模信息管理对象 t_company_scales
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public class TCompanyScales extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公司规模 */
    @Excel(name = "公司规模")
    private Long id;

    /** 规模范围 */
    @Excel(name = "规模范围")
    private String scale;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setScale(String scale) 
    {
        this.scale = scale;
    }

    public String getScale() 
    {
        return scale;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("scale", getScale())
            .toString();
    }
}
