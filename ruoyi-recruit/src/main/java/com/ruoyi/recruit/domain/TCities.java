package com.ruoyi.recruit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 城市管理对象 t_cities
 * 
 * @author Yihan
 * @date 2025-12-01
 */
public class TCities extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 城市ID */
    @Excel(name = "城市ID")
    private Long id;

    /** 省份ID */
    @Excel(name = "省份ID")
    private Long provinceId;

    /** 城市代码 */
    @Excel(name = "城市代码")
    private String code;

    /** 城市名称 */
    @Excel(name = "城市名称")
    private String name;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setProvinceId(Long provinceId) 
    {
        this.provinceId = provinceId;
    }

    public Long getProvinceId() 
    {
        return provinceId;
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
            .append("provinceId", getProvinceId())
            .append("code", getCode())
            .append("name", getName())
            .toString();
    }
}

