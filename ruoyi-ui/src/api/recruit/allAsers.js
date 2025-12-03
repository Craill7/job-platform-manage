import request from '@/utils/request'

// 查询账号管理列表
export function listAllAsers(query) {
  return request({
    url: '/recruit/allAsers/list',
    method: 'get',
    params: query
  })
}

// 查询账号管理详细
export function getAllAsers(id) {
  return request({
    url: '/recruit/allAsers/' + id,
    method: 'get'
  })
}

// 新增账号管理
export function addAllAsers(data) {
  return request({
    url: '/recruit/allAsers',
    method: 'post',
    data: data
  })
}

// 修改账号管理
export function updateAllAsers(data) {
  return request({
    url: '/recruit/allAsers',
    method: 'put',
    data: data
  })
}

// 删除账号管理
export function delAllAsers(id) {
  return request({
    url: '/recruit/allAsers/' + id,
    method: 'delete'
  })
}
