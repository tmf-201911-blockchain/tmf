import service from '@/utils/axios';
import qs from 'qs';
//得到所有的基站的信息
function getAll(params, method) {
  return service({
    url: 'resources/getAll',
    params,
    method,
  });
}
//根据基站名称进行搜索
function getResByName(params, method) {
  return service({
    url: 'resources/getResByName',
    params,
    method,
  });
}
//得到最新的10条记录
function getNewInfo(params, method) {
  return service({
    url: 'resources/getNewInfo',
    params,
    method,
  });
}
//删除所有的搜索记录
function deleteAllInfo(params, method) {
  return service({
    url: 'resources/deleteAllInfo',
    params,
    method,
  });
}
//新增基站
// function create(params, method) {
//   return service({
//     url: 'resources/create',
//     params,
//     method,
//   });
// }
// 新增基站
function create(params,method){
  return service({
    url:"resources/create",
    data:qs.stringify(params),
    method:'post',
  })
}
function upimg(params, method) {
  return service({
    url: 'upload/getFile',
    params,
    method,
  });
}
//查看一个基站的信息
function getOne(params, method) {
  return service({
    url: 'resources/getOne',
    params,
    method,
  });
}
//查看基站详情页面
function getDetail(params, method) {
  return service({
    url: 'resources/getDetail',
    params,
    method,
  });
}
//根据基站状态查询
function getResByStatus(params, method) {
  return service({
    url: 'resources/getResByStatus',
    params,
    method,
  });
}
//状态修改
function updateStatus(params, method) {
  return service({
    url: 'resources/updateStatus',
    params,
    method,
  });
}
//资源修改
// function update(params, method) {
//   return service({
//     url: 'resources/update',
//     params,
//     method,
//   });
// }
function update(params,method){
  return service({
    url:"resources/update",
    data:qs.stringify(params),
    method:'post',
  })
}
//资源删除
function deletedetails(params, method) {
  return service({
    url: 'resources/delete',
    params,
    method,
  });
}
//资源申请
function apply(params, method) {
  return service({
    url: 'resources/apply',
    params,
    method,
  });
}
//资源终止
function end(params, method) {
  return service({
    url: 'resources/end',
    params,
    method,
  });
}
function haveApplied(params, method) {
  return service({
    url: 'resources/haveApplied',
    params,
    method,
  });
}
//资源申请点击之后不能点击
function isApplyRes(params, method) {
  return service({
    url: 'resources/isApplyRes',
    params,
    method,
  });
}
function isApplyEnd(params, method) {
  return service({
    url: 'resources/isApplyEnd',
    params,
    method,
  });
}
//评论
// function evaluate(params, method) {
//   return service({
//     url: 'resources/evaluate',
//     params,
//     method,
//   });
// }
//发起招标
function auction(params, method) {
  return service({
    url: 'bid/auction',
    params,
    method,
  });
}
//招标详情不加密
function getAllByResourceId(params, method) {
  return service({
    url: 'bid/getAllByResourceId',
    params,
    method,
  });
}
//招标加密
function getEncryptInfo(params, method) {
  return service({
    url: 'bid/getEncryptInfo',
    params,
    method,
  });
}
//获取用户报障信息
function showStationReport(params, method) {
  return service({
    url: 'resources/showStationReport',
    params,
    method,
  });
}
//上链
function evaluate(params, method) {
  return service({
    url: 'resources/evaluate',
    params,
    method,
  });
}
export default {
  getAll,
  getResByName,
  create,
  getOne,
  getNewInfo,
  deleteAllInfo,
  getResByStatus,
  getDetail,
  updateStatus,
  upimg,
  update,
  deletedetails,
  apply,
  end,
  haveApplied,
  isApplyRes,
  isApplyEnd,
  auction,
  getAllByResourceId,
  getEncryptInfo,
  // evaluate,
  showStationReport,
  evaluate,
}

