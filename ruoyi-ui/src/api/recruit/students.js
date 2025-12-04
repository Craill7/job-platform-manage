import request from '@/utils/request'

// 查询学生信息管理列表
export function listStudents(query) {
  return request({
    url: '/recruit/students/list',
    method: 'get',
    params: query
  })
}

// 查询学生信息管理详细
export function getStudents(userId) {
  return request({
    url: '/recruit/students/' + userId,
    method: 'get'
  })
}

// 新增学生信息管理
export function addStudents(data) {
  return request({
    url: '/recruit/students',
    method: 'post',
    data: data
  })
}

// 修改学生信息管理
export function updateStudents(data) {
  return request({
    url: '/recruit/students',
    method: 'put',
    data: data
  })
}

// 删除学生信息管理
export function delStudents(userId) {
  return request({
    url: '/recruit/students/' + userId,
    method: 'delete'
  })
}
