export function dateAddLine(date) {
  /** 字符串转时间格式 */
  if (!date) return '';
  const year = date.substring(0, 4);
  const month = date.substring(4, 6);
  const day = date.substring(6);
  return `${year}-${month}-${day}`;
}

export function getCurrentDateStr(date) {
  // 获取当前年月日，或格式化时间格式
  const data = date ? new Date(date) : new Date();
  const year = data.getFullYear();
  const mon = data.getMonth() + 1;
  const monStr = mon < 10 ? `0${mon}` : `${mon}`;
  const day = data.getDate();
  const dayStr = day < 10 ? `0${day}` : `${day}`;
  return `${year}-${monStr}-${dayStr}`;
}
