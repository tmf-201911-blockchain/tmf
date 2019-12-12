import service from '@/utils/axios';
import state from '@/store/state';


// 查询联通待办
function getUnicomUnfinished(params, method) {
  let getUrl = '';
  if (state.userInfo.role == 'Unicom') {
    getUrl = 'progress/getUnicomUnfinishedByCondition';
  } else if (state.userInfo.role == 'Telecom') {
    getUrl = 'progress/getTelecomUnfinishedByCondition';
  }
  return service({
    url: getUrl,
    params,
    method,
  });
}

// 查询联通已办
function getUnicomFinished(params, method) {
  let getUrl = '';
  if (state.userInfo.role == 'Unicom') {
    getUrl = 'progress/getUnicomFinishedByCondition';
  } else if (state.userInfo.role == 'Telecom') {
    getUrl = 'progress/getTelecomFinishedByCondition';
  }
  return service({
    url: getUrl,
    params,
    method,
  });
}

// 查询联通申请
function getUnicomApply(params, method) {
  let getUrl = '';
  if (state.userInfo.role == 'Unicom') {
    getUrl = 'progress/getUnicomApplyByCondition';
  } else if (state.userInfo.role == 'Telecom') {
    getUrl = 'progress/getTelecomApplyByCondition';
  } else {
    getUrl = 'rent/getInvestorApply';
  }
  return service({
    url: getUrl,
    params,
    method,
  });
}

// 审核操作
function updateUnicomProgress(params, method) {
  return service({
    url: 'progress/updateUnicomProgress',
    params,
    method,
  });
}

// 资源申请审批
function updateResourceApply(params, method) {
  return service({
    url: 'progress/updateResourceApply',
    params,
    method,
  });
}

// 终止申请的审批
function updateTerminationApply(params, method) {
  return service({
    url: 'progress/updateTerminationApply',
    params,
    method,
  });
}

// 获取详情，包括任务处理的信息
function getAllInfo(params, method) {
  return service({
    url: 'resources/getAllInfo',
    params,
    method,
  });
}

export default {
  getUnicomUnfinished,
  getUnicomFinished,
  getUnicomApply,
  updateUnicomProgress,
  getAllInfo,
  updateResourceApply,
  updateTerminationApply,
}
