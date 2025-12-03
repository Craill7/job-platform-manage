package com.ruoyi.recruit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业性质管理对象 t_company_natures
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public class TCompanyNatures extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 企业性质ID */
    @Excel(name = "企业性质ID")
    private Long id;

    /** 企业性质名称 */
    @Excel(name = "企业性质名称")
    private String name;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .toString();
    }
}
