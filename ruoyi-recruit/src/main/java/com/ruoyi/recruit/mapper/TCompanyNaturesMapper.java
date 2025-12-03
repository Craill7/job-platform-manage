package com.ruoyi.recruit.mapper;

import java.util.List;
import com.ruoyi.recruit.domain.TCompanyNatures;

/**
 * 企业性质管理Mapper接口
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public interface TCompanyNaturesMapper 
{
    /**
     * 查询企业性质管理
     * 
     * @param id 企业性质管理主键
     * @return 企业性质管理
     */
    public TCompanyNatures selectTCompanyNaturesById(Long id);

    /**
     * 查询企业性质管理列表
     * 
     * @param tCompanyNatures 企业性质管理
     * @return 企业性质管理集合
     */
    public List<TCompanyNatures> selectTCompanyNaturesList(TCompanyNatures tCompanyNatures);

    /**
     * 新增企业性质管理
     * 
     * @param tCompanyNatures 企业性质管理
     * @return 结果
     */
    public int insertTCompanyNatures(TCompanyNatures tCompanyNatures);

    /**
     * 修改企业性质管理
     * 
     * @param tCompanyNatures 企业性质管理
     * @return 结果
     */
    public int updateTCompanyNatures(TCompanyNatures tCompanyNatures);

    /**
     * 删除企业性质管理
     * 
     * @param id 企业性质管理主键
     * @return 结果
     */
    public int deleteTCompanyNaturesById(Long id);

    /**
     * 批量删除企业性质管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTCompanyNaturesByIds(Long[] ids);
}
