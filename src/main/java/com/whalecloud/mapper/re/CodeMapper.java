package com.whalecloud.mapper.re;

import com.whalecloud.domain.re.Code;
import com.whalecloud.domain.re.CodeExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CodeMapper {
    int countByExample(CodeExample example);

    int deleteByExample(CodeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Code record);

    int insertSelective(Code record);

    List<Code> selectByExampleWithRowbounds(CodeExample example, RowBounds rowBounds);

    List<Code> selectByExample(CodeExample example);

    Code selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Code record, @Param("example") CodeExample example);

    int updateByExample(@Param("record") Code record, @Param("example") CodeExample example);

    int updateByPrimaryKeySelective(Code record);

    int updateByPrimaryKey(Code record);
}