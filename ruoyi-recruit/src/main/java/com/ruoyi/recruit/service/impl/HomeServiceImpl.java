package com.ruoyi.recruit.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.recruit.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.domain.Events;
import com.ruoyi.recruit.service.IHomeService;

/**
 * 首页概览Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-12
 */
@Service
public class HomeServiceImpl implements IHomeService 
{
    @Autowired
    private JobsMapper jobsMapper;

    @Autowired
    private ApplicationsMapper applicationsMapper;

    @Autowired
    private CompaniesMapper companiesMapper;

    @Autowired
    private EventsMapper eventsMapper;

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 获取首页概览数据
     * 
     * @return 概览数据
     */
    @Override
    public Map<String, Object> getHomeOverview()
    {
        Map<String, Object> result = new HashMap<>();
        // 2. 统计待审核岗位数量
        int pendingJobCount = jobsMapper.countPendingJobs();
        result.put("pendingJobCount", pendingJobCount);

        // 3. 统计待审核企业账号数量
        int pendingCompanyCount = usersMapper.countPendingCompanies();
        result.put("pendingCompanyCount", pendingCompanyCount);

        // 1. 统计未到截止日期的岗位数量
        int currentRecruitCount = jobsMapper.countCurrentRecruitJobs();
        result.put("currentRecruitCount", currentRecruitCount);

        // 2. 统计投递记录数量
        int studentDeliveryCount = applicationsMapper.countApplications();
        result.put("studentDeliveryCount", studentDeliveryCount);

        // 3. 统计企业数量
        int companyCount = companiesMapper.countCompanies();
        result.put("companyCount", companyCount);

        // 4. 获取最新的活动列表（用于时间轴）
        List<Events> events = eventsMapper.selectLatestEvents(10);
        List<Map<String, Object>> timeline = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        for (Events event : events)
        {
            Map<String, Object> timelineItem = new HashMap<>();
            timelineItem.put("id", event.getId());
            timelineItem.put("type", "activity"); // 活动类型
            timelineItem.put("title", event.getEventTitle());
            timelineItem.put("time", dateFormat.format(event.getEventStartTime()));
            timelineItem.put("desc", event.getEventSummary() != null ? event.getEventSummary() : "");
            timelineItem.put("color", "blue"); // 默认颜色
            timeline.add(timelineItem);
        }
        
        result.put("latestTimeline", timeline);

        return result;
    }
}

