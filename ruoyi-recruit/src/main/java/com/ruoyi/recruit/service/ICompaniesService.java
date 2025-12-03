package com.ruoyi.recruit.service;

import java.util.List;
import com.ruoyi.recruit.domain.Companies;

/**
 * 企业管理Service接口
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public interface ICompaniesService 
{
    /**
     * 查询企业管理
     * 
     * @param companyId 企业管理主键
     * @return 企业管理
     */
    public Companies selectCompaniesByCompanyId(Long companyId);

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
     * 批量删除企业管理
     * 
     * @param companyIds 需要删除的企业管理主键集合
     * @return 结果
     */
    public int deleteCompaniesByCompanyIds(Long[] companyIds);

    /**
     * 删除企业管理信息
     * 
     * @param companyId 企业管理主键
     * @return 结果
     */
    public int deleteCompaniesByCompanyId(Long companyId);
}
