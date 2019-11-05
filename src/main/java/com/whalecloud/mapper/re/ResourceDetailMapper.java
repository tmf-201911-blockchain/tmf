package com.whalecloud.mapper.re;

import com.whalecloud.domain.re.ResDetail;

import java.util.List;

/**
 * @author zhaoyanac
 * @date 2019/10/14
 */
public interface ResourceDetailMapper {

    /**
     * 得到一条信息
     *
     * @param resourceId
     * @return
     */
    ResDetail getOne(String resourceId);

    /**
     * 得到最新的5条信息
     * @return
     */
    List<ResDetail> getFiveInfo();

    /**
     * 得到所有
     *
     * @return
     */
    List<ResDetail> getAll();
}


