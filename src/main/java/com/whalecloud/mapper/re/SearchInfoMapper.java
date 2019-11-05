package com.whalecloud.mapper.re;

import com.whalecloud.domain.re.SearchInfo;
import com.whalecloud.domain.re.SearchInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SearchInfoMapper {
    int countByExample(SearchInfoExample example);

    int deleteByExample(SearchInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SearchInfo record);

    int insertSelective(SearchInfo record);

    List<SearchInfo> selectByExampleWithRowbounds(SearchInfoExample example, RowBounds rowBounds);

    List<SearchInfo> selectByExample(SearchInfoExample example);

    SearchInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SearchInfo record, @Param("example") SearchInfoExample example);

    int updateByExample(@Param("record") SearchInfo record, @Param("example") SearchInfoExample example);

    int updateByPrimaryKeySelective(SearchInfo record);

    int updateByPrimaryKey(SearchInfo record);
}