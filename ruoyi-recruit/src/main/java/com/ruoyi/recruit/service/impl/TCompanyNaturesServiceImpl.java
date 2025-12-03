package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.TCompanyNaturesMapper;
import com.ruoyi.recruit.domain.TCompanyNatures;
import com.ruoyi.recruit.service.ITCompanyNaturesService;

/**
 * 企业性质管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@Service
public class TCompanyNaturesServiceImpl implements ITCompanyNaturesService 
{
    @Autowired
    private TCompanyNaturesMapper tCompanyNaturesMapper;

    /**
     * 查询企业性质管理
     * 
     * @param id 企业性质管理主键
     * @return 企业性质管理
     */
    @Override
    public TCompanyNatures selectTCompanyNaturesById(Long id)
    {
        return tCompanyNaturesMapper.selectTCompanyNaturesById(id);
    }

    /**
     * 查询企业性质管理列表
     * 
     * @param tCompanyNatures 企业性质管理
     * @return 企业性质管理
     */
    @Override
    public List<TCompanyNatures> selectTCompanyNaturesList(TCompanyNatures tCompanyNatures)
    {
        return tCompanyNaturesMapper.selectTCompanyNaturesList(tCompanyNatures);
    }

    /**
     * 新增企业性质管理
     * 
     * @param tCompanyNatures 企业性质管理
     * @return 结果
     */
    @Override
    public int insertTCompanyNatures(TCompanyNatures tCompanyNatures)
    {
        return tCompanyNaturesMapper.insertTCompanyNatures(tCompanyNatures);
    }

    /**
     * 修改企业性质管理
     * 
     * @param tCompanyNatures 企业性质管理
     * @return 结果
     */
    @Override
    public int updateTCompanyNatures(TCompanyNatures tCompanyNatures)
    {
        return tCompanyNaturesMapper.updateTCompanyNatures(tCompanyNatures);
    }

    /**
     * 批量删除企业性质管理
     * 
     * @param ids 需要删除的企业性质管理主键
     * @return 结果
     */
    @Override
    public int deleteTCompanyNaturesByIds(Long[] ids)
    {
        return tCompanyNaturesMapper.deleteTCompanyNaturesByIds(ids);
    }

    /**
     * 删除企业性质管理信息
     * 
     * @param id 企业性质管理主键
     * @return 结果
     */
    @Override
    public int deleteTCompanyNaturesById(Long id)
    {
        return tCompanyNaturesMapper.deleteTCompanyNaturesById(id);
    }
}
