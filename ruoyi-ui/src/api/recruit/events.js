import request from '@/utils/request'

// 查询求职活动管理列表
export function listEvents(query) {
  return request({
    url: '/recruit/events/list',
    method: 'get',
    params: query
  })
}

// 查询求职活动管理详细
export function getEvents(id) {
  return request({
    url: '/recruit/events/' + id,
    method: 'get'
  })
}

// 新增求职活动管理
export function addEvents(data) {
  return request({
    url: '/recruit/events',
    method: 'post',
    data: data
  })
}

// 修改求职活动管理
export function updateEvents(data) {
  return request({
    url: '/recruit/events',
    method: 'put',
    data: data
  })
}

// 删除求职活动管理
export function delEvents(id) {
  return request({
    url: '/recruit/events/' + id,
    method: 'delete'
  })
}
