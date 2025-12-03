package com.ruoyi.recruit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 行业信息管理对象 t_industries
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public class TIndustries extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 行业ID */
    @Excel(name = "行业ID")
    private Long id;

    /** 行业名称 */
    @Excel(name = "行业名称")
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
