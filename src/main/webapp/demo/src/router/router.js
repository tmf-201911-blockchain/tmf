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

import Settlement from "../page/Settlement/Settlement";
import OpsAndMaintenance from "../page/OpsAndMaintenance/OpsAndMaintenance";

import RentIn from "../page/Settlement/RentIn";
import RentOut from "../page/Settlement/RentOut";
import InvestmentSharing from "../page/Settlement/InvestmentSharing";

import InprogressPage from "../page/OpsAndMaintenance/InprogressPage";
import SolvedPage from "../page/OpsAndMaintenance/SolvedPage";


Vue.use(Router);
const router = new Router({
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
        {
          path: 'OpsAndMaintenance',
          name: 'OpsAndMaintenance',
          component: OpsAndMaintenance,
          children: [
            {
              path: '/',
              name: 'Inprogress',
              component: InprogressPage,
            },
            {
              path: 'Solved',
              name: 'Solved',
              component: SolvedPage,
            }
          ],
        },
        {
          path: 'Settlement',
          name: 'Settlement',
          component: Settlement,
          children: [
            {
              path: '/',
              name: 'RentIn',
              component: RentIn,
            },
            {
              path: 'RentOut',
              name: 'RentOut',
              component: RentOut,
            },
            {
              path: 'InvestmentSharing',
              name: 'InvestmentSharing',
              component: InvestmentSharing,
            },
          ],
        }
      ],
    },
  ]
});

// router.beforeEach((to, from, next) => {
//   console.log(to, 'to');
//   console.log(from, 'from');
//   console.log(next, 'next');
//   next();
// });

export default router;


