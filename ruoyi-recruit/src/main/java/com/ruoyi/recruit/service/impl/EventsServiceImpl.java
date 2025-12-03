package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.EventsMapper;
import com.ruoyi.recruit.domain.Events;
import com.ruoyi.recruit.service.IEventsService;

/**
 * 求职活动管理Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@Service
public class EventsServiceImpl implements IEventsService 
{
    @Autowired
    private EventsMapper eventsMapper;

    /**
     * 查询求职活动管理
     * 
     * @param id 求职活动管理主键
     * @return 求职活动管理
     */
    @Override
    public Events selectEventsById(Long id)
    {
        return eventsMapper.selectEventsById(id);
    }

    /**
     * 查询求职活动管理列表
     * 
     * @param events 求职活动管理
     * @return 求职活动管理
     */
    @Override
    public List<Events> selectEventsList(Events events)
    {
        return eventsMapper.selectEventsList(events);
    }

    /**
     * 新增求职活动管理
     * 
     * @param events 求职活动管理
     * @return 结果
     */
    @Override
    public int insertEvents(Events events)
    {
        return eventsMapper.insertEvents(events);
    }

    /**
     * 修改求职活动管理
     * 
     * @param events 求职活动管理
     * @return 结果
     */
    @Override
    public int updateEvents(Events events)
    {
        return eventsMapper.updateEvents(events);
    }

    /**
     * 批量删除求职活动管理
     * 
     * @param ids 需要删除的求职活动管理主键
     * @return 结果
     */
    @Override
    public int deleteEventsByIds(Long[] ids)
    {
        return eventsMapper.deleteEventsByIds(ids);
    }

    /**
     * 删除求职活动管理信息
     * 
     * @param id 求职活动管理主键
     * @return 结果
     */
    @Override
    public int deleteEventsById(Long id)
    {
        return eventsMapper.deleteEventsById(id);
    }
}
