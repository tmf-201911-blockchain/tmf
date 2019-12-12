import service from '@/utils/axios';
//第一个饼图
// 查询总的基站数量
function getTotalCount(params, method) {
  return service({
    url: 'count/getTotalCount',
    params,
    method,
  });
}
// 查询联通基站数量
function getUnicomCount(params, method) {
  return service({
    url: 'count/getUnicomCount',
    params,
    method,
  });
}
//查询电信基站数量
function getTelecomCount(params, method) {
  return service({
    url: 'count/getTelecomCount',
    params,
    method,
  });
}
function getPercent(params, method) {
  return service({
    url: 'count/getPercent',
    params,
    method,
  });
}

//第三个饼图
//查询总的使用中基站数量
function getTotalUsing(params, method) {
  return service({
    url: 'count/getTotalUsing',
    params,
    method,
  });
}
//查询总的建设中基站数量
function getTotalBuilding(params, method) {
  return service({
    url: 'count/getTotalBuilding',
    params,
    method,
  });
}
//查询总的规划中的基站数量
function getTotalPlaning(params, method) {
  return service({
    url: 'count/getTotalPlaning',
    params,
    method,
  });
}
//查询总的维护中的基站数量
function getTotalMaintenance(params, method) {
  return service({
    url: 'count/getTotalMaintenance',
    params,
    method,
  });
}
//查询联通使用中,建设中,规划中,维护中占联通总的百分比
function getUnicomPercent(params, method) {
  return service({
    url: 'count/getUnicomPercent',
    params,
    method,
  });
}
//查询电信使用中,建设中,规划中,维护中占联通总的百分比
function getTelecomPercent(params, method) {
  return service({
    url: 'count/getTelecomPercent',
    params,
    method,
  });
}
//查询联通租入租出
function getUnicomRent(params, method) {
  return service({
    url: 'count/getUnicomRent',
    params,
    method,
  });
}
//查询电信租入租出
function getTelecomRent(params, method) {
  return service({
    url: 'count/getTelecomRent',
    params,
    method,
  });
}
//最后一个柱形统计图
function getRentMonth(params, method) {
  return service({
    url: 'count/getRentMonth',
    params,
    method,
  });
}
//联通查询
function getUnicomRentDetailByCondition(params, method) {
  return service({
    url: 'count/getUnicomRentDetailByCondition',
    params,
    method,
  });
}
//电信查询
function getTelecomRentDetailByCondition(params, method) {
  return service({
    url: 'count/getTelecomRentDetailByCondition',
    params,
    method,
  });
}

function getMapCount(params, method) {
  return service({
    url: 'count/getMapCount',
    params,
    method,
  });
}
//最后饼图上面的数字
function getInvestorCount(params, method) {
  return service({
    url: 'count/getInvestorCount',
    params,
    method,
  });
}
function getPlanStation(params, method) {
  return service({
    url: 'count/getPlanStation',
    params,
    method,
  });
}
function getInvestorCost(params, method) {
  return service({
    url: 'count/getInvestorCost',
    params,
    method,
  });
}
export default {
  getTotalCount,
  getUnicomCount,
  getTelecomCount,
  getPercent,
  getTotalUsing,
  getTotalBuilding,
  getTotalPlaning,
  getTotalMaintenance,
  getUnicomPercent,
  getTelecomPercent,
  getUnicomRent,
  getTelecomRent,
  getRentMonth,
  getUnicomRentDetailByCondition,
  getTelecomRentDetailByCondition,
  getMapCount,
  getInvestorCount,
  getPlanStation,
  getInvestorCost,
}

