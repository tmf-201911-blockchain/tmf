import types from './mutations-types';
import { getCurrentDateStr } from '@/utils/dateUtil';

const mutations = {
  // 获取待办事项选中item的列表数据
  [types.TODO_ITEM] (state, newData) {
    // 转换时间格式
    if (newData.applicationTime) {
      newData.applicationTime = getCurrentDateStr(newData.applicationTime);
    }
    if (newData.effectiveTime) {
      newData.effectiveTime = getCurrentDateStr(newData.effectiveTime);
    }
    if (newData.reportTime) {
      newData.reportTime = getCurrentDateStr(newData.reportTime);
    }
    state.todoItem = { ...newData};

  },

  // 获取待办事项列表数据
  [types.TODO_LIST] (state, newData) {
    state.todoList = newData;
  },

  // 获取待办事项列表参数
  [types.SEARCH_CRITERIA] (state, newData) {
    state.searchCriteria = { ...newData };
  },

  // 保存用户信息
  [types.USER_INFO] (state, newData) {
    let userInfo = { ...newData };
    switch (userInfo.role) {
      case 'Unicom':
      case 'Telecom':
        userInfo.Operator = true;
        break;
      default:
        userInfo.Operator = false;
        break;
    }
    state.userInfo = userInfo;

    if (typeof userInfo !== 'string') {
      userInfo = JSON.stringify(userInfo);
    }
    window.localStorage.setItem('user_info', userInfo);
  },

  // 删除用户信息
  [types.DELETE_USER_INFO] (state, newData) {
    window.localStorage.removeItem('user_info');
    state.userInfo = {};
  },

  //
  [types.TOGGLE_SIDEBAR] (state, newData) {
    state.toggleSider = !state.toggleSider;
  },

  //
  [types.ROUTER_PATH] (state, newData) {
    state.routerPath = newData;
  },
  //
  [types.IS_HAS_APPROVAL] (state, newData) {
    state.isHasApproval = newData;
  },
  [types.IS_HAS_INPROGRESS] (state, newData) {
    state.isHasInprogress = newData;
  },
  [types.RESOURCE_DIALOG_VISIBLEADAT] (state, newData = '') {
    state.ResourceDialogId = newData;
    state.ResourceDialogVisibledat = !state.ResourceDialogVisibledat;
  },
  [types.ACTUALTOTAL] (state, newData = 0) {
    state.ActualTotal = newData;
  },
}

export default mutations;
