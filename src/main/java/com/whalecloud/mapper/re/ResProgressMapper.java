package com.whalecloud.mapper.re;

import com.whalecloud.domain.re.ResProgress;
import com.whalecloud.domain.re.ResProgressExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ResProgressMapper {
    int countByExample(ResProgressExample example);

    int deleteByExample(ResProgressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ResProgress record);

    int insertSelective(ResProgress record);

    List<ResProgress> selectByExampleWithRowbounds(ResProgressExample example, RowBounds rowBounds);

    List<ResProgress> selectByExample(ResProgressExample example);

    ResProgress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ResProgress record, @Param("example") ResProgressExample example);

    int updateByExample(@Param("record") ResProgress record, @Param("example") ResProgressExample example);

    int updateByPrimaryKeySelective(ResProgress record);

    int updateByPrimaryKey(ResProgress record);
}