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

// 根据学生姓名模糊查询学生列表（用于下拉选择）
export function searchStudents(fullName) {
  return request({
    url: '/recruit/students/search',
    method: 'get',
    params: { fullName: fullName }
  })
}