import TodoApi from "../page/Todo/api/TodoApi";
import LoginApi from '../page/Login/LoginApi';
import types from './mutations-types';
import OpsApi from '../page/OpsAndMaintenance/api/index';

const actions = {
  async getUnicomUnfinishedByCondition({ commit, state }, params) {
    const result = await TodoApi.getUnicomUnfinished(params);
    const { list = [] } = result;
    commit(types.TODO_LIST, list);
  },
  toggleSideBar({ commit, state }, newData) {
    commit(types.TOGGLE_SIDEBAR)
  },

  /**
   * Ops & Maintenance 点击审批/详情获取报障信息
   * @param commit
   * @param state
   * @param params
   * @returns {Promise<void>}
   */
  async getOne({ commit, state }, params) {
    try {
      const result = await OpsApi.getOne(params.params);
      const { data = {} }  = result;
      data.dialogTitile = 'Operation and maintenance';
      data.hideSome = true;
      data.processName = 'OPS';
      data.OpsProcess = params.OpsProcess;
      commit(types.TODO_ITEM, data);
    } catch (e) {
      console.log(e);
    }
  }

}

export default actions;
