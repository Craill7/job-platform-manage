import request from '@/utils/request'

// 查询岗位类别管理列表
export function listCategories(query) {
  return request({
    url: '/recruit/categories/list',
    method: 'get',
    params: query
  })
}

// 查询岗位类别管理详细
export function getCategories(id) {
  return request({
    url: '/recruit/categories/' + id,
    method: 'get'
  })
}

// 新增岗位类别管理
export function addCategories(data) {
  return request({
    url: '/recruit/categories',
    method: 'post',
    data: data
  })
}

// 修改岗位类别管理
export function updateCategories(data) {
  return request({
    url: '/recruit/categories',
    method: 'put',
    data: data
  })
}

// 删除岗位类别管理
export function delCategories(id) {
  return request({
    url: '/recruit/categories/' + id,
    method: 'delete'
  })
}
