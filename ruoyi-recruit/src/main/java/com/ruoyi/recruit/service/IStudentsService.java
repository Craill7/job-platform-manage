package com.ruoyi.recruit.service;

import java.util.List;
import com.ruoyi.recruit.domain.Students;

/**
 * 学生信息管理Service接口
 * 
 * @author Yihan
 * @date 2025-12-04
 */
public interface IStudentsService 
{
    /**
     * 查询学生信息管理
     * 
     * @param userId 学生信息管理主键
     * @return 学生信息管理
     */
    public Students selectStudentsByUserId(Long userId);

    /**
     * 查询学生信息管理列表
     * 
     * @param students 学生信息管理
     * @return 学生信息管理集合
     */
    public List<Students> selectStudentsList(Students students);

    /**
     * 新增学生信息管理
     * 
     * @param students 学生信息管理
     * @return 结果
     */
    public int insertStudents(Students students);

    /**
     * 修改学生信息管理
     * 
     * @param students 学生信息管理
     * @return 结果
     */
    public int updateStudents(Students students);

    /**
     * 批量删除学生信息管理
     * 
     * @param userIds 需要删除的学生信息管理主键集合
     * @return 结果
     */
    public int deleteStudentsByUserIds(Long[] userIds);

    /**
     * 删除学生信息管理信息
     * 
     * @param userId 学生信息管理主键
     * @return 结果
     */
    public int deleteStudentsByUserId(Long userId);
}
