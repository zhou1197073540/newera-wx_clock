package com.myclock.newera.mapper;

import com.myclock.newera.bean.StockInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WxStockMapper {

    @Transactional
    @Select("select count(*) from wx_daily_stock_info where uid=#{uid} and date=#{date}")
    int selectNumByUidAndDate(StockInfo stockInfo);

    @Transactional
    @Update("update wx_daily_stock_info set account_info=#{account_info},trusts_info=#{trusts_info},position_info=#{position_info},balance_info=#{balance_info} " +
            " where uid=#{uid} and date=#{date}")
    void updateStockInfo(StockInfo stockInfo);

    @Transactional
    @Insert("insert into wx_daily_stock_info(uid,account_info,trusts_info,position_info,date,balance_info) " +
            "values(#{uid},#{account_info},#{trusts_info},#{position_info},#{date},#{balance_info})")
    void insertStockInfo(StockInfo stockInfo);
}
