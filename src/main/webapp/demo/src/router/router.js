import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/page/Login';
import Index from '@/page/index';

import ResourceManagement from '@/page/ResourceManagement/ResourceManagement';
import ResourcesStatistics from '@/page/ResourcesStatistics/ResourcesStatistics';
import Todo from '@/page/Todo/Todo';
import HaveTodo from "@/page/Todo/HaveTodo";
import Backlog from "@/page/Todo/Backlog";
import Application from "@/page/Todo/Application";


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/Index',
      name: 'Index',
      component: Index,
      children: [
        {
          path: '/',
          name: 'ResourceManagement',
          component: ResourceManagement,
        },
        {
          path: 'ResourcesStatistics',
          name: 'ResourcesStatistics',
          component: ResourcesStatistics,
        },
        {
          path: 'Todo',
          name: 'Todo',
          component: Todo,
          children: [
            {
              path: '/',
              name: 'Backlog',
              component: Backlog,
            },
            {
              path: 'HaveTodo',
              name: 'HaveTodo',
              component: HaveTodo,
            },
            {
              path: 'Application',
              name: 'Application',
              component: Application,
            },
          ],
        },
      ],
    },
  ]
})
