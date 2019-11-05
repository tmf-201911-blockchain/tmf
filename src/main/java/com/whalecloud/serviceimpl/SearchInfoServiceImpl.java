package com.whalecloud.serviceimpl;

import com.whalecloud.domain.re.ResDetail;
import com.whalecloud.domain.re.SearchInfo;
import com.whalecloud.dto.enumdto.YNEnum;
import com.whalecloud.mapper.re.SearchMapper;
import com.whalecloud.service.SearchInfoService;
import com.whalecloud.util.SeedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 * 搜索
 *
 * @author zhaoyanac
 * @date 2019/10/18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SearchInfoServiceImpl implements SearchInfoService {


    @Autowired
    SearchMapper searchMapper;

    @Override
    public void add(String info, ResDetail resDetail) {
        SearchInfo searchInfo = new SearchInfo();
        searchInfo.setId(SeedUtil.getId());
        searchInfo.setResourceType(resDetail.getResourceType());
        searchInfo.setInfo(info);
        searchInfo.setLatitude(resDetail.getLatitude());
        searchInfo.setLongitude(resDetail.getLongitude());
        searchInfo.setCreateTime(new Date());
        searchInfo.setUpdateTime(new Date());
        searchInfo.setYn(YNEnum.NO.getCode());

        searchMapper.add(searchInfo);
}

    @Override
    public void deleteAll(String resourceType) {
        searchMapper.delete(resourceType);
    }

    @Override
    public List<SearchInfo> showList(String resourceType) {


        List<SearchInfo> searchInfos = searchMapper.showList(resourceType);


        return searchInfos;
    }
}


