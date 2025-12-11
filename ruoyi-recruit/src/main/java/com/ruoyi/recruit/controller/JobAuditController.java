package com.ruoyi.recruit.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.recruit.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.recruit.service.IJobAuditLogsService;
import com.ruoyi.recruit.service.IJobsService;

/**
 * 管理端 - 岗位审核控制器
 */
@RestController
@RequestMapping("/recruit/audit/job")
public class JobAuditController extends BaseController {

    @Autowired
    private IJobsService jobsService;

    @Autowired
    private IJobAuditLogsService jobAuditLogsService; // 需要注入日志Service

    // ... [保留原有的 list, getInfo, approve, reject 方法] ...

    /**
     * 1. 获取岗位审核列表 (返回 VO)
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:list')")
    @GetMapping("/list")
    public TableDataInfo list(Jobs jobs) {
        startPage();
        // 修改为调用查询 VO 的方法
        List<JobAuditVo> list = jobsService.selectJobAuditVoList(jobs);
        return getDataTable(list);
    }

    /**
     * 2. 查看岗位详情
     * 详情页通常需要完整字段（如描述、要求），继续使用 Entity 即可，
     * 也可以按需封装为 JobDetailVo。此处保持 Entity 以展示完整信息。
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        // 修改为调用 VO 查询方法
        JobDetailVo vo = jobsService.selectJobDetailVoById(id);
        return AjaxResult.success(vo);
    }
    /**
     * 3. 通过岗位审核 (已存在)
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:edit')")
    @Log(title = "岗位审核通过", businessType = BusinessType.UPDATE)
    @PutMapping("/approve/{jobId}")
    public AjaxResult approve(@PathVariable Long jobId, @RequestBody(required = false) AuditBody body) {
        String contact = (body != null) ? body.getOperatorContact() : null;
        String remark = (body != null && body.getRemark() != null) ? body.getRemark() : "审核通过";

        jobsService.auditJob(jobId, 20, remark, SecurityUtils.getUserId(), contact);
        return AjaxResult.success("审核通过成功");
    }

    /**
     * 4. 拒绝岗位审核 (已存在)
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:edit')")
    @Log(title = "岗位审核拒绝", businessType = BusinessType.UPDATE)
    @PutMapping("/reject/{jobId}")
    public AjaxResult reject(@PathVariable Long jobId, @RequestBody(required = false) AuditBody body) {
        String contact = (body != null) ? body.getOperatorContact() : null;
        String remark = (body != null && body.getRemark() != null) ? body.getRemark() : "审核拒绝";

        jobsService.auditJob(jobId, 30, remark, SecurityUtils.getUserId(), contact);
        return AjaxResult.success("已拒绝该岗位申请");
    }


    /**
     * 5. 批量通过岗位审核
     * Body: { "ids": [1, 2, 3] }
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:edit')")
    @Log(title = "岗位审核批量通过", businessType = BusinessType.UPDATE)
    @PutMapping("/batch/approve")
    public AjaxResult batchApprove(@RequestBody AuditBody body) {
        if (body.getJobIds() == null || body.getJobIds().isEmpty()) {
            return AjaxResult.error("请选择需要审核的岗位");
        }
        jobsService.batchAuditJobs(body.getJobIds(), 20, body.getRemark(),
                SecurityUtils.getUserId(), body.getOperatorContact());
        return AjaxResult.success("批量通过成功");
    }


    /**
     * 6. 批量拒绝岗位审核
     * Body: { "ids": [1, 2, 3], "auditRemark": "批量拒绝原因..." }
     * 注意：为了接收额外参数，建议使用 Map 或者专门的 DTO，这里为了简单演示，假设前端把 remark 放在 query 或者 body 结构调整
     * 简单做法：复用 Jobs 对象接收 ids 和 remark，或者定义一个 BatchAuditBody
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:edit')")
    @Log(title = "岗位审核批量拒绝", businessType = BusinessType.UPDATE)
    @PutMapping("/batch/reject")
    public AjaxResult batchReject(@RequestBody AuditBody body) {
        if (body.getJobIds() == null || body.getJobIds().isEmpty()) {
            return AjaxResult.error("请选择需要拒绝的岗位");
        }
        String remark = (body.getRemark() != null) ? body.getRemark() : "批量拒绝";
        jobsService.batchAuditJobs(body.getJobIds(), 30, remark,
                SecurityUtils.getUserId(), body.getOperatorContact());
        return AjaxResult.success("批量拒绝成功");
    }

    /**
     * 7. 导出岗位审核数据
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:export')")
    @Log(title = "岗位审核导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    // 修改点：添加 @RequestBody 注解以接收 JSON 参数
    public void export(HttpServletResponse response, @RequestBody Jobs jobs) {
        // 使用 VO 进行导出，查询逻辑会读取 jobs 中的 title, status 等过滤条件
        List<JobAuditVo> list = jobsService.selectJobAuditVoList(jobs);

        ExcelUtil<JobAuditVo> util = new ExcelUtil<>(JobAuditVo.class);
        util.exportExcel(response, list, "岗位审核数据");
    }


    /**
     * 8. 查看岗位审核历史记录（日志）
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:query')")
    @GetMapping("/{jobId}/logs")
    public AjaxResult history(@PathVariable Long jobId) {
        JobAuditLogs query = new JobAuditLogs();
        query.setJobId(jobId.intValue());
        List<JobAuditLogs> list = jobAuditLogsService.selectJobAuditLogsList(query);
        return AjaxResult.success(list);
    }

    // 内部类：用于接收批量拒绝参数
    public static class BatchAuditBody {
        private List<Long> jobIds;
        private String auditRemark;
        private String operatorContact;
        public String getOperatorContact() { return operatorContact; }
        public void setOperatorContact(String operatorContact) { this.operatorContact = operatorContact; }
        // Getters Setters
        public List<Long> getIds() { return jobIds; }
        public void setIds(List<Long> ids) { this.jobIds = ids; }
        public String getAuditRemark() { return auditRemark; }
        public void setAuditRemark(String auditRemark) { this.auditRemark = auditRemark; }
    }
}