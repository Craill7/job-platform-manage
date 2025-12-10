package com.ruoyi.recruit.service.impl;

import java.util.List;

import com.ruoyi.recruit.domain.CompanyAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.UsersMapper;
import com.ruoyi.recruit.domain.Users;
import com.ruoyi.recruit.service.IUsersService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账号管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@Service
public class UsersServiceImpl implements IUsersService 
{
    @Autowired
    private UsersMapper usersMapper;

    /**
     * 查询账号管理
     * 
     * @param id 账号管理主键
     * @return 账号管理
     */
    @Override
    public Users selectUsersById(Long id)
    {
        return usersMapper.selectUsersById(id);
    }

    /**
     * 查询账号管理列表
     * 
     * @param users 账号管理
     * @return 账号管理
     */
    @Override
    public List<Users> selectUsersList(Users users)
    {
        return usersMapper.selectUsersList(users);
    }

    /**
     * 新增账号管理
     * 
     * @param users 账号管理
     * @return 结果
     */
    @Override
    public int insertUsers(Users users)
    {
        return usersMapper.insertUsers(users);
    }

    /**
     * 修改账号管理
     * 
     * @param users 账号管理
     * @return 结果
     */
    @Override
    public int updateUsers(Users users)
    {
        return usersMapper.updateUsers(users);
    }

    /**
     * 批量删除账号管理
     * 
     * @param ids 需要删除的账号管理主键
     * @return 结果
     */
    @Override
    public int deleteUsersByIds(Long[] ids)
    {
        return usersMapper.deleteUsersByIds(ids);
    }

    /**
     * 删除账号管理信息
     * 
     * @param id 账号管理主键
     * @return 结果
     */
    @Override
    public int deleteUsersById(Long id)
    {
        return usersMapper.deleteUsersById(id);
    }

    @Override
    public List<CompanyAudit> selectCompanyAuditList(Users users) {
        return usersMapper.selectCompanyAuditList(users);
    }

    @Override
    public CompanyAudit selectCompanyAuditById(Long id) {
        return usersMapper.selectCompanyAuditById(id);
    }

    @Override
    public int updateUserStatus(Long userId, Integer status) {
        Users user = new Users();
        user.setId(userId);
        user.setStatus(status.longValue());
        return usersMapper.updateUsers(user);
    }

    @Override
    @Transactional
    public void batchAuditUsers(List<Long> userIds, Integer status) {
        for (Long id : userIds) {
            updateUserStatus(id, status);
        }
    }
}
