

const state = {
  todoItem: {}, // 待办事项列表item数据
  todoList: [], // 待办事项列表数据
  searchCriteria: {},
  userInfo: JSON.parse(localStorage.getItem('user_info')),
  toggleSider: false,
  hasTodo: false,
  hasApplication: false,
  routerPath: '',
  ResourceDialogId: '',
  isHasApproval: false,
  isHasInprogress: false,
  ResourceDialogVisibledat: false,
  ActualTotal: 0,
}

export default state;
