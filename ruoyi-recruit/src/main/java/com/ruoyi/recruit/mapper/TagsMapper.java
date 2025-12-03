package com.ruoyi.recruit.mapper;

import java.util.List;
import com.ruoyi.recruit.domain.Tags;

/**
 * 标签库Mapper接口
 * 
 * @author Yihan
 * @date 2025-12-03
 */
public interface TagsMapper 
{
    /**
     * 查询标签库
     * 
     * @param id 标签库主键
     * @return 标签库
     */
    public Tags selectTagsById(Long id);

    /**
     * 查询标签库列表
     * 
     * @param tags 标签库
     * @return 标签库集合
     */
    public List<Tags> selectTagsList(Tags tags);

    /**
     * 新增标签库
     * 
     * @param tags 标签库
     * @return 结果
     */
    public int insertTags(Tags tags);

    /**
     * 修改标签库
     * 
     * @param tags 标签库
     * @return 结果
     */
    public int updateTags(Tags tags);

    /**
     * 删除标签库
     * 
     * @param id 标签库主键
     * @return 结果
     */
    public int deleteTagsById(Long id);

    /**
     * 批量删除标签库
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTagsByIds(Long[] ids);
}
