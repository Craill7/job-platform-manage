package com.ruoyi.recruit.service.impl;

import java.util.List;

import com.ruoyi.recruit.domain.ApplicationStatus;
import com.ruoyi.recruit.service.IApplicationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.ApplicationsMapper;
import com.ruoyi.recruit.domain.Applications;
import com.ruoyi.recruit.domain.Jobs;
import com.ruoyi.recruit.domain.Students;
import com.ruoyi.recruit.service.IApplicationsService;
import com.ruoyi.recruit.service.IJobsService;
import com.ruoyi.recruit.service.IStudentsService;

/**
 * 投递记录Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-04
 */
@Service
public class ApplicationsServiceImpl implements IApplicationsService 
{
    @Autowired
    private ApplicationsMapper applicationsMapper;

    @Autowired
    private IJobsService jobsService;

    @Autowired
    private IStudentsService studentsService;

    /**
     * 查询投递记录
     * 
     * @param id 投递记录主键
     * @return 投递记录
     */
    @Override
    public Applications selectApplicationsById(Long id)
    {
        Applications applications = applicationsMapper.selectApplicationsById(id);
        // 填充职位名称和投递人名称
        fillJobTitle(applications);
        fillStudentName(applications);
        fillStatusName(applications);
        return applications;
    }

    /**
     * 查询投递记录列表
     * 
     * @param applications 投递记录
     * @return 投递记录
     */
    @Override
    public List<Applications> selectApplicationsList(Applications applications)
    {
        List<Applications> applicationsList = applicationsMapper.selectApplicationsList(applications);
        // 填充职位名称和投递人名称
        if (applicationsList != null && !applicationsList.isEmpty()) {
            for (Applications application : applicationsList) {
                fillJobTitle(application);
                fillStudentName(application);
                fillStatusName(application);
            }
        }
        return applicationsList;
    }

    /**
     * 填充职位名称
     * 根据jobId查询jobs表获取title
     * 
     * @param applications 投递记录对象
     */
    private void fillJobTitle(Applications applications)
    {
        if (applications != null && applications.getJobId() != null) {
            Jobs job = jobsService.selectJobsById(applications.getJobId());
            if (job != null) {
                applications.setJobTitle(job.getTitle());
            }
        }
    }

    /**
     * 填充投递人名称
     * 根据studentUserId查询students表获取fullName
     * 
     * @param applications 投递记录对象
     */
    private void fillStudentName(Applications applications)
    {
        if (applications != null && applications.getStudentUserId() != null) {
            Students student = studentsService.selectStudentsByUserId(applications.getStudentUserId());
            if (student != null) {
                applications.setStudentName(student.getFullName());
            }
        }
    }

    /**
     * 新增投递记录
     * 
     * @param applications 投递记录
     * @return 结果
     */
    @Override
    public int insertApplications(Applications applications)
    {
        // 如果提供了职位名称，根据职位名称查找jobId
        convertJobTitleToId(applications);
        // 如果提供了投递人姓名，根据投递人姓名查找studentUserId
        convertStudentNameToUserId(applications);
        return applicationsMapper.insertApplications(applications);
    }

    /**
     * 修改投递记录
     * 
     * @param applications 投递记录
     * @return 结果
     */
    @Override
    public int updateApplications(Applications applications)
    {
        // 如果提供了职位名称，根据职位名称查找jobId
        convertJobTitleToId(applications);
        return applicationsMapper.updateApplications(applications);
    }

    /**
     * 根据职位名称转换为jobId
     * 如果applications中有jobTitle，则根据jobTitle查询获取jobId并更新
     * 
     * @param applications 投递记录对象
     */
    private void convertJobTitleToId(Applications applications)
    {
        if (applications != null && applications.getJobTitle() != null && !applications.getJobTitle().isEmpty()) {
            // 如果已经有jobId，不需要转换
            if (applications.getJobId() == null) {
                // 根据职位名称精确匹配查找
                Jobs searchJob = new Jobs();
                searchJob.setTitle(applications.getJobTitle());
                List<Jobs> jobsList = jobsService.selectJobsList(searchJob);
                if (jobsList != null && !jobsList.isEmpty()) {
                    // 优先查找精确匹配的职位
                    Jobs matchedJob = jobsList.stream()
                        .filter(job -> applications.getJobTitle().equals(job.getTitle()))
                        .findFirst()
                        .orElse(jobsList.get(0)); // 如果没有精确匹配，使用第一条
                    applications.setJobId(matchedJob.getId());
                } else {
                    throw new RuntimeException("职位名称不存在: " + applications.getJobTitle());
                }
            }
        }
    }

    /**
     * 根据投递人姓名转换为studentUserId
     * 如果applications中有studentName，则根据studentName查询获取studentUserId并更新
     * 注意：如果前端已经提供了studentUserId，则直接使用，不再根据姓名查找
     * 
     * @param applications 投递记录对象
     */
    private void convertStudentNameToUserId(Applications applications)
    {
        if (applications != null && applications.getStudentName() != null && !applications.getStudentName().isEmpty()) {
            // 如果已经有studentUserId，不需要转换（前端已选择）
            if (applications.getStudentUserId() == null) {
                // 根据投递人姓名精确匹配查找
                Students searchStudent = new Students();
                searchStudent.setFullName(applications.getStudentName());
                List<Students> studentsList = studentsService.selectStudentsList(searchStudent);
                if (studentsList != null && !studentsList.isEmpty()) {
                    // 优先查找精确匹配的投递人
                    Students matchedStudent = studentsList.stream()
                        .filter(student -> applications.getStudentName().equals(student.getFullName()))
                        .findFirst()
                        .orElse(null);
                    
                    if (matchedStudent != null) {
                        // 如果只有一个精确匹配，使用它
                        if (studentsList.stream().filter(s -> applications.getStudentName().equals(s.getFullName())).count() == 1) {
                            applications.setStudentUserId(matchedStudent.getUserId());
                        } else {
                            // 如果有多个重名，抛出异常提示前端必须选择具体的学生
                            throw new RuntimeException("存在多个同名投递人，请从下拉列表中选择具体的学生（学号或电话可区分）");
                        }
                    } else {
                        // 没有精确匹配，使用第一条
                        applications.setStudentUserId(studentsList.get(0).getUserId());
                    }
                } else {
                    throw new RuntimeException("投递人姓名不存在: " + applications.getStudentName());
                }
            }
        }
    }

    /**
     * 批量删除投递记录
     * 
     * @param ids 需要删除的投递记录主键
     * @return 结果
     */
    @Override
    public int deleteApplicationsByIds(Long[] ids)
    {
        return applicationsMapper.deleteApplicationsByIds(ids);
    }

    /**
     * 删除投递记录信息
     * 
     * @param id 投递记录主键
     * @return 结果
     */
    @Override
    public int deleteApplicationsById(Long id)
    {
        return applicationsMapper.deleteApplicationsById(id);
    }


    @Autowired
    private IApplicationStatusService applicationStatusService;
    private void fillStatusName(Applications applications) {
        if (applications != null && applications.getStatus() != null) {
            ApplicationStatus status =
                    applicationStatusService.selectByCode(
                            applications.getStatus().toString()
                    );
            if (status != null) {
                applications.setStatusName(status.getName());
            }
        }
    }

}
