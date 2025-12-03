import request from '@/utils/request'

// 查询企业性质管理列表
export function listNatures(query) {
  return request({
    url: '/recruit/natures/list',
    method: 'get',
    params: query
  })
}

// 查询企业性质管理详细
export function getNatures(id) {
  return request({
    url: '/recruit/natures/' + id,
    method: 'get'
  })
}

// 新增企业性质管理
export function addNatures(data) {
  return request({
    url: '/recruit/natures',
    method: 'post',
    data: data
  })
}

// 修改企业性质管理
export function updateNatures(data) {
  return request({
    url: '/recruit/natures',
    method: 'put',
    data: data
  })
}

// 删除企业性质管理
export function delNatures(id) {
  return request({
    url: '/recruit/natures/' + id,
    method: 'delete'
  })
}
