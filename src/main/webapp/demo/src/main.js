// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/router'

import store from './store/index';

import ElementUI from 'element-ui'
import locale from '../node_modules/element-ui/lib/locale/lang/en'//elementUi切换英文
import 'element-ui/lib/theme-chalk/index.css'

import Antd from 'ant-design-vue';
import Button from 'ant-design-vue/lib/button';
import 'ant-design-vue/dist/antd.css';

import echarts from 'echarts'
Vue.prototype.echarts = echarts
import '../node_modules/echarts/map/js/province/beijing.js' // 引入北京地图数据
import { Message } from 'element-ui';
Vue.prototype.$msg = Message
Vue.use(ElementUI, {locale});
Vue.component(Button.name, Button);
Vue.use(Antd);

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
