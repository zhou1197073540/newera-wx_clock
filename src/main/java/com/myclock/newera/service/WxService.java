package com.myclock.newera.service;

import com.myclock.newera.bean.StockInfo;
import com.myclock.newera.mapper.WxStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxService {
    @Autowired
    WxStockMapper wxStockMapper;

    public void saveOrUpdate(StockInfo stockInfo) {
        int num=wxStockMapper.selectNumByUidAndDate(stockInfo);
        if(num>0){
            wxStockMapper.updateStockInfo(stockInfo);
        }else{
            wxStockMapper.insertStockInfo(stockInfo);
        }
    }
}
