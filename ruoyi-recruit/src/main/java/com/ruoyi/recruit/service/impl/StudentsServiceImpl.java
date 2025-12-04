package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.StudentsMapper;
import com.ruoyi.recruit.domain.Students;
import com.ruoyi.recruit.service.IStudentsService;

/**
 * 学生信息管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-04
 */
@Service
public class StudentsServiceImpl implements IStudentsService 
{
    @Autowired
    private StudentsMapper studentsMapper;

    /**
     * 查询学生信息管理
     * 
     * @param userId 学生信息管理主键
     * @return 学生信息管理
     */
    @Override
    public Students selectStudentsByUserId(Long userId)
    {
        return studentsMapper.selectStudentsByUserId(userId);
    }

    /**
     * 查询学生信息管理列表
     * 
     * @param students 学生信息管理
     * @return 学生信息管理
     */
    @Override
    public List<Students> selectStudentsList(Students students)
    {
        return studentsMapper.selectStudentsList(students);
    }

    /**
     * 新增学生信息管理
     * 
     * @param students 学生信息管理
     * @return 结果
     */
    @Override
    public int insertStudents(Students students)
    {
        return studentsMapper.insertStudents(students);
    }

    /**
     * 修改学生信息管理
     * 
     * @param students 学生信息管理
     * @return 结果
     */
    @Override
    public int updateStudents(Students students)
    {
        return studentsMapper.updateStudents(students);
    }

    /**
     * 批量删除学生信息管理
     * 
     * @param userIds 需要删除的学生信息管理主键
     * @return 结果
     */
    @Override
    public int deleteStudentsByUserIds(Long[] userIds)
    {
        return studentsMapper.deleteStudentsByUserIds(userIds);
    }

    /**
     * 删除学生信息管理信息
     * 
     * @param userId 学生信息管理主键
     * @return 结果
     */
    @Override
    public int deleteStudentsByUserId(Long userId)
    {
        return studentsMapper.deleteStudentsByUserId(userId);
    }
}
