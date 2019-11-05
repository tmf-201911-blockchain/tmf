package com.whalecloud.mapper.re;

import com.whalecloud.domain.re.Quotation;
import com.whalecloud.domain.re.QuotationExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuotationMapper {
    int countByExample(QuotationExample example);

    int deleteByExample(QuotationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Quotation record);

    int insertSelective(Quotation record);

    List<Quotation> selectByExampleWithRowbounds(QuotationExample example, RowBounds rowBounds);

    List<Quotation> selectByExample(QuotationExample example);

    Quotation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Quotation record, @Param("example") QuotationExample example);

    int updateByExample(@Param("record") Quotation record, @Param("example") QuotationExample example);

    int updateByPrimaryKeySelective(Quotation record);

    int updateByPrimaryKey(Quotation record);
}