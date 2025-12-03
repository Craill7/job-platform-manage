package com.ruoyi.recruit.mapper;

import java.util.List;
import com.ruoyi.recruit.domain.Events;

/**
 * 求职活动管理Mapper接口
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public interface EventsMapper 
{
    /**
     * 查询求职活动管理
     * 
     * @param id 求职活动管理主键
     * @return 求职活动管理
     */
    public Events selectEventsById(Long id);

    /**
     * 查询求职活动管理列表
     * 
     * @param events 求职活动管理
     * @return 求职活动管理集合
     */
    public List<Events> selectEventsList(Events events);

    /**
     * 新增求职活动管理
     * 
     * @param events 求职活动管理
     * @return 结果
     */
    public int insertEvents(Events events);

    /**
     * 修改求职活动管理
     * 
     * @param events 求职活动管理
     * @return 结果
     */
    public int updateEvents(Events events);

    /**
     * 删除求职活动管理
     * 
     * @param id 求职活动管理主键
     * @return 结果
     */
    public int deleteEventsById(Long id);

    /**
     * 批量删除求职活动管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEventsByIds(Long[] ids);
}
