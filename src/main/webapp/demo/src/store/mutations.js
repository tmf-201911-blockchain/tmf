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
    state.todoItem = newData;

  },

  // 获取待办事项列表数据
  [types.TODO_LIST] (state, newData) {
    state.todoList = newData;
  },

  // 获取待办事项列表参数
  [types.SEARCH_CRITERIA] (state, newData) {
    state.searchCriteria = newData;
  },

  // 报存用户信息
  [types.USER_INFO] (state, newData) {
    state.userInfo = newData;
    if (typeof newData !== 'string') {
      newData = JSON.stringify(newData);
    }
    window.localStorage.setItem('user_info', newData);
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
}

export default mutations;
