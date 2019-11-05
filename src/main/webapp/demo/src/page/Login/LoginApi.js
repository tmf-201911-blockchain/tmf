import service from '@/utils/axios';

function Login(params, method) {
  return service({
    url: '/user/login',
    params,
    method,
  });
}

export default {
  Login,
}
