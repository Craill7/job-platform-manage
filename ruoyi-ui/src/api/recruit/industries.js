import request from '@/utils/request'

// 查询行业信息管理列表
export function listIndustries(query) {
  return request({
    url: '/recruit/industries/list',
    method: 'get',
    params: query
  })
}

// 查询行业信息管理详细
export function getIndustries(id) {
  return request({
    url: '/recruit/industries/' + id,
    method: 'get'
  })
}

// 新增行业信息管理
export function addIndustries(data) {
  return request({
    url: '/recruit/industries',
    method: 'post',
    data: data
  })
}

// 修改行业信息管理
export function updateIndustries(data) {
  return request({
    url: '/recruit/industries',
    method: 'put',
    data: data
  })
}

// 删除行业信息管理
export function delIndustries(id) {
  return request({
    url: '/recruit/industries/' + id,
    method: 'delete'
  })
}
