package com.ruoyi.recruit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 省份管理对象 t_provinces
 * 
 * @author Yihan
 * @date 2025-12-01
 */
public class TProvinces extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 省份ID */
    @Excel(name = "省份ID")
    private Long id;

    /** 省份代码 */
    @Excel(name = "省份代码")
    private String code;

    /** 省份名称 */
    @Excel(name = "省份名称")
    private String name;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
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
            .append("code", getCode())
            .append("name", getName())
            .toString();
    }
}

