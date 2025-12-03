package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.CompaniesMapper;
import com.ruoyi.recruit.domain.Companies;
import com.ruoyi.recruit.service.ICompaniesService;

/**
 * 企业管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@Service
public class CompaniesServiceImpl implements ICompaniesService 
{
    @Autowired
    private CompaniesMapper companiesMapper;

    /**
     * 查询企业管理
     * 
     * @param companyId 企业管理主键
     * @return 企业管理
     */
    @Override
    public Companies selectCompaniesByCompanyId(Long companyId)
    {
        return companiesMapper.selectCompaniesByCompanyId(companyId);
    }

    /**
     * 查询企业管理列表
     * 
     * @param companies 企业管理
     * @return 企业管理
     */
    @Override
    public List<Companies> selectCompaniesList(Companies companies)
    {
        return companiesMapper.selectCompaniesList(companies);
    }

    /**
     * 新增企业管理
     * 
     * @param companies 企业管理
     * @return 结果
     */
    @Override
    public int insertCompanies(Companies companies)
    {
        return companiesMapper.insertCompanies(companies);
    }

    /**
     * 修改企业管理
     * 
     * @param companies 企业管理
     * @return 结果
     */
    @Override
    public int updateCompanies(Companies companies)
    {
        return companiesMapper.updateCompanies(companies);
    }

    /**
     * 批量删除企业管理
     * 
     * @param companyIds 需要删除的企业管理主键
     * @return 结果
     */
    @Override
    public int deleteCompaniesByCompanyIds(Long[] companyIds)
    {
        return companiesMapper.deleteCompaniesByCompanyIds(companyIds);
    }

    /**
     * 删除企业管理信息
     * 
     * @param companyId 企业管理主键
     * @return 结果
     */
    @Override
    public int deleteCompaniesByCompanyId(Long companyId)
    {
        return companiesMapper.deleteCompaniesByCompanyId(companyId);
    }
}
