package com.ruoyi.recruit.mapper;

import java.util.List;
import com.ruoyi.recruit.domain.Users;

/**
 * 账号管理Mapper接口
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public interface UsersMapper 
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
     * 删除账号管理
     * 
     * @param id 账号管理主键
     * @return 结果
     */
    public int deleteUsersById(Long id);

    /**
     * 批量删除账号管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUsersByIds(Long[] ids);
}
