import service from '@/utils/axios';
import qs from 'qs';

/**
 * ops页面--获取所有数据
 * @param params
 * @param method
 */
function fetchList(params, method) {
  return service({
    url: 'code/fetchListPC',
    data: qs.stringify(params),
    method: 'post',
  });
}

/**
 * 点击基站名称获取详情
 * @param params
 * @param method
 */
function getDetailByTaskId(params, method) {
  return service({
    url: 'code/getDetailByTaskId',
    params,
  });
}

/**
 * 点击审批/详情获取报障信息
 * @param params
 * @param method
 */
function getOne(params, method) {
  return service({
    url: 'code/getOne',
    params,
  });
}

/**
 * OPS审批
 * @param params
 * @param method
 */
function process(params, method) {
  return service({
    url: 'code/process',
    params,
  });
}




export default {
  fetchList,
  getDetailByTaskId,
  getOne,
  process,
}
