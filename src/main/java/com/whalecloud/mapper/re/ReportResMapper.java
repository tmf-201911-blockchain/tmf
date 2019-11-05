package com.whalecloud.mapper.re;

import com.whalecloud.domain.re.ReportRes;
import com.whalecloud.domain.re.ReportResExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ReportResMapper {
    int countByExample(ReportResExample example);

    int deleteByExample(ReportResExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReportRes record);

    int insertSelective(ReportRes record);

    List<ReportRes> selectByExampleWithRowbounds(ReportResExample example, RowBounds rowBounds);

    List<ReportRes> selectByExample(ReportResExample example);

    ReportRes selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReportRes record, @Param("example") ReportResExample example);

    int updateByExample(@Param("record") ReportRes record, @Param("example") ReportResExample example);

    int updateByPrimaryKeySelective(ReportRes record);

    int updateByPrimaryKey(ReportRes record);
}