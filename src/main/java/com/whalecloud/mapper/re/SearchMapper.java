package com.whalecloud.mapper.re;

import com.whalecloud.domain.re.SearchInfo;

import java.util.List;

/**
 * @author zhaoyanac
 * @date 2019/10/18
 */
public interface SearchMapper {
    /**
     * 新增
     *
     * @param searchInfo
     */
    void add(SearchInfo searchInfo);

    /**
     *
     * 删除
     *
     * @param resourceType
     * @return
     */
    void delete(String resourceType);


    /**
     * 展示最新的10条数据
     *
     * @return
     */
    List<SearchInfo> showList(String resourceType);
}

