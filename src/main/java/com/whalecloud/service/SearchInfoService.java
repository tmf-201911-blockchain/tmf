package com.whalecloud.service;

import com.whalecloud.domain.re.ResDetail;
import com.whalecloud.domain.re.SearchInfo;

import java.util.List;

/**
 * @author zhaoyanac
 * @date 2019/10/18
 */
public interface SearchInfoService {

    /**
     * 新增
     *
     * @param info
     */
    void add(String info, ResDetail resDetail);

    /**
     *
     * 删除所有的记录
     *
     * @param resourceType
     */
    void deleteAll(String resourceType);

    /**
     * 展示最新的10条数据
     *
     * @param resourceType
     * @return
     */
    List<SearchInfo> showList(String resourceType);

}


