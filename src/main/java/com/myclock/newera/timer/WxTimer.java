package com.myclock.newera.timer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myclock.newera.bean.StockInfo;
import com.myclock.newera.bean.StockSina;
import com.myclock.newera.crawler.SinaCrawler;
import com.myclock.newera.service.WxService;
import com.myclock.newera.utils.SampleHttpUtil;
import com.myclock.newera.utils.StringUtil;
import com.myclock.newera.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WxTimer {
    @Autowired
    WxService wxService;

    @Scheduled(cron = "0 10-50/5 15 * * MON-FRI")
    public void timerQuarterCalculate() throws Exception {
        String account_url="http://172.16.20.205:8866/user_account";
        String trusts_url="http://172.16.20.205:8866/checkinfo/todayentrusts?uid=%s";
        String position_url="http://172.16.20.205:8866/checkinfo/position?uid=%s";
        String balance_url="http://172.16.20.205:8866/checkinfo/balance?uid=%s";
        String account_json= SampleHttpUtil.getResult(account_url);
        JSONObject object=JSONObject.parseObject(account_json);
        if(object.containsKey("data")){
            Object[] objs= JSONArray.parseArray(object.getString("data")).toArray();
            for(Object obj:objs){
                JSONObject obj_ac=JSONObject.parseObject(obj.toString());
                String trusts_html="",postion_html="",balance_html="";
                StockInfo stockInfo=new StockInfo();
                if(obj_ac.containsKey("uid")){
                    trusts_html= SampleHttpUtil.getResult(String.format(trusts_url,obj_ac.getString("uid")));
                    postion_html= SampleHttpUtil.getResult(String.format(position_url,obj_ac.getString("uid")));
                    balance_html= SampleHttpUtil.getResult(String.format(balance_url,obj_ac.getString("uid")));
                    stockInfo.setUid(obj_ac.getString("uid"));
                    stockInfo.setAccount_info(obj.toString());
                }
                if(trusts_html.length()>10&&postion_html.length()>10&&balance_html.length()>10){
                    JSONObject trusts_json=JSONObject.parseObject(trusts_html);
                    JSONObject postion_json=JSONObject.parseObject(postion_html);
                    JSONObject balance_json=JSONObject.parseObject(balance_html);
                    if(trusts_json.containsKey("data")&&postion_json.containsKey("data")&&balance_json.containsKey("data")){
                        stockInfo.setTrusts_info(trusts_json.getString("data"));
                        stockInfo.setPosition_info(StringUtil.calculateRate(postion_json.getString("data")));
                        stockInfo.setBalance_info(balance_json.getString("data"));
                        wxService.saveOrUpdate(stockInfo);
                    }
                }
            }
        }
    }
}
