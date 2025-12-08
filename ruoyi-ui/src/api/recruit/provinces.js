import request from '@/utils/request'

// 查询省份管理列表
export function listProvinces(query) {
  return request({
    url: '/recruit/provinces/list',
    method: 'get',
    params: query
  })
}

// 查询省份管理详细
export function getProvince(id) {
  return request({
    url: '/recruit/provinces/' + id,
    method: 'get'
  })
}

