package com.ruoyi.recruit.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.recruit.domain.Users;
import com.ruoyi.recruit.domain.CompanyAudit; // 引入 VO
import com.ruoyi.recruit.service.IUsersService;

@RestController
@RequestMapping("/recruit/audit/company")
public class CompanyAuditController extends BaseController {

    @Autowired
    private IUsersService usersService;

    /**
     * 1. 获取企业账号审核列表
     * GET /recruit/audit/company/list
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(Users users) {
        // Tips: 前端传来的 companyName 会自动封装到 users.params 中，
        // XML 里一定要用 #{params.companyName} 来接收
        startPage();
        List<CompanyAudit> list = usersService.selectCompanyAuditList(users);
        return getDataTable(list);
    }

    /**
     * 2. 查看企业账号详情
     * GET /recruit/audit/company/{userId}
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:company:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId) {
        CompanyAudit vo = usersService.selectCompanyAuditById(userId);
        return AjaxResult.success(vo);
    }

    /**
     * 3. 通过企业账号审核
     * PUT /recruit/audit/company/approve/{userId}
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:company:edit')")
    @Log(title = "企业审核通过", businessType = BusinessType.UPDATE)
    @PutMapping("/approve/{userId}")
    public AjaxResult approve(@PathVariable Long userId) {
        usersService.updateUserStatus(userId, 1); // 1=Approved
        return AjaxResult.success("审核通过成功");
    }

    /**
     * 4. 拒绝企业账号审核
     * PUT /recruit/audit/company/reject/{userId}
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:company:edit')")
    @Log(title = "企业审核拒绝", businessType = BusinessType.UPDATE)
    @PutMapping("/reject/{userId}")
    public AjaxResult reject(@PathVariable Long userId) {
        usersService.updateUserStatus(userId, 2); // 2=Rejected
        return AjaxResult.success("已拒绝该企业账号");
    }

    /**
     * 5. 批量通过
     * Body: { "userIds": [1, 2, 3] }
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:company:edit')")
    @Log(title = "企业审核批量通过", businessType = BusinessType.UPDATE)
    @PutMapping("/batch/approve")
    public AjaxResult batchApprove(@RequestBody BatchAuditBody body) {
        if (body.getUserIds() == null || body.getUserIds().isEmpty()) {
            return AjaxResult.error("请选择需要审核的数据");
        }
        usersService.batchAuditUsers(body.getUserIds(), 1);
        return AjaxResult.success("批量通过成功");
    }

    /**
     * 6. 批量拒绝
     * Body: { "userIds": [1, 2, 3] }
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:company:edit')")
    @Log(title = "企业审核批量拒绝", businessType = BusinessType.UPDATE)
    @PutMapping("/batch/reject")
    public AjaxResult batchReject(@RequestBody BatchAuditBody body) {
        if (body.getUserIds() == null || body.getUserIds().isEmpty()) {
            return AjaxResult.error("请选择需要审核的数据");
        }
        usersService.batchAuditUsers(body.getUserIds(), 2);
        return AjaxResult.success("批量拒绝成功");
    }

    /**
     * 7. 导出数据
     */
    @PreAuthorize("@ss.hasPermi('recruit:audit:company:export')")
    @Log(title = "企业审核导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Users users) {
        List<CompanyAudit> list = usersService.selectCompanyAuditList(users);
        // 使用 VO 类进行导出
        ExcelUtil<CompanyAudit> util = new ExcelUtil<>(CompanyAudit.class);
        util.exportExcel(response, list, "企业账号审核数据");
    }

    /**
     * DTO: 用于接收批量 ID 数组
     */
    public static class BatchAuditBody {
        private List<Long> userIds;
        public List<Long> getUserIds() { return userIds; }
        public void setUserIds(List<Long> userIds) { this.userIds = userIds; }
    }
}