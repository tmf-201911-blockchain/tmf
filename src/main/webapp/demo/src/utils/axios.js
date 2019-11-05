import axios from 'axios';

let service = axios.create({
  baseURL: 'http://122.192.9.52:20205/tmf/',
  // baseURL: 'https://www.runoob.com/try/ajax/json_demo.json',
  timeout: 5000,
});

// post请求头
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';


// 配置各个环境请求地址，暂时用不到
// if (process.env.NODE_ENV == 'development') {
//     axios.defaults.baseURL = 'https://www.baidu.com';}
// else if (process.env.NODE_ENV == 'debug') {
//     axios.defaults.baseURL = 'https://www.ceshi.com';
// }
// else if (process.env.NODE_ENV == 'production') {
//     axios.defaults.baseURL = 'https://www.production.com';
// }
var url = '';

service.interceptors.request.use(request => {
  // 测试环境打印console.log，方便查看数据
  if (process.env.NODE_ENV == 'development') {
    url = request.url;
    console.log(`==============================${request.url} 开始=================================`)
    console.log(request.params);
  }
  return request;
});


// 响应拦截器，用于获取数据之后做些处理
service.interceptors.response.use(response => {
  const status = response.status;
  // 测试环境打印console.log，方便查看数据
  if (process.env.NODE_ENV == 'development') {
    console.log(`==============================${url} 结束=================================`)
    console.log(response.data);
  }
  if (status == '200') {
    return response.data;
  }
});



export default service;
