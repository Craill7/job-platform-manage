import request from '@/utils/request'

// 查询标签分类管理列表
export function listTagCategories(query) {
  return request({
    url: '/recruit/tagCategories/list',
    method: 'get',
    params: query
  })
}

// 查询标签分类管理详细
export function getTagCategories(id) {
  return request({
    url: '/recruit/tagCategories/' + id,
    method: 'get'
  })
}

// 新增标签分类管理
export function addTagCategories(data) {
  return request({
    url: '/recruit/tagCategories',
    method: 'post',
    data: data
  })
}

// 修改标签分类管理
export function updateTagCategories(data) {
  return request({
    url: '/recruit/tagCategories',
    method: 'put',
    data: data
  })
}

// 删除标签分类管理
export function delTagCategories(id) {
  return request({
    url: '/recruit/tagCategories/' + id,
    method: 'delete'
  })
}
