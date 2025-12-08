import request from '@/utils/request'

// 查询城市管理列表
export function listCities(query) {
  return request({
    url: '/recruit/cities/list',
    method: 'get',
    params: query
  })
}

// 根据省份ID查询城市列表
export function listCitiesByProvince(provinceId) {
  return request({
    url: '/recruit/cities/byProvince/' + provinceId,
    method: 'get'
  })
}

// 查询城市管理详细
export function getCity(id) {
  return request({
    url: '/recruit/cities/' + id,
    method: 'get'
  })
}

