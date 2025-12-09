package com.ruoyi.recruit.mapper;

import java.util.List;
import com.ruoyi.recruit.domain.Companies;

/**
 * 企业管理Mapper接口
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public interface CompaniesMapper 
{
    /**
     * 查询企业管理
     * 
     * @param companyId 企业管理主键
     * @return 企业管理
     */
    public Companies selectCompaniesByCompanyId(Long companyId);

    /**
     * 根据公司名称查询企业管理
     * 
     * @param companyName 公司名称
     * @return 企业管理
     */
    public Companies selectCompaniesByCompanyName(String companyName);

    /**
     * 查询企业管理列表
     * 
     * @param companies 企业管理
     * @return 企业管理集合
     */
    public List<Companies> selectCompaniesList(Companies companies);

    /**
     * 新增企业管理
     * 
     * @param companies 企业管理
     * @return 结果
     */
    public int insertCompanies(Companies companies);

    /**
     * 修改企业管理
     * 
     * @param companies 企业管理
     * @return 结果
     */
    public int updateCompanies(Companies companies);

    /**
     * 删除企业管理
     * 
     * @param companyId 企业管理主键
     * @return 结果
     */
    public int deleteCompaniesByCompanyId(Long companyId);

    /**
     * 批量删除企业管理
     * 
     * @param companyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompaniesByCompanyIds(Long[] companyIds);

    /**
     * 统计企业数量
     * 
     * @return 数量
     */
    public int countCompanies();
}
