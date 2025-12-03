import request from '@/utils/request'

// 查询标签库列表
export function listTags(query) {
  return request({
    url: '/recruit/tags/list',
    method: 'get',
    params: query
  })
}

// 查询标签库详细
export function getTags(id) {
  return request({
    url: '/recruit/tags/' + id,
    method: 'get'
  })
}

// 新增标签库
export function addTags(data) {
  return request({
    url: '/recruit/tags',
    method: 'post',
    data: data
  })
}

// 修改标签库
export function updateTags(data) {
  return request({
    url: '/recruit/tags',
    method: 'put',
    data: data
  })
}

// 删除标签库
export function delTags(id) {
  return request({
    url: '/recruit/tags/' + id,
    method: 'delete'
  })
}
