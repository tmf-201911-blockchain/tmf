import service from '@/utils/axios';
import state from '@/store/state';

// 租入结算
function findRentInSettlement(params, method) {
  return service({
    url: 'rent/findRentInSettlement',
    params,
    method,
  });
}

// 租出结算
function findRentOutSettlement(params, method) {
  return service({
    url: 'rent/findRentOutSettlement',
    params,
    method,
  });
}

/**
 * 收益结算
 * @param params
 * @param method
 */
function getSharing(params, method) {
  let url = '';
  switch (state.userInfo.role) {
    case 'Unicom':
    case 'Telecom':
      url = 'rent/getISPSharing';  // 运营商收益结算
      break;
    case 'Investor':
      url = 'rent/getInvestmentSharing';  // 第三方收益结算
      break;
    default:
      break;
  }
  return service({
    url,
    params,
    method,
  });
}

export default {
  findRentOutSettlement,
  findRentInSettlement,
  getSharing,
}
