export function dateAddLine(date) {
  /** 字符串转时间格式 */
  if (!date) return '';
  const year = date.substring(0, 4);
  const month = date.substring(4, 6);
  const day = date.substring(6);
  return `${year}-${month}-${day}`;
}

export function getCurrentDateStr(date) {
  // 获取当前年月日 时分秒，或格式化时间格式
  const data = date ? new Date(date) : new Date();
  const year = data.getFullYear();
  const mon = data.getMonth() + 1;
  const monStr = mon < 10 ? `0${mon}` : `${mon}`;
  const day = data.getDate();
  const dayStr = day < 10 ? `0${day}` : `${day}`;
  const hour = data.getHours();
  const hourStr = hour < 10 ? `0${hour}` : `${hour}`;
  const minutes = data.getMinutes();
  const minutesStr = minutes < 10 ? `0${minutes}` : `${minutes}`;
  const seconds = data.getSeconds();
  const secondsStr = seconds < 10 ? `0${seconds}` : `${seconds}`;
  return `${year}-${monStr}-${dayStr} ${hourStr}:${minutesStr}:${secondsStr}`;
}
