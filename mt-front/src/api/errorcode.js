import request from '@/utils/request'

export function insert(data) {
  return request({
    url: '/errorcode/insert',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/errorcode/update',
    method: 'post',
    data
  })
}
// 审批
export function approvalUpdate(data) {
  return request({
    url: '/errorcode/approval',
    method: 'post',
    data
  })
}

export function deleteData(data) {
  return request({
    url: '/errorcode/delete',
    method: 'post',
    data
  })
}

export function getList(data) {
  return request({
    url: '/errorcode/list',
    method: 'get',
    params: data
  })
}
export function getListByUser(data) {
  return request({
    url: '/errorcode/listByUser',
    method: 'get',
    params: data
  })
}
