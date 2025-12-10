package com.ruoyi.recruit.service;

import java.util.List;

import com.ruoyi.recruit.domain.CompanyAudit;
import com.ruoyi.recruit.domain.Users;

/**
 * 账号管理Service接口
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public interface IUsersService 
{
    /**
     * 查询账号管理
     * 
     * @param id 账号管理主键
     * @return 账号管理
     */
    public Users selectUsersById(Long id);

    /**
     * 查询账号管理列表
     * 
     * @param users 账号管理
     * @return 账号管理集合
     */
    public List<Users> selectUsersList(Users users);

    /**
     * 新增账号管理
     * 
     * @param users 账号管理
     * @return 结果
     */
    public int insertUsers(Users users);

    /**
     * 修改账号管理
     * 
     * @param users 账号管理
     * @return 结果
     */
    public int updateUsers(Users users);

    /**
     * 批量删除账号管理
     * 
     * @param ids 需要删除的账号管理主键集合
     * @return 结果
     */
    public int deleteUsersByIds(Long[] ids);

    /**
     * 删除账号管理信息
     * 
     * @param id 账号管理主键
     * @return 结果
     */
    public int deleteUsersById(Long id);

    /** 查询企业审核列表 */
    public List<CompanyAudit> selectCompanyAuditList(Users users);

    /** 查询详情 */
    public CompanyAudit selectCompanyAuditById(Long id);

    /** 审核用户状态 */
    public int updateUserStatus(Long userId, Integer status);

    /** 批量审核用户 */
    public void batchAuditUsers(List<Long> userIds, Integer status);

}
