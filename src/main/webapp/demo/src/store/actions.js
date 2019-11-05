import TodoApi from "../page/Todo/api/TodoApi";
import LoginApi from '../page/Login/LoginApi';
import types from './mutations-types';

const actions = {
  async getUnicomUnfinishedByCondition({ commit, state }, params) {
    const result = await TodoApi.getUnicomUnfinished(params);
    const { list = [] } = result;
    commit(types.TODO_LIST, list);
  },
  toggleSideBar({ commit, state }, newData) {
    commit(types.TOGGLE_SIDEBAR)
  }

}

export default actions;
