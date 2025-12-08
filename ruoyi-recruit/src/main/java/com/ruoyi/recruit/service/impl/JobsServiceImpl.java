package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.JobsMapper;
import com.ruoyi.recruit.domain.Jobs;
import com.ruoyi.recruit.domain.Companies;
import com.ruoyi.recruit.service.IJobsService;
import com.ruoyi.recruit.service.ICompaniesService;
import com.ruoyi.recruit.service.ITProvincesService;
import com.ruoyi.recruit.service.ITCitiesService;
import com.ruoyi.recruit.domain.TProvinces;
import com.ruoyi.recruit.domain.TCities;
import com.ruoyi.common.utils.SecurityUtils;

/**
 * 岗位管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-01
 */
@Service
public class JobsServiceImpl implements IJobsService 
{
    @Autowired
    private JobsMapper jobsMapper;

    @Autowired
    private ICompaniesService companiesService;

    @Autowired
    private ITProvincesService tProvincesService;

    @Autowired
    private ITCitiesService tCitiesService;

    /**
     * 查询岗位管理
     * 
     * @param id 岗位管理主键
     * @return 岗位管理
     */
    @Override
    public Jobs selectJobsById(Long id)
    {
        Jobs jobs = jobsMapper.selectJobsById(id);
        // 填充公司名称、省份名称、城市名称
        fillCompanyName(jobs);
        fillProvinceAndCityName(jobs);
        return jobs;
    }

    /**
     * 查询岗位管理列表
     * 
     * @param jobs 岗位管理
     * @return 岗位管理
     */
    @Override
    public List<Jobs> selectJobsList(Jobs jobs)
    {
        List<Jobs> jobsList = jobsMapper.selectJobsList(jobs);
        // 填充公司名称、省份名称、城市名称
        if (jobsList != null && !jobsList.isEmpty()) {
            for (Jobs job : jobsList) {
                fillCompanyName(job);
                fillProvinceAndCityName(job);
            }
        }
        return jobsList;
    }

    /**
     * 填充公司名称
     * 根据companyId查询companies表获取company_name
     * 
     * @param jobs 岗位对象
     */
    private void fillCompanyName(Jobs jobs)
    {
        if (jobs != null && jobs.getCompanyId() != null) {
            Companies company = companiesService.selectCompaniesByCompanyId(jobs.getCompanyId());
            if (company != null) {
                jobs.setCompanyName(company.getCompanyName());
            }
        }
    }

    /**
     * 填充省份和城市名称
     * 根据provinceId和cityId查询获取省份和城市名称
     * 
     * @param jobs 岗位对象
     */
    private void fillProvinceAndCityName(Jobs jobs)
    {
        if (jobs != null) {
            // 填充省份名称
            if (jobs.getProvinceId() != null) {
                TProvinces province = tProvincesService.selectTProvincesById(jobs.getProvinceId());
                if (province != null) {
                    jobs.setProvinceName(province.getName());
                }
            }
            // 填充城市名称
            if (jobs.getCityId() != null) {
                TCities city = tCitiesService.selectTCitiesById(jobs.getCityId());
                if (city != null) {
                    jobs.setCityName(city.getName());
                }
            }
        }
    }

    /**
     * 新增岗位管理
     * 
     * @param jobs 岗位管理
     * @return 结果
     */
    @Override
    public int insertJobs(Jobs jobs)
    {
        // 如果提供了公司名称，根据公司名称查找companyId
        convertCompanyNameToId(jobs);
        // 如果没有设置发布用户ID，设置为当前登录用户ID
        if (jobs.getPostedByUserId() == null) {
            try {
                jobs.setPostedByUserId(SecurityUtils.getUserId());
            } catch (Exception e) {
                // 如果获取用户ID失败，抛出异常
                throw new RuntimeException("获取当前用户ID失败，无法创建岗位");
            }
        }
        // 如果没有设置浏览次数，默认为0
        if (jobs.getViewCount() == null) {
            jobs.setViewCount(0L);
        }
        return jobsMapper.insertJobs(jobs);
    }

    /**
     * 修改岗位管理
     * 
     * @param jobs 岗位管理
     * @return 结果
     */
    @Override
    public int updateJobs(Jobs jobs)
    {
        // 如果提供了公司名称，根据公司名称查找companyId（更换岗位所属公司）
        convertCompanyNameToId(jobs);
        return jobsMapper.updateJobs(jobs);
    }

    /**
     * 根据公司名称转换为companyId
     * 如果jobs中有companyName，则根据companyName查询获取companyId并更新
     * 
     * @param jobs 岗位对象
     */
    private void convertCompanyNameToId(Jobs jobs)
    {
        if (jobs != null && jobs.getCompanyName() != null && !jobs.getCompanyName().isEmpty()) {
            // 根据公司名称查找对应的companyId
            Companies company = companiesService.selectCompaniesByCompanyName(jobs.getCompanyName());
            if (company != null) {
                jobs.setCompanyId(company.getCompanyId());
            } else {
                throw new RuntimeException("公司名称不存在: " + jobs.getCompanyName());
            }
        }
    }

    /**
     * 批量删除岗位管理
     * 
     * @param ids 需要删除的岗位管理主键
     * @return 结果
     */
    @Override
    public int deleteJobsByIds(Long[] ids)
    {
        return jobsMapper.deleteJobsByIds(ids);
    }

    /**
     * 删除岗位管理信息
     * 
     * @param id 岗位管理主键
     * @return 结果
     */
    @Override
    public int deleteJobsById(Long id)
    {
        return jobsMapper.deleteJobsById(id);
    }
}
