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
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.recruit.domain.Events;
import com.ruoyi.recruit.service.IEventsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 求职活动管理Controller
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@RestController
@RequestMapping("/recruit/events")
public class EventsController extends BaseController
{
    @Autowired
    private IEventsService eventsService;

    /**
     * 查询求职活动管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:events:list')")
    @GetMapping("/list")
    public TableDataInfo list(Events events)
    {
        startPage();
        List<Events> list = eventsService.selectEventsList(events);
        return getDataTable(list);
    }

    /**
     * 导出求职活动管理列表
     */
    @PreAuthorize("@ss.hasPermi('recruit:events:export')")
    @Log(title = "求职活动管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Events events)
    {
        List<Events> list = eventsService.selectEventsList(events);
        ExcelUtil<Events> util = new ExcelUtil<Events>(Events.class);
        util.exportExcel(response, list, "求职活动管理数据");
    }

    /**
     * 获取求职活动管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('recruit:events:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(eventsService.selectEventsById(id));
    }

    /**
     * 新增求职活动管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:events:add')")
    @Log(title = "求职活动管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Events events)
    {
        return toAjax(eventsService.insertEvents(events));
    }

    /**
     * 修改求职活动管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:events:edit')")
    @Log(title = "求职活动管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Events events)
    {
        return toAjax(eventsService.updateEvents(events));
    }

    /**
     * 删除求职活动管理
     */
    @PreAuthorize("@ss.hasPermi('recruit:events:remove')")
    @Log(title = "求职活动管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eventsService.deleteEventsByIds(ids));
    }
}
