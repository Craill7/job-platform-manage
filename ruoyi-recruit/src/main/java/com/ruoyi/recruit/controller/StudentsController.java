package com.ruoyi.recruit.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.recruit.domain.Students;
import com.ruoyi.recruit.service.IStudentsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生信息管理Controller
 * 
 * @author Yihan
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/recruit/students")
public class StudentsController extends BaseController
{
    @Autowired
    private IStudentsService studentsService;

    /**
     * 查询学生信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:students:list')")
    @GetMapping("/list")
    public TableDataInfo list(Students students)
    {
        startPage();
        List<Students> list = studentsService.selectStudentsList(students);
        return getDataTable(list);
    }

    /**
     * 导出学生信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:students:export')")
    @Log(title = "学生信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Students students)
    {
        List<Students> list = studentsService.selectStudentsList(students);
        ExcelUtil<Students> util = new ExcelUtil<Students>(Students.class);
        util.exportExcel(response, list, "学生信息管理数据");
    }

    /**
     * 获取学生信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('recruit:students:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(studentsService.selectStudentsByUserId(userId));
    }

    /**
     * 新增学生信息管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:students:add')")
    @Log(title = "学生信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Students students)
    {
        return toAjax(studentsService.insertStudents(students));
    }

    /**
     * 修改学生信息管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:students:edit')")
    @Log(title = "学生信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Students students)
    {
        return toAjax(studentsService.updateStudents(students));
    }

    /**
     * 删除学生信息管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:students:remove')")
    @Log(title = "学生信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(studentsService.deleteStudentsByUserIds(userIds));
    }

    /**
     * 根据学生姓名模糊查询学生列表（用于下拉选择）
     */
    @GetMapping("/search")
    public AjaxResult searchStudents(@RequestParam(value = "fullName", required = false) String fullName)
    {
        Students students = new Students();
        if (fullName != null && !fullName.isEmpty()) {
            students.setFullName(fullName);
        }
        List<Students> list = studentsService.selectStudentsList(students);
        // 限制返回数量，最多返回10条
        if (list != null && list.size() > 10) {
            list = list.subList(0, 10);
        }
        return success(list);
    }
}
