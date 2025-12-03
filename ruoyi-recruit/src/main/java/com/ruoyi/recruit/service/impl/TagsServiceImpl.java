package com.ruoyi.recruit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.mapper.TagsMapper;
import com.ruoyi.recruit.domain.Tags;
import com.ruoyi.recruit.service.ITagsService;

/**
 * 标签库Service业务层处理
 * 
 * @author Yihan
 * @date 2025-12-03
 */
@Service
public class TagsServiceImpl implements ITagsService 
{
    @Autowired
    private TagsMapper tagsMapper;

    /**
     * 查询标签库
     * 
     * @param id 标签库主键
     * @return 标签库
     */
    @Override
    public Tags selectTagsById(Long id)
    {
        return tagsMapper.selectTagsById(id);
    }

    /**
     * 查询标签库列表
     * 
     * @param tags 标签库
     * @return 标签库
     */
    @Override
    public List<Tags> selectTagsList(Tags tags)
    {
        return tagsMapper.selectTagsList(tags);
    }

    /**
     * 新增标签库
     * 
     * @param tags 标签库
     * @return 结果
     */
    @Override
    public int insertTags(Tags tags)
    {
        return tagsMapper.insertTags(tags);
    }

    /**
     * 修改标签库
     * 
     * @param tags 标签库
     * @return 结果
     */
    @Override
    public int updateTags(Tags tags)
    {
        return tagsMapper.updateTags(tags);
    }

    /**
     * 批量删除标签库
     * 
     * @param ids 需要删除的标签库主键
     * @return 结果
     */
    @Override
    public int deleteTagsByIds(Long[] ids)
    {
        return tagsMapper.deleteTagsByIds(ids);
    }

    /**
     * 删除标签库信息
     * 
     * @param id 标签库主键
     * @return 结果
     */
    @Override
    public int deleteTagsById(Long id)
    {
        return tagsMapper.deleteTagsById(id);
    }
}
