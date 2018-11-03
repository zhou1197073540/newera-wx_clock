package com.myclock.newera.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

public class StringUtil {

    public static String calculateRate(String data) throws Exception{
        Object[] arrays=JSONArray.parseArray(data).toArray();
        JSONArray arrs=new JSONArray();
        for(Object obj:arrays){
            JSONObject json=JSONObject.parseObject(obj.toString());
            if (json.containsKey("市价")&&json.containsKey("成本价")&&json.containsKey("盈亏比")){
                String market=json.getString("市价");
                String cost=json.getString("成本价");
                Float fCost=Float.parseFloat(cost);
                if(fCost==0){
                    json.put("盈亏比","0");
                    arrs.add(json);
                }else{
                    BigDecimal market_price=new BigDecimal(json.getString("市价"));
                    BigDecimal cost_price=new BigDecimal(json.getString("成本价"));
                    BigDecimal rate=market_price.divide(cost_price,BigDecimal.ROUND_HALF_UP,4);
                    BigDecimal rate_percent=rate.subtract(new BigDecimal(1)).multiply(new BigDecimal(100)).setScale(2);
                    json.put("盈亏比",rate_percent);
                    arrs.add(json);
                }
            }
        }
        return arrs.toJSONString();
    }

    public static void main(String[] args) {
        BigDecimal market_price=new BigDecimal("5.67");
        BigDecimal cost_price=new BigDecimal("5.702999999999999");
        BigDecimal rate=market_price.divide(cost_price,BigDecimal.ROUND_HALF_UP,4);
        BigDecimal rate_percent=rate.subtract(new BigDecimal(1)).multiply(new BigDecimal(100)).setScale(2);
        System.out.println(rate_percent);
    }
}
