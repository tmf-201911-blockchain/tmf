package com.whalecloud.mapper.re;

import com.whalecloud.domain.re.ResDetail;
import com.whalecloud.domain.re.ResDetailExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ResDetailMapper {
    int countByExample(ResDetailExample example);

    int deleteByExample(ResDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ResDetail record);

    int insertSelective(ResDetail record);

    List<ResDetail> selectByExampleWithRowbounds(ResDetailExample example, RowBounds rowBounds);

    List<ResDetail> selectByExample(ResDetailExample example);

    ResDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ResDetail record, @Param("example") ResDetailExample example);

    int updateByExample(@Param("record") ResDetail record, @Param("example") ResDetailExample example);

    int updateByPrimaryKeySelective(ResDetail record);

    int updateByPrimaryKey(ResDetail record);
}